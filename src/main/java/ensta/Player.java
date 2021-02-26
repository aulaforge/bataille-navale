package ensta;
import ships.*;
import java.io.Serializable;
import java.util.List;

public class Player {
    /* **
     * Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    /* **
     * Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /* **
     * Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coodrinates.
     */
    public void putShips() {
        boolean done = false;
        int i = 0;
        boolean error= false;

        do {
            error=false;
            AbstractShip s = ships[i];
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getNom(), s.getLength());
            System.out.println(msg);
            InputHelper.ShipInput res = InputHelper.readShipInput();
            // TODO set ship orientation
            switch(res.orientation){
              case "n":
                s.setOrientation(AbstractShip.Orientation.NORTH);
                break;
              case "s":
                s.setOrientation(AbstractShip.Orientation.SOUTH);
                break;
              case "e":
                s.setOrientation(AbstractShip.Orientation.EAST);
                break;
              case "w":
                s.setOrientation(AbstractShip.Orientation.WEST);
                break;
            }
            // TODO put ship at given position
            try{
              board.putShip(s, res.x, res.y);
            } catch (Exception e){
              System.err.println(e.getMessage());
              error=true;
            }
            // TODO when ship placement successful
            if(!error){
              ++i;
              board.print();
            }
            done = i == 5;


        } while (!done);
    }


    /**
    * @param coords tableau de int de taille 2 qui comportera le x puis le y choisi par le joueur pour le tir
    * @return Hit renvoie le résultat de l'appel à sendHit appliqué à opponent board
    * @see Board
    */
    public Hit sendHit(int[] coords) {
        boolean done= false;
        Hit hit = null;
        boolean error=false;
        if (coords == null || coords.length < 2) {
            throw new IllegalArgumentException("must provide an initialized array of size 2");
        }

        do {
            error=false;
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            // TODO call sendHit on this.opponentBoard
            try{
              hit=opponentBoard.sendHit(hitInput.x, hitInput.y);
            } catch(Exception e){
              System.err.println(e.getMessage());
              error=true;
            }
            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            // return hit is obvious. But how to return coords at the same time ?

            if(!error){
              coords[0]=hitInput.x;
              coords[1]=hitInput.y;
              done=true;
            }
        } while (!done);

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}
