
NOTICE
_______________________________________________________________________________

Ce dictionnaire, appelé Réforme 1990, respecte les rectifications de
l'orthographe proposées en 1990 par le Conseil supérieur à la langue française
et par l'Académie française.
http://www.orthographe-recommandee.info/

Attention ! Ce dictionnaire est prévu pour fonctionner avec Myspell, l'ancien 
correcteur orthographique d'OpenOffice.org, depuis les versions 1.x jusqu'à la
version 2.0.1. Si vous avez une version postérieure d'OpenOffice.org, utilisez
les dictionnaires créés pour Hunspell.
Note : Firefox 3 utilise le correcteur Hunspell, Firefox 2 Myspell.

Contact: dico.savant<at>free.fr
Dicollecte: http://dico.savant.free.fr/

Remerciements à :
- Vazkor, pour l'examen minutieux du dictionnaire et les innombrables
  corrections apportées;
- Jean Beney, pour la comparaison avec le dictionnaire de l'ABU qui a permis de
  débusquer maintes erreurs;
- Romain Muller, pour la vérification des graphies spécifiques à la réforme de
  l'orthographe de 1990;
- et à tous ceux qui ont apporté leur pierre à l'édifice.

_______________________________________________________________________________

LICENCE
_______________________________________________________________________________

Avec l'accord de l'auteur initial, Christophe Pythoud, ces dictionnaires
sont désormais distribués sous triple licence : LGPL, GPL, MPL.

Licence GPL 2    ou supérieure   http://www.gnu.org/licenses/gpl-2.0.html
Licence LGPL 2.1 ou supérieure   http://www.gnu.org/licenses/lgpl-2.1.html
Licence MPL 1.1  ou supérieure   http://www.mozilla.org/MPL/MPL-1.1.html

La première version du dictionnaire MySpell pour OpenOffice.org a été créée 
automatiquement à partir de la convertion du fichier affix et des listes 
de mots créés par Christophe Pythoud pour Ispell. 
Ces fichiers ont été publiés dans la version 1.0.1 de Français-GUTenberg et
étaient soumis à la licence GPL version 2.

_______________________________________________________________________________

CHANGELOG
_______________________________________________________________________________

##### VERSION 1.3.1 - avril 2008 ##############################################
établie par O.R.

----- Modifications du dictionnaire -------------------------------------------

* Dépliage des lemmes préfixés avec C D N P R O T.
  A permis d'éliminer bon nombre d'erreurs et de redondances.
* Mise à jour avec les propositions du site "Dicollecte".
  
----- Modifications du fichier des affixes ------------------------------------

* Suppression des drapeaux de préfixation : C D N P R O T


##### VERSION 1.2.0 - mars 2008 ###############################################
##### VERSION 1.1.6 - février 2008 ############################################
établies par O.R.

----- Modifications du dictionnaire -------------------------------------------

* Ajout de communes françaises (villes de plus de 20000 habitants)
* Mise à jour avec les entrées proposées sur le site "Dicollecte".
* Diverses corrections
* Quelques ajouts

----- Modifications du fichier des affixes ------------------------------------

* Modifications du mécanisme de suggestion :
  + REP ai é
  + REP é ai
  + REP ai è
  + REP è ai
  + REP ai ê
  + REP ê ai
  + REP ei é
  + REP é ei
  + REP ei è
  + REP è ei
  + REP ei ê
  + REP ê ei
  + REP ss c
  + REP c ss
  + REP k qu
  + REP qu k
  + REP x ct
  + REP ct x
  + REP ss ç
  + REP ç ss
  + REP o au
  + REP au o
  + REP o eau
  + REP eau o
  + REP disez dites
  + REP fesez faites
  + REP faisez faites
  + REP décrédibilis discrédit
  + REP antitartre détartrant
  Exemples:
    luminère   > luminaire
    pégne      > peigne
    impreigner > imprégner
* Modification du drapeau y
  > SFX y   er         è-je       er
  

##### VERSION 1.1.5 - décembre 2007 ###########################################
##### VERSION 1.1.4 - décembre 2007 ###########################################
##### VERSION 1.1.3 - décembre 2007 ###########################################
établies par O.R.

* Mise à jour avec les entrées proposées sur le site "dico|savant".
* Diverses corrections


##### VERSION 1.1 - novembre 2007 #############################################
établie par O.R.

----- Modifications du dictionnaire -------------------------------------------

* Suppression de milliers d'entrées redondantes (notamment avec les déclinaisons
  engendrées par les drapeaux p, q).
* Corrections des mots avec ligatures ('oe', 'ae').
* Ordonnancement des drapeaux (et effacement des doublons).
* Des centaines de nouvelles entrées.
* Des centaines de corrections.
* Mise à jour avec les entrées proposées sur le site "dico|savant".
* Reprise partielle des corrections et ajouts du dictionnaire de Vazkor
  du 08 septembre 2007.
  A la demande de l'intéressé, seul son pseudonyme demeure. La page web
  fournissant ce dictionnaire a été effacée.
  La plupart des ajouts et des modifications ont été repris (environ 3200 sur 4900).
  Seules les suppressions des entrées erronées ont été reprises (environ 800 sur 12300).
  Les termes rares, scientifiques, mathématiques, les mots composés, etc. n'ont
  pas été effacés.
  Quelques erreurs ont été corrigées.
* Comparaison du dictionnaire avec celui de l'ABU, ce qui a permis de corriger
  des centaines d'erreurs et de combler des oublis. (Jean Beney)
* Vérification des nouvelles orthographes recommandées. (Romain Muller)
  http://www.orthographe-recommandee.info/
  
----- Modifications du fichier des affixes ------------------------------------

* ajout des lignes MAP, REP et WORDCHARS
  >> meilleures suggestions de correction
* modification du drapeau D
  > PFX D   0          dé         [^aàâeèéêiîoôuh½æ]
  > PFX D   0          dés        [aàâeèéêiîoôuh½æ]
* modification du drapeau R
  > PFX R   0          re         [^aàâeèéêiîoôuhs½æ]
  > PFX R   0          ré         [aàâeèéêiîoôuh½æ]
* modification du drapeau O
  > PFX O   0          ré         [^aàâeèéêiîoôuhs½æ]
  > PFX O   0          r          [aàâeèéêiîoôuh½æ]
* modification du drapeau L
  > PFX L   0          l'         [aàâeèéêiîoôuyh½æAÀÂEÈÉÊIÎOÔUYH¼Æ]
* modification du drapeau D
  > PFX M   0          d'         [aàâeèéêiîoôuyh½æAÀÂEÈÉÊIÎOÔUYH¼Æ]
* modification du drapeau Q
  > PFX Q   0          qu'        [aàâeèéêiîoôuyh½æAÀÂEÈÉÊIÎOÔUYH¼Æ]
  > PFX Q   0          quoiqu'    [aàâeèéêiîoôuyh½æAÀÂEÈÉÊIÎOÔUYH¼Æ]
  > PFX Q   0          puisqu'    [aàâeèéêiîoôuyh½æAÀÂEÈÉÊIÎOÔUYH¼Æ]
  > PFX Q   0          lorsqu'    [aàâeèéêiîoôuyh½æAÀÂEÈÉÊIÎOÔUYH¼Æ]
* modification du drapeau U
  > PFX U   0          jusqu'     [aàâeèéêiîoôuh½æ]
* modification du drapeau X
  > SFX X   0          x          [aeo½]u
* modification du drapeau j
  > PFX j   0          j'         [aàâeèéêiîoôuyh½æ]
* modification du drapeau n
  > PFX n   0          n'         [aàâeèéêiîoôuyh½æ]
  > PFX n   0          qu'        [aàâeèéêiîoôuyh½æ]
* modification du drapeau m
  > PFX m   0          m'         [aàâeèéêiîoôuyh½æ]
* modification du drapeau t
  > PFX t   0          t'         [aàâeèéêiîoôuyh½æ]
* modification du drapeau l
  > PFX l   0          l'         [aàâeèéêiîoôuyh½æ]
* modification du drapeau s
  > PFX s   0          s'         [aàâeèéêiîoôuyh½æ]
* modification du drapeau F
  - SFX F   ée         è          ée
  - SFX F   èle        el         èle
  - SFX F   èle        els        èle
  > SFX F   ne         n          [aiou]ne
  > SFX F   e          s          [aiou]ne
  > SFX F   re         r          [aiuûy]re
  > SFX F   e          s          [aiuûy]re
  + SFX F   esse       e          esse
  + SFX F   he         h          [ut]he
  + SFX F   e          s          [ut]he
  + SFX F   ke         k          ke
  + SFX F   e          s          ke
* modification du drapeau x
  > le champ conditionnel 'ai' a été remplacé par 'rai'
* modification du drapeau p
  > SFX p   e          èrent      [^è][^è].e
  > SFX p   0          ra         e
  > SFX p   0          ront       e
  > SFX p   0          rait       e
  > SFX p   0          raient     e
  + SFX p   ie         yèrent     [aou]ie
  + SFX p   elle       elèrent    elle
  + SFX p   ère        érèrent    ère
  + SFX p   ège        égèrent    ège
  + SFX p   ève        evèrent    ève
  + SFX p   ène        énèrent    [^mr]ène
  + SFX p   ène        enèrent    [mr]ène
  + SFX p   ète        étèrent    [^h]ète
  + SFX p   ète        etèrent    chète
  + SFX p   èle        elèrent    èle
  + SFX p   èche       échèrent   èche
  + SFX p   èque       équèrent   èque
  + SFX p   ètre       étrèrent   ètre
  + SFX p   ègne       égnèrent   ègne
  + SFX p   ègre       égrèrent   ègre
  + SFX p   èvre       évrèrent   èvre
  
  Légende : 
  > lignes modifiées
  + lignes ajoutées
  - lignes supprimées


  
###############################################################################

Ce dictionnaire MySpell inclut les propositions de rectifications de l'orthographe, émises en 1990 par le Conseil supérieur à la langue française et par l'Académie française, et considère comme fautives les anciennes graphies. Il a été créé à partir du dictionnaire MySpell fr_FR 1.0.1 de Français-GUTenberg (voir ci-dessous).

Septembre 2006
~~~~~~~~~~~~~~
- intégration au projet lingucomponent d'OpenOffice.org (lgodard@indesko.com)

Juin 2006
~~~~~~~~~
- Ajouts de nombreux noms propres et de néologismes
- Correction des pluriels de quelques mots en -al (naval, banal ...)
- Correction de la 2e personne du pluriel de l'indicatif des verbes dérivés de dire (prédire, dédire, médire ...)
- Restructuration du fichier d'instructions *.aff, suppression des paramètres inutilisés, suppression des paramètres utiles à un seul verbes
- Regroupement des verbes avec leurs dérivés à préfix commençant par une consonne (contre-, dé-, ré-, pré-, sur-)


Octobre 2005
~~~~~~~~~~~~
Reprise de la liste des mots du dictionnaire MySpell fr_FR 1.0.1 de Français-GUTenberg et adaptation de celle-ci (ajouts et/ou suppresions de mots) afin de la faire correspondre aux graphies renouvelées proposées en 1990 par deux organes officiels de l'État français (pour plus d'info, voir : http://www.orthographe-recommandee.info/)
Pour toutes propositions, remarques, critiques, ... : mat.schopfer@bluewin.ch


Historique du dictionnaire MySpell fr_FR de Français-GUTenberg :
----------------------------------------------------------------

version 1.0.1
Version corrigée de la liste de mots avec regroupement des mots équivalents afin 
de supprimer des fautes lors de la correction (les mots sont considérés comme faux
si ils existent à double dans la liste de mots)

version 1.0.0
Version du dictionnaire MySpell fr_FR générée automatiquement depuis à partir de la
convertion du fichier affix et des listes de mots créés par Christophe Pythoud pour
Ispell
