package ensta;
import ships.*;
import java.util.*;

public class TestGame{

/**
* fait "dormir" le programme pour ms millisecondes
* @param ms le temps en millisecondes durant lequel le programme devrai "dormir"
*/

  private static void sleep(int ms) {
	 try {
	  Thread.sleep(ms);
	 } catch (InterruptedException e) {
     e.printStackTrace();
	 }
  }

  public static void main(String[] args) {
    Game game = new Game();
    game.init();
    game.run();
  }
}
