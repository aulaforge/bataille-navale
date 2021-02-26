package ships;
import ensta.*;
public class ShipState{

  /**
  * Attributs
  */

  protected AbstractShip navRef;
  protected boolean struck;

  /**
  *Constructeur valué
  */

  public ShipState(AbstractShip navRef){
    this.navRef=navRef;
    this.struck=false;
  }

  /**
  * constructeur par défaut
  */

  public ShipState(){
    this.struck=false;
  }

  /**
  * rend cet état du navire comme touché
  * @throws Exception si jamais la case représenté par cet instance a déjà été touchée
  */

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

  /**
  * @retun renvoie vrai (true) si jamais cet partie du navire a été touché, faux (false) sinon
  */

  public boolean isStruck(){
    return struck;
  }

  /**
  * @return renvoie vrai (true) si le bateau comportant cet état a coulé, faux (false) sinon
  */
  public boolean isSunk(){
    if(navRef!=null){
      return navRef.isSunk();
    }
    else{
      return false;
    }
  }


  /**
  * @return renvoie le label du navire représenté par cet état ("." si il n'y a pas de navire). Le label sera en rouge si cet état a été touché
  */
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


  /**
  * @return renvoie le navire comportant cet état
  */
  public AbstractShip getShip(){
    return navRef;
  }

}
