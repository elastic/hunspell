#!/usr/bin/env perl

use strict;
use warnings;
use YAML qw(LoadFile Dump);
use GetOpt::Long;
use Path::Class;
use HTTP::Tiny;
sub logmsg ($@);
sub throw ($@);

our $Conf = LoadFile('conf.yaml');

my $dicts = dir( $Conf->{paths}{dicts_dir} );

my @langs = @ARGV ? @ARGV : sort keys %{ $Conf->{langs} };
for my $lang ( sort @langs ) {
    count_words( $lang, $dicts->subdir($lang), $Conf->{langs}{$lang} );
}

#===================================
sub count_words {
#===================================
    my ( $lang, $dir, $conf ) = @_;
    next unless -e $dir;
    my $source = $conf->{source}
        or throw "No (source) configured for $lang", $conf;

    my ($type) = ( $source =~ /^(\w+):(.+)/ );
    my %lines = ( dic => 0, aff => 0 );
    for my $dic ( sort grep { $_ =~ /\.(dic|aff)$/ } $dir->children ) {
        my $count = `wc -l < "$dic"`;
        die "wc failed: $?" if $?;
        chomp($count);
        my $ext = $dic->basename =~ /\.dic$/ ? 'dic' : 'aff';
        $lines{$ext} += $count;
    }
    print join( "\t",
        $lang, $conf->{name}, $type, $conf->{license}, $lines{dic},
        $lines{aff} )
        . "\n";
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
