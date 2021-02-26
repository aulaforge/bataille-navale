# bataille-navale

Pour faire fonctionner le projet, ouvrez un terminal, placez vous dans le dossier bataille-navale (le dossier à la racine du projet), puis exécutez " mvn clean install exec:java "


Exercice 1 : 

- Les grilles sont "protected" car on y aura appel régulièrement pour appliquer d'autres méthodes, on évite ainsi de faire beaucoup d'appels de méthode consécutifs (ex: board.méthode1().méthode2().méthode3()).
Le nom est privé car on y aura peu besoin et il n'a normalement pas à etre modifié. Il a donc un "getter" uniquement.

Exercice 2 : 

- Pour représenter les orientations des navires, on crée un enum avec les 4 valeurs dont on a besoin.

- Pour remédier au problème du nombre de fichier, on peut créer des packages différents. On mettra ainsi les fichiers correspondant aux navires dans un package "ships".


Exercice 3 : 

- Les indices du tableau commence à 0, donc les indices de position commence à 0. Pour les faire correspondre à "A, B, C etc" ou "1, 2, 3 etc" on fera les décalages nécessaires.

- Si les valeurs positions+longueur dépassent la taille du tableau, on renvoie une exception correspondant à cette erreur (ArrayIndexOutOfBoundsException, avec un message d'aide).

- Si deux navires se chevauchent lorsque l'on cherche à poser un navire, on renvoie une exception y correspondant (IllegalArgumentException, puisque l'argument n'a pas le droit d'être choisi, et on y indique un message d'aide).

Exercice 5 :

- Si jamais addStrike est appelé plus d'une fois par ShipState, la fonction renverra une erreur avec un message l'expliquant.

Exercice 6 :

- Si on appelle sendHit(x ,y) deux fois sur la même position, on renvoie une exception. Si jamais on choisi deux fois la même coordonnées dans la méthode sendHit de la classe Player, la méthode demandera de rentrer de nouvelles coordonnées.

- La méthode hasShip(x, y) renvoie toujours la même chose si le navire a été coulé, puisque qu'on vérifiera de toute façon si le navire a été coulé lorsque l'on fait appel à cette fonction.

Exercice 7 :

- On utilise une liste de type List<AbstractShip> qu'on transformera en Array si nécessaire, pour n'avoir qu'à .add(newElement) plutôt que changer la taille du Array lors de la déclaration à chaque fois qu'on veut ajouter ou retirer un navire dans le jeu pour effectuer un test (un élément Array étant statique, il faut lui donner la bonne taille dès le début).
