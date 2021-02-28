package ensta;
import ships.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }



    public void test_getName()
    {
      Board board = new Board("Test", 5);
      String test = board.getNom();
      assertTrue(test.equals("Test"));
    }

    public void test_getSize()
    {
      Board board = new Board("Test", 6);
      int size = board.getSize();
      assertTrue(size == 6);
    }

    public void test_setHit()
    {
      Board board = new Board("Test", 6);
      board.setHit(true, 0, 0);
      assertTrue(board.frappes[0][0]);
      board.setHit(false, 0, 1);
      assertTrue(board.frappes[1][0] == false);
    }


    public void test_should_throw_exception()
    {
      try{
        Board board = new Board ("Test", 5);
        board.setHit(false, -1, 0);
        fail("Missing Exception");
      } catch(IndexOutOfBoundsException e){
        assertEquals("Les coordonnées entrées ne sont pas comprises entre 0 et 4 (bornes incluses).", e.getMessage());
      }
    }

    public void test_ShipState_Null(){
      ShipState test = new ShipState();
      assertEquals(test.toString(), ".");
      ShipState test2 = new ShipState(new Destroyer());
      assertEquals(test2.toString(), "D");
    }


    public void test_putShip_should_throw_exception()
    {
      Board board = new Board("Test");
      try{
        board.putShip(new Destroyer(), -1, 0);
        fail("Missing Exception");
      } catch(ArrayIndexOutOfBoundsException e){
        assertEquals("Les coordonnées entrées ne sont pas comprises entre 0 et 9 (bornes incluses).", e.getMessage());
      }
    }




}
