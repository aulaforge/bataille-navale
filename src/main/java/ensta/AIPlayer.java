package ensta;
import ships.*;
import java.io.Serializable;
import java.util.List;

public class AIPlayer extends Player {
    /* **
     * Attribut
     */
    private BattleShipsAI ai;

    /* **
     * Constructeur
     */
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    // TODO AIPlayer must not inherit "keyboard behavior" from player. Call ai instead.

    /**
    * fait simplement appel à la méthode putShips de la classe BattleShipsAI, le joueur représenté par cette classe étant une IA
    * @see BattleShipsAI
    */

    public void putShips(){
      ai.putShips(ships);
    }

    /**
    * fait simplement appel à la méthode sendHit de la classe BattleShipsAI, le joueur représenté par cette classe étant une IA
    * @see BattleShipsAI
    */

    public Hit sendHit(int[] coords){

      Hit res = ai.sendHit(coords);
      return res;
    }
}
