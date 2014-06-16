#!/usr/bin/env perl

use strict;
use warnings;
use YAML qw(LoadFile Dump);
use GetOpt::Long;
use Path::Class;
use HTTP::Tiny;
sub logmsg ($@);
sub throw ($@);

our $Conf     = LoadFile('conf.yaml');
our $http     = HTTP::Tiny->new;
our %Handlers = (
    download => {
        ooe => \&download_ooe,
        oo  => \&mirror_url,
        moz => \&mirror_url,
    },
    tidy => { oo => \&tidy_oo, }
);

our $OOE_Link_re = qr{
    <a [^>]* href="([^"]+)" [^>]* >
    (?:(?!</a>).)+
    Download \s extension
}xi;

our $SF_Link_re = qr{
    <a [^>]* href="([^"]+)" [^>]* >
    (?:(?!</a>).)+
    direct \s link
}xi;

our %Paths;

for ( 'dicts_dir', 'zips_dir' ) {
    my $dir = $Conf->{paths}{$_}
        || throw "No (paths.$_) specfied in conf";
    $Paths{$_} = dir($dir);
    $Paths{$_}->mkpath;
}

my @langs = @ARGV ? @ARGV : sort keys %{ $Conf->{langs} };
for my $lang (@langs) {
    download( $lang, $Conf->{langs}{$lang} );
}

#===================================
sub download {
#===================================
    my ( $lang, $conf ) = @_;
    logmsg "Downloading: ", $lang;

    my $source = $conf->{source}
        or throw "No (source) configured for $lang", $conf;

    my ( $type, $id ) = ( $source =~ /^(\w+):(.+)/ );
    $type ||= '';

    my $url;
    if ( $type eq 'http' ) {
        $url = $source;
    }
    else {
        $url = $Conf->{urls}{$type}
            || throw "No URL specified for ($type) in config", $conf;
        $url =~ s/\{id\}/$id/g;
    }

    my $file = $Paths{zips_dir}->file( $lang . ".zip" );
    if ( -e $file ) {
        logmsg " - Skipping, $file exists";
        return;
    }

    my $last_mod = -e $file ? $file->stat->mtime : 0;
    $Handlers{download}{$type}->( $url, $file );
    return logmsg " - Not updated"
        if -e $file && $last_mod eq $file->stat->mtime;

    logmsg " - Processing";
    my $dir = unpack_zip( $lang, $type, $conf, $file );

    if ( my $tidy = $Handlers{tidy}{$type} ) {
        $tidy->( $lang, $dir, $conf );
    }
}

#===================================
sub download_ooe {
#===================================
    my ( $url, $file ) = @_;
    my $download_page = get_url($url);
    my ($path) = ( $download_page =~ m/$OOE_Link_re/ )
        or throw("Couldn't extract download link from ($url)");
    $url =~ s{(https?://[^/]+)/.+}{$1$path};

    return mirror_url( $url, $file );
}

#===================================
sub mirror_url {
#===================================
    my ( $url, $file ) = @_;
    my $response = $http->mirror( $url, $file );
    throw "Couldn't retrieve ($url): " . $response->{reason}
        unless $response->{success};

}

#===================================
sub get_url {
#===================================
    my ($url) = @_;
    my $response = $http->get($url);
    throw "Couldn't retrieve ($url): " . $response->{reason}
        unless $response->{success};
    return $response->{content};
}

#===================================
sub tidy_oo {
#===================================
    my ( $lang, $dir, $conf ) = @_;
    my $readme = $conf->{readme} or return;
    my $url = $Conf->{urls}{oo};
    $url =~ s/{id}/${readme}/;
    my $file = $dir->file("README_${lang}.txt");
    mirror_url( $url, $file );
}

#===================================
sub unpack_zip {
#===================================
    my ( $lang, $type, $conf, $file ) = @_;
    my $dir = $Paths{dicts_dir}->subdir($lang);
    $dir->rmtree;
    $dir->mkpath;

    my @skip = ( @{ $Conf->{skip}{$type} || [] }, @{ $conf->{skip} || [] } );
    unshift @skip, "-x" if @skip;

    my $exit = system(
        "unzip",
        "-qq",    # quiet
        "-n",     # don't overwrite existing
        "-j",     # ignore paths
        "-d", $dir,    # dest dir
        $file,
        @skip
    );

    die "Couldn't unzip ($file): $?"
        unless $exit == 0 || $exit == 256;

    return $dir;
}

#===================================
sub move_file {
#===================================
    my ( $src_file, $dest_file ) = @_;
    inspect_dir( $src_file->parent, "Source file ($src_file) doesn't exist" )
        unless -e $src_file;
    $src_file->move_to($dest_file)
        || throw "Couldn't move ($src_file) to ($dest_file): $!";
}

#===================================
sub find_files {
#===================================
    my $dir = shift;
    my @files;
    for (@_) {
        my $file     = $dir->file($_);
        my $basename = $file->basename;
        if ( $basename =~ /\*/ ) {
            for ( $file->parent->children ) {
                next if $_->is_dir;
                push @files, $_ if $_->basename =~ /$basename/;
            }
        }
        else {
            next if $file->is_dir;
            push @files, $file if -e $file;
        }
    }
    return @files;
}

#===================================
sub inspect_dir {
#===================================
    my $dir = shift;
    logmsg "@_";
    logmsg "Inspecting directory - press Ctrl-D to exit";
    chdir $dir;
    system $ENV{SHELL};
    exit 1;
}

#===================================
sub logmsg ($@) {
#===================================
    print "@_\n";
}

#===================================
sub throw ($@) {
#===================================
    my $msg = shift;
    if (@_) {
        $msg .= ': ' . Dump( shift() );
    }
    die "$msg\n";
}
