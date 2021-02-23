package ships;
public class Carrier extends AbstractShip{

  public Carrier(){
    super("Aircraft-Carrier",'C', 5, Orientation.EAST);
  }

  public Carrier(Orientation orientation){
    super("Aircraft-Carrier",'C', 5, orientation);
  }
}
