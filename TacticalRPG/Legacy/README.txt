-------------------
The Legend Of Zelda
-------------------

Réalisé en java par Mathias Brulatout et Matthieu Douris S4P2B'.

-----------
Compilation
-----------

Marche parfaitement sous Linux et Windows. Si l'erreur suivante arrive :
- Link passe a travers les murs et il n'y a aucun monstre
Il existe 2 solutions :
- La compilation marche par javac en terminal (voir dossier Livrable avec .jar parfaitement fonctionnel). Le .jar doit être dans le même dossier que les dossiers maps, musique et sprites,
- Il faut modifier le fichier maps.java du modele et changer la ligne 21 : remplacer << String path="maps/matrice"; >> par << String path="src/maps/matrice"; >> et la compilation marchera alors depuis Eclipse.