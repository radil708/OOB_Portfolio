import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This is a JUnit test for the queen class.
 */
public class TestQueen {
  private ChessPiece blackRook1;
  private ChessPiece whiteKnight1;
  private ChessPiece blackQueen1;
  private ChessPiece whiteQueen1;
  private ChessPiece whitePawn1;
  private ChessPiece blackKnight1;
  private ChessPiece blackPawn1;
  private ChessPiece blackBishop1;
  private ChessPiece whiteBishop1;

  @Before
  public void setUp() {
    blackRook1 = new Rook(2,0,Color.BLACK);
    whiteKnight1 = new Knight(4,1,Color.WHITE);
    blackQueen1 = new Queen(6,2, Color.BLACK);
    whiteQueen1 = new Queen(2,2, Color.WHITE);
    whitePawn1 = new Pawn(7,3,Color.WHITE);
    blackKnight1 = new Knight(4,3, Color.BLACK);
    blackPawn1 = new Pawn(0,4, Color.BLACK);
    blackBishop1 = new Bishop(6,6,Color.BLACK);
    whiteBishop1 = new Bishop(2,6,Color.WHITE);
  }

  @Test
  public void testCanMove() {
    //testing moving outside of board
    assertFalse(blackQueen1.canMove(8,2));
    assertFalse(blackQueen1.canMove(6,8));
    assertFalse(blackQueen1.canMove(0,8));
    assertFalse(whiteQueen1.canMove(-1, 2));
    assertFalse(whiteQueen1.canMove(2,-1));
    assertFalse(whiteQueen1.canMove(-1,5));
    // testing moving to invalid spaces in board
    assertFalse(whiteQueen1.canMove(0,1));
    assertFalse(whiteQueen1.canMove(0,3));
    assertFalse(blackQueen1.canMove(5,4));
    assertFalse(blackQueen1.canMove(5,0));
    assertFalse(blackQueen1.canMove(7,0));
    assertFalse(blackQueen1.canMove(7,4));
    assertFalse(whiteQueen1.canMove(4,1));
    assertFalse(whiteQueen1.canMove(4,3));
    // testing valid moves
    assertTrue(blackQueen1.canMove(6,7));
    assertTrue(blackQueen1.canMove(1,7));
    assertTrue(blackQueen1.canMove(0,2));
    assertTrue(whiteQueen1.canMove(7,7));
    assertTrue(whiteQueen1.canMove(0,2));
    assertTrue(whiteQueen1.canMove(0,0));
    assertTrue(whiteQueen1.canMove(4,0));
    assertTrue(whiteQueen1.canMove(7,2));
  }

  @Test
  public void testCanKill() {
    // test capturing same color
    assertFalse(blackQueen1.canKill(blackBishop1));
    ChessPiece blackPawn2 = new Pawn(7,1, Color.BLACK);
    assertFalse(blackQueen1.canKill(blackPawn1));
    ChessPiece whiteRook2 = new Rook(0,2, Color.WHITE);
    assertFalse(whiteQueen1.canKill(whiteRook2));
    // test capturing different pieces
    assertTrue(blackQueen1.canKill(whitePawn1));
    assertTrue(blackQueen1.canKill(whiteBishop1));
    assertTrue(blackQueen1.canKill(whiteQueen1));
    assertTrue(whiteQueen1.canKill(blackQueen1));
    assertTrue(whiteQueen1.canKill(blackRook1));
    ChessPiece blackKnight2 = new Knight(4,0, Color.BLACK);
    assertTrue(whiteQueen1.canKill(blackKnight2));
    // test capturing pieces not in range
    assertFalse(blackQueen1.canKill(whiteKnight1));
    assertFalse(whiteQueen1.canKill(blackKnight1));

  }
}
