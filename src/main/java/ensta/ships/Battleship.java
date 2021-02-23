package ships;
public class Battleship extends AbstractShip{

  public Battleship(){
    super("Battleship",'B', 4, Orientation.EAST);
  }

  public Battleship(Orientation orientation){
    super("Battleship", 'B', 4, orientation);
  }
}
