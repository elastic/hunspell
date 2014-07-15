# Latvieðu valodas pareizrakstîbas pârbaudes bibliotçka (afiksu un vârdnîcas fails) 
# lietoðanai ar OpenOffice 2.4.1 un augstâk
# Latvian spelling dictionary (affix and dictionary files) for OpenOffice 2.4.1 and higher
#
# Copyright (C) 2002-2010 Janis Eisaks, jancs@dv.lv, http://dict.dv.lv
# 
# Ðî bibliotçka tiek licencçta ar Lesser General Public Licence (LGPL) 2.1 nosacîjumiem. 
# Licences nosacîjumi pievienoti failâ license.txt vai iegûstami tîmekïa vietnç  
# http://www.fsf.org/licensing/licenses/lgpl.html
# 
# This library is free software; you can redistribute it and/or
# modify it under the terms of the GNU Lesser General Public
# License as published by the Free Software Foundation; either
# version 2.1 of the License, or (at your option) any later version.
#
# This library is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
# Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public
# license along with this library; if not, write to the Free Software
# Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA


1. Uzstâdîðana
2. Interesentiem
3. Izmaiòu saraksts

Svarîgs paziòojums:

ðî vârdnîcas versija vairs neuztur MySpell pareizrakstîbas dziòa izmantoðanu.
Afiksu bâze ir veidota, izmantojot Hunspell palaðinâto funkcionalitâti.

=================

1. Vârdîcas uzstâdîðana

Ieteikums: uzstâdît vismaz OO 3.2 versiju.
Vârdnîcas uzstâdîðana ir ïoti vienkârða - izmantojot OO Extension Manager.
Extension Manager piedâvâ iespçju kâ tieðsaistes, tâ lokâlu paplaðinâjumu uzstâdîðanu.
Ja uzstâdîðana tieðsaistes reþîmâ nav iespçjama, vajadzîgo valodas paplaðinâjumu (vârdnîcu)
var lejupielâdçt ðeit:

http://extensions.services.openoffice.org/dictionary

un izmantot lokâlai uzstâdîðanai.

Ja izmantojat OO versiju, kas neuztur Extensions (pirms 2.4.1), tad:

1. iespçja. Uzstâdîðana tieðsaistes reþîmâ
 No izvçlnes File/Wizards/Install new dictionaries palaidiet att. vedni, izvçlieties 
 Jums tîkamo vedòa valodu un sekojiet norâdîjumiem. Bez latvieðu valodas pareizrakstîbas 
 rîkiem Jûs vienlaicîgi varat uzstâdît papildus valodas vai atsvaidzinât esoðâs bibliotçkas.
 (Uzmanîbu! - nav zinâms, cik ilgi ðî bibliotçka vçl tiks aktualizçta; pilns laidienu arhîvs ir 
  atrodams http://sourceforge.net/projects/openoffice-lv/)

 Ja kaut kâdu iemeslu dçï nevarat izmantot 1. iespçju, ir
 
 2. iespçja. "Offline" uzstâdîðana
 Lejupielâdçjiet pçdçjo moduïa versiju no openoffice-lv.sourceforge.net .
 Pçc faila iegûðanas tas ir jâatpako direktorijâ %Openoffice%\share\dict\ooo, 
 kur %Openoffice% - direktorija, kurâ veikta OpenOffice uzstâdîðana. Tur esoðajam failam 
 dictionary.lst ir jâpievieno sekojoðas rindas: 
 
 DICT lv LV lv_LV
 HYPH lv LV hyph_lv_LV

 vai arî jâizpilda win-lv_LV_add.bat (Windows gadîjumâ) vai, Linux gdîjumâ, jâizpilda 
 komandu:

   sh <lin-lv_LV_add.sh

 Lai izpildîtu 2. iespçju, Jums ir jâbût tiesîbâm rakstît minçtajâ katalogâ. Ja tâdu nav, 
 varat uzstâdît vçrdnîcu lokâli, savâ lietotâja opciju katalogâ (%OOopt%/user/wordbook).

 Offline uzstadîðanai var izmantot arî 1. iespçjâ minçto vedni, viss notiks lîdzîgi, 
 tikai nepiecieðamajâm moduïu pakotnçm bûs jâbût uz lokâlâ diska. Jâpiezîmç ka, piemçram, 
 SUSE gadîjumâ minçtais vednis ir izgriezts ârâ no OO un 2. iespeja ir vienîgâ. Atseviðíi 
 ðis lîdzeklis un vârdnîcas ir iegûstams tîmekïa vietnç
 
  http://wiki.services.openoffice.org/wiki/Dictionaries

Ar to moduïu uzstâdîðana praktiski ir pabeigta; atliek vienîgi caur 
Options>Language settings>Writing aids ieslçgt vai izslçgt nepiecieðamos moduïus un 
iestatît dokumentu noklusçto valodu.


 Ja ir nepiecieðama automâtiskâ pareizrakstîbas pârbaude, zem Tools>Spellcheck jâieíeksç 
 AutoSpellcheck.

================

2. Interesentiem

Ja jums ir iekrâjuðies vârdi, kurus ðis lîdzeklis neatpazîst vai arî atpazîst kïûdaini, esat
laipni aicinâti tos atsûtît tâlâkai vârdnîcas pilnveidoðanai vai arî reìistrçties vârdnîcas 
izstrâdei veltîtajâ vietnç //dict.dv.lv.

Lielâka apjoma dokumentu filtrçðanai var izmantot sekojoðâ vietâ atrodamu StarBasic makrosu:
http://lingucomponent.openoffice.org/servlets/ReadMsg?listName=dev&msgNo=1843

Piezîme - makross nedarbojas ar OO >3.0.

Sarakstu gadîjumâ ir lûgums sâkumâ paðiem kritiski izvçrtçt neatpazîto vârdu pareizîbu 
vai to pielietojamîbu (piem slengs, barbarismi utml. drazas, manuprât, nav tâ vçrtas, 
lai tâs iekïautu pareizrakstîbas pârbaudes vârdnîcâ, lai gan viena otra tomçr iespraucas).
