package ensta;
import ships.*;




public class Board implements IBoard  {

    /**
    * Nom donné à cette grille (nom du joueur quand il s'agit du joueur et non de l'IA).
    */
    private String nom;

    /**
    * grille contenant l'état des bateaux du joueur.
    */
    protected ShipState[][] navires;

    /**
    * grille contenant l'état des tirs effectués par le joueur.
    */
    protected Boolean[][] frappes;


    /**
    *@return nom de la grille
    */

    public String getNom(){
      return nom;
    }



    /** constructeur
    *@param nom est le nom de la grille créée
    *@param taille est la taille de la grille créée
    * @throws IllegalArgumentException si jamais la taille entrée en paramètre n'est pas correcte
    */

    public Board(String nom, int taille){
      if (taille>=27||taille<4){
        throw new IllegalArgumentException("Taille entrée : " + taille + ". Taille non comprise entre 4 et 26.");
      }
      this.nom=nom;
      this.navires=new ShipState[taille][taille];
      this.frappes=new Boolean[taille][taille];
      for(int i=0; i<taille; i++){
        for(int j=0; j<taille; j++){
          navires[i][j]=new ShipState();
          frappes[i][j]=null;
        }
      }
    }

    /** constructeur
    *@param nom est le nom de la grille créée
    */

    public Board(String nom){
      this.nom = nom;
      this.navires=new ShipState[10][10];
      this.frappes=new Boolean[10][10];
      for(int i=0; i<10; i++){
        for(int j=0; j<10; j++){
          navires[i][j]=new ShipState();
          frappes[i][j]=null;
        }
      }
    }


    /**
    *@return retourne la taille de la grille de jeu
    */

    public int getSize(){
      int taille=navires.length;
      return taille;
    }

    /**
    * place un navire à partir d'une position donnée en paramètre
    *@param ship est le navire que l'on souhaite placer
    *@param x la position en x où placer le navire
    *@param y la position en y où placer le navire
    *@throws ArrayIndexOutOfBoundsException si le navire est placé en partie ou totalement en dehors de la grille
    *@throws IllegalArgumentException si le navire est placé par dessus un autre navire déjà placé sur la grille
    */

    public void putShip(AbstractShip ship, int x, int y){
      int tailleGrille=getSize();
      int tailleBoat=ship.getLength();
      for(int k=0;k<tailleBoat;k++){
        switch (ship.getOrientation()){
          case NORTH:
            if((y-k)<0){
              throw new ArrayIndexOutOfBoundsException("Le bateau est placé trop haut.");
            }
            else if (hasShip(x, y-k)){
              throw new IllegalArgumentException("Le bateau est placé par dessus un autre bâteau.");
            }
            break;
          case SOUTH:
            if((y+k)>=tailleGrille){
              throw new ArrayIndexOutOfBoundsException("Le bateau est placé trop bas.");
            }
            else if (hasShip(x, y+k)){
              throw new IllegalArgumentException("Le bateau est placé par dessus un autre bâteau.");
            }
            break;
          case EAST:
            if((x+k)>=tailleGrille){
              throw new ArrayIndexOutOfBoundsException("Le bateau est placé trop à droite.");
            }
            else if (hasShip(x+k, y)){
              throw new IllegalArgumentException("Le bateau est placé par dessus un autre bâteau.");
            }
            break;
          case WEST:
            if((x-k)<0){
              throw new ArrayIndexOutOfBoundsException("Le bateau est placé trop à gauche.");
            }
            else if (hasShip(x-k, y)){
              throw new IllegalArgumentException("Le bateau est placé par dessus un autre bâteau.");
            }
            break;
        }
      }
      for(int k=0;k<tailleBoat;k++){
        switch (ship.getOrientation()){
          case NORTH:

            navires[y-k][x]=new ShipState(ship);

            break;
          case SOUTH:

            navires[y+k][x]=new ShipState(ship);

            break;
          case EAST:

            navires[y][x+k]=new ShipState(ship);

            break;
          case WEST:

            navires[y][x-k]=new ShipState(ship);

            break;

        }
      }
    }

    /**
    * @param x position en x
    * @param y position en y
    * @return renvoie vrai (true) si il y a un navire à cet emplacement, faux (false) si il n'y en a players
    * @throws ArrayIndexOutOfBoundsException si les coordonnées ne sont pas valides
    */

    public boolean hasShip(int x, int y){
      int borne=navires.length-1;
      if(x<0||y<0||x>borne||y>borne){
        throw new ArrayIndexOutOfBoundsException("Les coordonnées entrées ne sont pas comprises entre 0 et " + borne + " (bornes incluses).");
      }
      if(navires[y][x].toString()!="."){
        return true;
      }
      return false;
    }

    /**
    * @param hit la valeur de la frappe qui sera placée sur la grille des frappes
    * @param x position en x
    * @param y position en y
    * @throws ArrayIndexOutOfBoundsException si les coordonnées ne sont pas valides
    */

    public void setHit(Boolean hit, int x, int y){
      int borne=frappes.length-1;
      if(x<0||y<0||x>borne||y>borne){
        throw new ArrayIndexOutOfBoundsException("Les coordonnées entrées ne sont pas comprises entre 0 et " + borne + " (bornes incluses).");
      }
      frappes[y][x]=hit;

    }


    /**
    * @param x position en x
    * @param y position en y
    * @return renvoie le statut de la frappe de la grille des frappes en position x,y
    * @throws ArrayIndexOutOfBoundsException si les coordonnées ne sont pas valides
    */

    public Boolean getHit(int x, int y){
      int borne=frappes.length-1;
      if(x<0||y<0||x>borne||y>borne){
        throw new ArrayIndexOutOfBoundsException("Les coordonnées entrées ne sont pas comprises entre 0 et " + borne + " (bornes incluses).");
      }
      return frappes[y][x];

    }

    /**
    * @param x position en x
    * @param y position en y
    * @return MISS si le tir est raté, STRUCK si le tir a touché, l'enum DESTROYER/SUBMARINE/BATTLESHIP/CARRIER correspondant si jamais le navire est détruit avec ce tir
    * @throws IllegalArgumentException si la case a déjà été visée
    */


    public Hit sendHit(int x, int y){
      if (!hasShip(x, y)){
        return Hit.MISS;
      }
      else{
        try {
          navires[y][x].addStrike();
        }catch(Exception e){
          throw new IllegalArgumentException("La case a déjà été visée");
        }
        if(navires[y][x].isSunk()){
          System.out.println(navires[y][x].getShip().getLabel() + " coulé");
          switch(navires[y][x].getShip().getLabel()){
            case 'D':
              return Hit.DESTROYER;
            case 'S':
              return Hit.SUBMARINE;
            case 'B':
              return Hit.BATTLESHIP;
            case 'C':
              return Hit.CARRIER;
          }
        }
        else{
          return Hit.STRUCK;
        }
      }
      return Hit.MISS;
    }

    /**
    * permet d'afficher la grille des navires ainsi que la grille des tirs côte à côte
    */

    public void print(){
      int taille=getSize();
      int nombreCaracParLigne;

      if (taille<10){
        nombreCaracParLigne=2*taille+1;
      }
      else{
        nombreCaracParLigne=2*taille+2;
      }

      System.out.print("Navires :");
      for(int gap=0;gap<(nombreCaracParLigne-5);gap++){
        System.out.print(" ");
      }
      System.out.println("Frappes :");

      if (taille<10){
        System.out.print(" ");
      }
      else{
        System.out.print("  ");
      }

      for (int i=0; i<taille;i++){
        char c=(char)(65+i);
        System.out.print(" " + c);
      }

      System.out.print("    ");

      if (taille<10){
        System.out.print(" ");
      }
      else{
        System.out.print("  ");
      }

      for (int i=0; i<taille;i++){
        char c=(char)(65+i);
        System.out.print(" " + c);
      }
      System.out.println(" ");
      for(int j=1; j<taille+1; j++){
        if (j<10&&taille>=10){
          System.out.print(j + " ");
        }
        else{
          System.out.print(j);
        }
        for(int i=0;i<taille; i++){
            System.out.print(" " + navires[j-1][i].toString());
        }

        System.out.print("    ");

        if (j<10&&taille>=10){
          System.out.print(j + " ");
        }
        else{
          System.out.print(j);
        }
        for(int i=0;i<taille; i++){
          String affichageFrappe;
          if(frappes[j-1][i]==null){
            affichageFrappe=".";
          }
          else if(frappes[j-1][i]==false){
            affichageFrappe=ColorUtil.colorize("X", ColorUtil.Color.WHITE);
          }
          else{
            affichageFrappe=ColorUtil.colorize("X", ColorUtil.Color.RED);
          }
          System.out.print(" " + affichageFrappe);
        }


        System.out.println(" ");
      }
    }

}
