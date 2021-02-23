package ensta;
import ships.*;


class TestBoard {
  public static void main(String[] args) {
    Board test=new Board("Test");
    Battleship battleship=new Battleship();
    test.putShip(battleship, 4, 4);
    test.print();
  }
}
