package ships;
public class Submarine extends AbstractShip{

  public Submarine(){
    super("Submarine",'S', 3, Orientation.EAST);
  }

  public Submarine(Orientation orientation){
    super("Submarine",'S', 3, orientation);
  }
}
