package ensta;
import ships.*;
import java.util.*;

public class TestGame{


  private static void sleep(int ms) {
	 try {
	  Thread.sleep(ms);
	 } catch (InterruptedException e) {
     e.printStackTrace();
	 }
  }

  public static void main(String[] args) {
    Board board=new Board("board");
    //Board player2=new Board("P2");
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

    AbstractShip[] testShipsArray =new AbstractShip[testShips.size()];
    testShips.toArray(testShipsArray);
    BattleShipsAI ai = new BattleShipsAI(board, board);
    int destructCount=0;
    Hit result;
    int[] coords={0,0};
    ai.putShips(testShipsArray);
    while(destructCount<5){
      result=ai.sendHit(coords);
      int affichCoordY=coords[1]+1;
      System.out.println("Coordonnées du tir : " + Character.toUpperCase((char)(coords[0]+97)) + affichCoordY);

      System.out.println("Statut du tir : " +result.toString());
      sleep(1000);
      if(result!=Hit.MISS&&result!=Hit.STRUCK){
        destructCount++;
      }
      board.print();

    }
  }
}
