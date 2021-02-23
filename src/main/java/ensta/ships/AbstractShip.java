package ships;
public abstract class AbstractShip{

  public static enum Orientation{NORTH, SOUTH, EAST, WEST}

  protected char label;
  protected String nom;
  protected int taille;
  protected Orientation orientation;


  public char getLabel(){
    return label;
  }

  public String getNom(){
    return nom;
  }

  public int getTaille(){
    return taille;
  }

  public Orientation getOrientation(){
    return orientation;
  }

  public void setOrientation(Orientation orientation){
    this.orientation=orientation;
  }

  public AbstractShip(String nom, char label, int taille, Orientation orientation){
    this.label=label;
    this.nom=nom;
    this.taille=taille;
    this.orientation=orientation;
  }

}
