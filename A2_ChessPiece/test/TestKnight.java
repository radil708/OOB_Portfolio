import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This represents the test cases for the knight class.
 */
public class TestKnight {
  private ChessPiece whiteKnight1;
  private ChessPiece whiteKnight2;
  private ChessPiece blackKnight1;
  private ChessPiece blackBishop1;

  @Before
  public void setUp() {
    whiteKnight1 = new Knight(4,3, Color.WHITE);
    whiteKnight2 = new Knight(0,3, Color.WHITE);
    blackKnight1 = new Knight(2,2,Color.BLACK);
    blackBishop1 = new Bishop(0,1, Color.BLACK);
  }

  @Test
  public void testKnightMove() {
    //testing horizontal and vertical movements
    assertFalse(blackKnight1.canMove(4,2));
    assertFalse(blackKnight1.canMove(0,2));
    assertFalse(whiteKnight1.canMove(4,1));
    assertFalse(whiteKnight1.canMove(4,5));
    // testing more invalid cases
    assertFalse(whiteKnight1.canMove(6,5));
    assertFalse(blackKnight1.canMove(0,4));
    assertFalse(whiteKnight1.canMove(2,1));
    assertFalse(whiteKnight1.canMove(6,1));
    assertFalse(whiteKnight1.canMove(6,1));
    assertFalse(blackKnight1.canMove(0,0));
    assertFalse(blackKnight1.canMove(0,4));
    assertFalse(whiteKnight1.canMove(6,5));
    // testing can move outside of board
    assertFalse(whiteKnight2.canMove(-2,4));
    assertFalse(whiteKnight1.canMove(-1,5));
    // testing valid cases
    assertTrue(blackKnight1.canMove(4,1));
    assertTrue(blackKnight1.canMove(1,0));
    assertTrue(whiteKnight1.canMove(2,4));
    assertTrue(whiteKnight1.canMove(5,5));
    assertTrue(whiteKnight1.canMove(6,4));
    assertTrue(blackKnight1.canMove(1,4));
    assertTrue(blackKnight1.canMove(0,1));
    assertTrue(blackKnight1.canMove(3,0));
  }

  @Test
  public void testKnightCanKill() {
    // test same color
    assertFalse(blackKnight1.canKill(blackBishop1));
    // test valid kills
    assertTrue(whiteKnight2.canKill(blackKnight1));
    assertTrue(blackKnight1.canKill(whiteKnight2));
    assertTrue(blackKnight1.canKill(whiteKnight1));
    ChessPiece blackKnight2 = new Knight(5,5,Color.BLACK);
    assertTrue(whiteKnight1.canKill(blackKnight2));
    assertTrue(blackKnight2.canKill(whiteKnight1));
  }
}
