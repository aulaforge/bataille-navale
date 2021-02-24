package ensta;
import ships.*;
public class Board implements IBoard  {
    private String nom;
    public ShipState[][] navires;
    public Boolean[][] frappes;

    public String getNom(){
      return nom;
    }

    public void setNom(String nouvNom){
      this.nom=nouvNom;
    }

    public Board(String nom, int taille){
      if (taille>=27||taille<4){
        throw new IllegalArgumentException("Taille entrée : " + taille + ". Taille non comprise entre 4 et 26.");
      }
      this.nom=nom;
      this.navires=new ShipState[taille][taille];
      this.frappes=new Boolean[taille][taille];
      for(int i=0; i<taille; i++){
        for(int j=0; j<taille; j++){
          frappes[i][j]=null;
        }
      }
    }

    public Board(String nom){
      this.nom = nom;
      this.navires=new ShipState[10][10];
      this.frappes=new Boolean[10][10];
      for(int i=0; i<10; i++){
        for(int j=0; j<10; j++){
          frappes[i][j]=null;
        }
      }
    }



    public int getSize(){
      int taille=navires.length;
      return taille;
    }


    public void putShip(AbstractShip ship, int x, int y){
      int tailleGrille=getSize();
      int tailleBoat=ship.getTaille();
      for(int k=0;k<tailleBoat;k++){
        switch (ship.getOrientation()){
          case NORTH:
            if((y-k-1)<0){
              throw new ArrayIndexOutOfBoundsException("Le bateau est placé trop haut.");
            }
            else if (navires[y-k-1][x-1]!=null){
              throw new IllegalArgumentException("Le bateau est placé par dessus un autre bâteau.");
            }
            break;
          case SOUTH:
            if((y+k-1)>=tailleGrille){
              throw new ArrayIndexOutOfBoundsException("Le bateau est placé trop bas.");
            }
            else if (navires[y+k-1][x-1]!=null){
              throw new IllegalArgumentException("Le bateau est placé par dessus un autre bâteau.");
            }
            break;
          case EAST:
            if((x+k-1)>=tailleGrille){
              throw new ArrayIndexOutOfBoundsException("Le bateau est placé trop à droite.");
            }
            else if (navires[y-1][x+k-1]!=null){
              throw new IllegalArgumentException("Le bateau est placé par dessus un autre bâteau.");
            }
            break;
          case WEST:
            if((x-k-1)<0){
              throw new ArrayIndexOutOfBoundsException("Le bateau est placé trop à gauche.");
            }
            else if (navires[y-1][x-k-1]!=null){
              throw new IllegalArgumentException("Le bateau est placé par dessus un autre bâteau.");
            }
            break;
        }
      }
      for(int k=0;k<tailleBoat;k++){
        switch (ship.getOrientation()){
          case NORTH:

            navires[y-k-1][x-1]=new ShipState(ship);

            break;
          case SOUTH:

            navires[y+k-1][x-1]=new ShipState(ship);

            break;
          case EAST:

            navires[y-1][x+k-1]=new ShipState(ship);

            break;
          case WEST:

            navires[y-1][x-k-1]=new ShipState(ship);

            break;

        }
      }
    }


    public boolean hasShip(int x, int y){
      int borne=navires.length;
      if(x<=0||y<=0||x>borne||y>borne){
        throw new ArrayIndexOutOfBoundsException("Les coordonnées entrées ne sont pas comprises entre 1 et " + borne + " (bornes incluses).");
      }
      if(navires[y-1][x-1]!=null){
        return true;
      }
      return false;
    }


    public void setHit(Boolean hit, int x, int y){
      int borne=frappes.length;
      if(x<=0||y<=0||x>borne||y>borne){
        throw new ArrayIndexOutOfBoundsException("Les coordonnées entrées ne sont pas comprises entre 1 et " + borne + " (bornes incluses).");
      }
      frappes[y-1][x-1]=hit;

    }


    public Boolean getHit(int x, int y){
      int borne=frappes.length;
      if(x<=0||y<=0||x>borne||y>borne){
        throw new ArrayIndexOutOfBoundsException("Les coordonnées entrées ne sont pas comprises entre 1 et " + borne + " (bornes incluses).");
      }
      return frappes[y-1][x-1];

    }


    public void print(){
      int taille=navires.length;
      int nombreCaracParLigne;
      if(taille>=27){
        System.out.println("Taille trop grande, non gérée, veuillez rentrer une valeur entre 4 et 26 (bornes incluses).");
        return;
      }
      else if(taille<4){
        System.out.println("Taille trop petite, non gérée, veuillez rentrer une valeur entre 4 et 26 (bornes incluses).");
        return;
      }
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
          if(navires[j-1][i]!=null){
            System.out.print(" " + navires[j-1][i].toString());
          }
          else{
            System.out.print(" " + ".");
          }
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
