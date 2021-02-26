package ships;
public abstract class AbstractShip{

  public static enum Orientation{NORTH, SOUTH, EAST, WEST}

/**
*Attributs de la classe
*/

  protected char label;
  protected String nom;
  protected int taille;
  protected Orientation orientation;
  protected int strikeCount;


/**
* incrémente de 1 l'attribut strikeCount si le navire peut toujours être touché et n'a pas été touché en tous ses points
*/

  public void addStrike(){
    if (strikeCount<taille){
      strikeCount++;
    }
  }

/**
*@return renvoie vrai (true) si le navire est coulé , faux (false) si le navire n'est pas coulé
*/

  public boolean isSunk(){
    return strikeCount==taille;
  }

/**
* @return renvoie le label du navire
*/

  public char getLabel(){
    return label;
  }

/**
* @return renvoie le nom complet du navire
*/

  public String getNom(){
    return nom;
  }

/**
* @returns renvoie la taille du navire
*/

  public int getLength(){
    return taille;
  }

/**
* @return renvoie l'orientation du navire
*/

  public Orientation getOrientation(){
    return orientation;
  }

/**
* @param orientation est l'orientation qu'on souhaite donner au navire
*/

  public void setOrientation(Orientation orientation){
    this.orientation=orientation;
  }

/**
*Constructeur public
*/

  public AbstractShip(String nom, char label, int taille, Orientation orientation){
    this.label=label;
    this.nom=nom;
    this.taille=taille;
    this.orientation=orientation;
    this.strikeCount=0;
  }

}
