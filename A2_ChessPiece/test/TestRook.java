import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This is the JUNit test for the rook class.
 */
public class TestRook {
  private ChessPiece blackPawn1;
  private ChessPiece blackBishop1;
  private ChessPiece blackRook1;
  private ChessPiece whitePawn1;
  private ChessPiece blackPawn2;
  private ChessPiece whiteRook1;
  private ChessPiece whiteKnight1;
  private ChessPiece whitePawn2;

  @Before
  public void setUp() {
    blackPawn1 = new Pawn(2,1, Color.BLACK);
    blackRook1 = new Rook(3,2, Color.BLACK);
    blackBishop1 = new Bishop(0,2,Color.BLACK);
    whitePawn1 = new Pawn(4,3, Color.WHITE);
    blackPawn2 = new Pawn(4,4, Color.BLACK);
    whiteKnight1 = new Knight(7,5,Color.WHITE);
    whiteRook1 = new Rook(3,5, Color.WHITE);
    whitePawn2 = new Pawn(2,6, Color.WHITE);
  }

  @Test
  public void testRookCanMove() {
    //testing moving diagonally in differing distances
    assertFalse(blackRook1.canMove(5,0));
    assertFalse(whiteRook1.canMove(5,7));
    assertFalse(blackRook1.canMove(0,5));
    assertFalse(whiteRook1.canMove(2,4));
    //testing moving outside of board like a rook
    assertFalse(blackRook1.canMove(8,2));
    assertFalse(whiteRook1.canMove(-1,5));
    assertFalse(whiteRook1.canMove(3,8));
    assertFalse(blackRook1.canMove(3,-1));
    //testing rook horizontal movements
    assertTrue(blackRook1.canMove(3,0));
    assertTrue(blackRook1.canMove(3,3));
    assertTrue(blackRook1.canMove(1,2));
    assertTrue(blackRook1.canMove(7,2));
    assertTrue(whiteRook1.canMove(0,5));
    assertTrue(whiteRook1.canMove(6,5));
    assertTrue(whiteRook1.canMove(3,4));
    assertTrue(whiteRook1.canMove(3,7));
  }

  @Test
  public void testCanKill() {
    //testing capturing same color
    assertFalse(blackRook1.canKill(blackBishop1));
    assertFalse(whiteRook1.canKill(whiteKnight1));
    ChessPiece whiteRook2 = new Rook(7,0,Color.WHITE);
    assertFalse(whiteRook2.canKill(whiteKnight1));
    ChessPiece blackRook2 = new Rook(0, 7, Color.BLACK);
    assertFalse(blackRook2.canKill(blackBishop1));
    // testing capturing valid opposing team
    assertTrue(blackRook1.canKill(whiteRook1));
    assertTrue(whiteRook1.canKill(blackRook1));
    ChessPiece whiteRook3 = new Rook(6,2, Color.WHITE);
    ChessPiece blackRook3 = new Rook(0,5,Color.BLACK);
    assertTrue(blackRook1.canKill(whiteRook3));
    assertTrue(whiteRook3.canKill(blackRook1));
    assertTrue(whiteRook1.canKill(blackRook3));
    assertTrue(blackRook3.canKill(whiteRook1));
    // testing diagonal capture
    assertFalse(blackRook1.canKill(whitePawn1));
    assertFalse(whiteRook1.canKill(blackPawn2));
    assertFalse(blackRook1.canKill(blackPawn1));
    assertFalse(whiteRook1.canKill(whitePawn2));
  }
}
