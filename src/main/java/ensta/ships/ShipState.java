package ships;
import ensta.*;
public class ShipState{

  protected AbstractShip navRef;
  protected boolean struck;

  public ShipState(AbstractShip navRef){
    this.navRef=navRef;
    this.struck=false;
  }

  public ShipState(){
    this.struck=false;
  }


  public void addStrike() throws Exception{
    if (!struck){
      struck=true;
      if (navRef!=null){
        navRef.addStrike();
      }
    }
    else{
      throw new Exception("La case a déjà été visée");
    }
  }

  public boolean isStruck(){
    return struck;
  }

  public boolean isSunk(){
    if(navRef!=null){
      return navRef.isSunk();
    }
    else{
      return false;
    }
  }

  public String toString(){
    if(navRef==null){
      return ".";
    }
    else{
      if(struck){
        return String.valueOf(ColorUtil.colorize(navRef.getLabel(), ColorUtil.Color.RED));
      }
      else{
        return String.valueOf(navRef.getLabel());
      }
    }
  }

  public AbstractShip getShip(){
    return navRef;
  }

}
