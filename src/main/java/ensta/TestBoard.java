package ensta;
import ships.*;
import java.util.*;


/**
* a servi de classe de test pour les premières méthodes et classes définies, concernant les grilles et les navires
*/

class TestBoard {


  public static void main(String[] args) {
    Board mytest=new Board("Test");
    Board ennemyTest=new Board("Ennemy test");

    Submarine submarine1=new Submarine();
    Submarine submarine2=new Submarine();
    Destroyer destroyer=new Destroyer();
    Battleship battleship=new Battleship();
    Carrier carrier=new Carrier();

    List<AbstractShip> testShips =new ArrayList<AbstractShip>();

    testShips.add(destroyer);
    testShips.add(submarine1);
    testShips.add(submarine2);
    testShips.add(battleship);
    testShips.add(carrier);
    Player player=new Player(mytest, ennemyTest,testShips);
    player.putShips();

    player.board.sendHit(6,6);
    player.board.sendHit(7,6);
    Hit res=player.board.sendHit(8,6);
    System.out.println(res);
    System.out.println(submarine2.isSunk());
    player.board.print();
  }
}
