README for Setswana MySpell dictionary
======================================

The MySpell spell checker was created from the aspell spell checker and
wordlist which is released under the GPL.

1. Copyright
2. Installation and setup
3. Helping to improve the spellchecker
4. Note on the construction of the wordlist


1. Copyright
------------

Setswana wordlist.in licensed under the GPL:
Copyright 2004 Kevin P. Scannell <scannell@slu.edu> and
               Thapelo Otlogetswe <Thapelo.Otlogetswe@itri.brighton.ac.uk>

Porting to MySpell and other MySpell specifics licensed under the LGPL
Copyright 2004 Zuza Software Foundation <info@translate.org.za>

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.

The CTexT list is produced by the North West University - CTexT (http://www.nwu.ac.za/ctext)
and is released under a Creative Commons Attribution 2.5 South Africa license (CC-BY)

2. Installation and setup
-------------------------

Automated
---------
http://lingucomponent.openoffice.org/download_dictionary.html

Use the DicOOo.sxw file to step you through an automatic install process.  If
the Setswana spellchecker is not available online then download the offline
pack from:
http://translate.sourceforge.net/


Non-automated
-------------
For instructions on how to install the Setswana dictionary please visit the
following URL.

http://lingucomponent.openoffice.org/download_dictionary.html#installspell

Spellchecker Selection
----------------------
NOTE: Setswana is as yet not a recognised language in OpenOffice.org - this
will change shortly - therefore we map the dictionary against Italian.

Tools -> Options -> Language Settings -> Writing Aids

Available language modules -> Edit -> Select Italian -> Ensure it is enabled



3. Contributing
---------------

You can help to make this software better.

If you find errors in the spellchecker or have wordlists that you would like to
contribute to the spellchecker then contact 
Dwayne Bailey <dwayne@translate.org.za>

If you would like to assist Kevin Scannell with the automated web crawler then
please read the next section and offer your assistance.


4. Note on the construction of the wordlist
-------------------------------------------

Note: taken from the Aspell package (doc/Crawler.txt) for your information

NOTES ON THE CONSTRUCTION OF THE WORD LIST
   A preliminary version of this spell checking dictionary was assembled
with the help of my web crawler "An Crúbadán":

  http://borel.slu.edu/crubadan/

BUILDING TEXT CORPORA FOR MINORITY LANGUAGES
Initially a small collection of "seed" texts are fed to the crawler
(a few hundred words of running text have been sufficient in practice).
Queries combining words from these texts are generated and passed to
the Google API which returns a list of documents potentially written
in the target language.  These are downloaded, processed into plain text,
and formatted.  A combination of statistical techniques bootstrapped from
the initial seed texts (and refined as more texts are added to the database)
is used to determine which documents (or sections thereof) are written in
the target language.   The crawler then recursively follows links contained
within documents that are in the target language.   When these run out,
the entire process is repeated, with a new set of Google queries generated
from the new, larger corpus.

EXTRACTING A CLEAN WORD LIST
The raw texts downloaded using the scheme just described contain
a lot of pollution and are unsuitable for use without further processing.   
I have been able to extract reasonably accurate spell checking dictionaries
by applying a series of simple filters.   First, the texts are tokenized
and used to generate a word list sorted by frequency and the lowest
frequency words are filtered out.   Then, depending on the target language,
correctly-spelled words from one or more "polluting" languages
are filtered out to be checked by hand later.  Usually this means English,
but I also filter Dutch from the Frisian corpus, Spanish from Chamorro, etc.
The remaining words are used to generate 3-gram statistics for the target
language.  These are used to flag as "suspect" any remaining words containing
one or more improbable 3-grams.

Please contact me at the address below if you are interested in applying
these techniques to a new language.

Kevin Scannell 
<scannell@slu.edu>
March 2004

