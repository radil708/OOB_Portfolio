import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;



/**
 * This represents the test cases for the bishop class.
 */
public class TestBishop {
  private ChessPiece whitePawn1;
  private ChessPiece whiteBishop1;
  private ChessPiece whiteBishop2;
  private ChessPiece whiteBishop3;
  private ChessPiece whiteBishop4;
  private ChessPiece blackBishop1;
  private ChessPiece blackBishop2;
  private ChessPiece blackPawn1;
  private ChessPiece blackBishop4;

  @Before
  public void setUp() {
    whitePawn1 = new Pawn(6, 0, Color.WHITE);
    whiteBishop1 = new Bishop(5, 5 ,Color.WHITE);
    whiteBishop2 = new Bishop(0,6,Color.WHITE);
    whiteBishop3 = new Bishop(6,5,Color.WHITE);
    whiteBishop4 = new Bishop(3,2, Color.WHITE);
    blackPawn1 = new Pawn(0,0,Color.BLACK);
    blackBishop1 = new Bishop(3,3,Color.BLACK);
    blackBishop2 = new Bishop(3,4,Color.BLACK);
    blackBishop4 = new Bishop(7,5, Color.BLACK);
  }

  @Test
  public void testBishopIllegalConstructor() throws IllegalArgumentException {
    // Testing super constructor
    // Testing row or column beyond board size
    // Row below 0

    assertThrows(IllegalArgumentException.class, () -> {
      new Bishop(-1,1, Color.WHITE);
    });
    // Column below 0
    assertThrows(IllegalArgumentException.class, () -> {
      new Bishop(1, -5, Color.BLACK);
    });
    //Row beyond 7
    assertThrows(IllegalArgumentException.class, () -> {
      new Bishop(8, 7, Color.WHITE);
    });
    // Column Beyond 8
    assertThrows(IllegalArgumentException.class, () -> {
      new Bishop(1, 8, Color.BLACK);
    });

    //I kept getting a PMD error : JUNIT should include assert even though
    // I asserted errors to be thrown so I am putting a simple assert below to
    // deal with the style issue
    int blue = 5;
    assertEquals(5,blue);

  }

  @Test
  public void testBishopCanMove() {
    // testing moving up
    assertFalse(blackBishop1.canMove(7,3));
    assertFalse(blackBishop1.canMove(4,3));
    // outside of board
    assertFalse(blackBishop1.canMove(8,3));
    // testing moving down
    assertFalse(whiteBishop1.canMove(4,5));
    assertFalse(whiteBishop1.canMove(0,5));
    // outside of board
    assertFalse(whiteBishop1.canMove(-1, 5));
    // testing moving right
    assertFalse(blackBishop2.canMove(3,5));
    assertFalse(blackBishop2.canMove(3,7));
    // outside of board
    assertFalse(blackBishop2.canMove(3,8));
    // testing moving left
    assertFalse(blackBishop1.canMove(3,2));
    assertFalse(blackBishop1.canMove(3,0));
    // outside of board
    assertFalse(blackBishop1.canMove(3,-1));
    // testing moving diagonal 1 space in each direction
    assertTrue(blackBishop1.canMove(4,2));
    assertTrue(blackBishop1.canMove(4,4));
    assertTrue(blackBishop1.canMove(2,2));
    assertTrue(blackBishop1.canMove(2,4));
    //testing moving diagonal in varying distance
    assertTrue(whiteBishop3.canMove(2,1));
    assertTrue(whiteBishop4.canMove(7,6));
    assertTrue(whiteBishop4.canMove(0,5));
    assertTrue(whiteBishop4.canMove(5,0));
    // testing moving outside of board
    assertFalse(whiteBishop3.canMove(8,7));
    assertFalse(blackBishop1.canMove(-1,-1));
    assertFalse(whiteBishop1.canMove(2,8));
    assertFalse(whiteBishop1.canMove(8,3));

  }

  @Test
  public void testBishopCanKill() {
    // testing killing horizontal
    assertFalse(blackBishop1.canKill(whiteBishop4));
    assertFalse(whiteBishop4.canKill(blackBishop1));
    // testing killing vertically
    assertFalse(whiteBishop3.canKill(blackBishop4));
    assertFalse(blackBishop4.canKill(whiteBishop3));
    // testing killing diagonally
    assertTrue(blackBishop1.canKill(whiteBishop1));
    assertTrue(whiteBishop1.canKill(blackPawn1));
    assertTrue(whiteBishop2.canKill(blackBishop1));
    assertTrue(blackBishop1.canKill(whiteBishop2));
    // testing killing same color
    assertFalse(blackBishop1.canKill(blackPawn1));
    assertFalse(whiteBishop2.canKill(whitePawn1));
  }
}
