package ships;
import ensta.*;
public class ShipState{

  protected AbstractShip navRef;
  protected boolean struck;

  public ShipState(AbstractShip navRef){
    this.navRef=navRef;
    this.struck=false;
  }


  public void addStrike(){
    if (!struck){
      struck=true;
      navRef.addStrike();
    }
  }

  public boolean isStruck(){
    return struck;
  }

  public boolean isSunk(){
    return navRef.isSunk();
  }

  public String toString(){
    if(struck){
      return String.valueOf(ColorUtil.colorize(navRef.getLabel(), ColorUtil.Color.RED));
    }
    else{
      return String.valueOf(navRef.getLabel());
    }
  }

  public AbstractShip getShip(){
    return navRef;
  }

}
