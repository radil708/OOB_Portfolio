import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This is a JUnit tess for Pawn objects.
 */
public class TestPawn {
  private ChessPiece whitePawn1;
  private ChessPiece whitePawn2;
  private ChessPiece whitePawn3;
  private ChessPiece whitePawn4;
  private ChessPiece whitePawn6;
  private ChessPiece blackPawn1;
  private ChessPiece blackPawn2;
  private ChessPiece blackPawn3;
  private ChessPiece blackPawn4;
  private ChessPiece blackPawn5;
  private ChessPiece blackPawn6;

  @Before
  public void setUp() {
    whitePawn1 = new Pawn(1,1, Color.WHITE);
    whitePawn2 = new Pawn(1, 5, Color.WHITE);
    whitePawn3 = new Pawn(2,4,Color.WHITE);
    whitePawn4 = new Pawn(2,6,Color.WHITE);
    whitePawn6 = new Pawn(3,1, Color.WHITE);
    blackPawn1 = new Pawn(4,0,Color.BLACK);
    blackPawn2 = new Pawn(4,2,Color.BLACK);
    blackPawn3 = new Pawn(6,5,Color.BLACK);
    blackPawn4 = new Pawn(5,6,Color.BLACK);
    blackPawn5 = new Pawn(5,4,Color.BLACK);
    blackPawn6 = new Pawn(6,1,Color.BLACK);
  }

  @Test
  public void testConstructor() {
    assertEquals("A black pawn at row: 4, col: 0", blackPawn1.toString());
    assertEquals("A white pawn at row: 1, col: 1", whitePawn1.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIllegalPawnConstructor() {
    // Testing super constructor
    // Testing row or column beyond board size
    // Row below 0
    ChessPiece pawn1 = new Pawn(-1,1, Color.WHITE);
    // Column below 0
    ChessPiece pawn2 = new Pawn(1, -5, Color.BLACK);
    // Row beyond 7
    ChessPiece pawn3 = new Pawn(8, 7, Color.WHITE);
    // Column beyond 7
    ChessPiece pawn4 = new Pawn(7, 8, Color.BLACK);
  }

  @Test
  public void testCanMove() {
    // white pawn cannot move backward
    assertFalse(whitePawn1.canMove(0,1));
    // black pawn cannot move backward
    assertFalse(blackPawn1.canMove(5,0));
    // testing pawns moving sidewards
    assertFalse(whitePawn1.canMove(1,3));
    assertFalse(whitePawn1.canMove(1,1));
    assertFalse(blackPawn6.canMove(6,3));
    assertFalse(blackPawn6.canMove(6,1));
    // testing pawn moving diagonally
    assertFalse(whitePawn1.canMove(2,0));
    assertFalse(whitePawn1.canMove(2,3));
    assertFalse(whitePawn1.canMove(0,0));
    assertFalse(whitePawn1.canMove(0,3));
    assertFalse(blackPawn6.canMove(7,0));
    assertFalse(blackPawn6.canMove(7,3));
    assertFalse(blackPawn6.canMove(5,0));
    assertFalse(blackPawn6.canMove(5,3));
    // testing moving 2 spaces forward or backward
    assertFalse(whitePawn2.canMove(3,5));
    assertFalse(whitePawn3.canMove(0,4));
    assertFalse(blackPawn1.canMove(2,0));
    assertFalse(blackPawn1.canMove(6,0));
    // Testing moving forward by one space
    assertTrue(blackPawn3.canMove(5,5));
    assertTrue(whitePawn4.canMove(3,6));
  }

  @Test
  public void testPawnCanKill() {
    // testing pawn capturing piece directly in front of them
    ChessPiece whitePawn7 = new Pawn(4,6,Color.WHITE);
    assertFalse(blackPawn4.canKill(whitePawn7));
    ChessPiece blackPawn7 = new Pawn(3,6,Color.BLACK);
    assertFalse(whitePawn4.canKill(blackPawn7));
    // testing capturing chess piece on the same team/ same color
    ChessPiece blackPawn8 = new Pawn(4,6,Color.BLACK);
    assertFalse(blackPawn3.canKill(blackPawn5));
    assertFalse(blackPawn3.canKill(blackPawn4));
    assertFalse(whitePawn2.canKill(whitePawn3));
    assertFalse(whitePawn2.canKill(whitePawn4));
    // testing valid diagonal right capture
    assertTrue(whitePawn6.canKill(blackPawn2));
    ChessPiece whitePawn9 = new Pawn(3,3,Color.WHITE);
    assertTrue(blackPawn2.canKill(whitePawn9));
    // testing valid diagonal left
    assertTrue(whitePawn6.canKill(blackPawn1));
    assertTrue(blackPawn2.canKill(whitePawn6));


  }

}
