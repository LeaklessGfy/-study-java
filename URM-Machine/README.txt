Pour build le programme : 'ant build'
Pour générer la javadoc : 'ant doc'
Pour créer un jar : 'ant jar'
Pour run le programme : 'ant run'
Pour clean le dossier : 'ant clean'

'ant' tout court fera un : build, jar, run

Usage:
java -jar jar/URM.jar -f chemin/vers/fichier/urm_ou_eurm -m [STRICT/SAFE/LOOPDETECT] -r [1/2/3/4/5]

-f = specifie le chemin du fichier (doit être un fichier avec l'extension .urm ou .eurm)
-m = specifie le mode utilisé (STRICT, SAFE ou LOOPDETECT)
-r = specifie le type d'initialisation de registre.

Pour -r les options sont :
1 = seul le registre 1 sera initialisé avec les valeurs 0, 1, 2, 3, 4.
2 = le registre 1 et 2 seront initialisé avec des valeurs 0, 1, 2, 3, 4.
3 = Le registre 1 sera initialisé avec une valeur de BigInteger importante. 55555555555555555555555 pour être exacte.
4 = Le registre 1 sera initialisé avec une valeur négative ce qui doit provoquer une erreur.
5 = Des registres random (entre 1 et 6) seront initialisé avec des valeurs randoms (entre 0 et 20).

Vous trouverez dans le repertoire urm et eurm des exemples de programme.
