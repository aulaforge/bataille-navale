package ships;
public class Destroyer extends AbstractShip{

  public Destroyer(){
    super("Destroyer",'D',2, Orientation.EAST);
  }

  public Destroyer(Orientation orientation){
    super("Destroyer",'D',2,orientation);
  }

}
