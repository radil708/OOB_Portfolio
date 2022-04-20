import org.junit.Test;

import cs5004.tictactoe.Player;
import cs5004.tictactoe.TicTacToe;
import cs5004.tictactoe.TicTacToeModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test cases for the tic tac toe model. Verifying that game state is properly managed, and
 * all game actions are properly validated.
 */
public class TicTacToeModelTest {

  private TicTacToe ttt1 = new TicTacToeModel();

  @Test
  public void testMove() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
  }

  @Test
  public void testWhatver() {
    Player[][] playerBoard = new Player[3][3];
    playerBoard[0][1] = Player.X;
    TicTacToeModel Tic = new TicTacToeModel();
    //Tic.board = playerBoard;
  }

  @Test
  public void testHorizontalWin() {
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(1, 0); // O takes middle left
    ttt1.move(0, 1); // X takes upper middle
    assertNull(ttt1.getWinner());
    ttt1.move(2, 0); // O takes lower left
    ttt1.move(0, 2); // X takes upper right
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.X, ttt1.getWinner());
    assertEquals(" X | X | X\n"
            + "-----------\n"
            + " O |   |  \n"
            + "-----------\n"
            + " O |   |  ", ttt1.toString());
  }

  @Test
  public void testDiagonalWin() {
    diagonalWinHelper();
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.O, ttt1.getWinner());
    assertEquals(" X | X | O\n"
            + "-----------\n"
            + " X | O |  \n"
            + "-----------\n"
            + " O |   |  ", ttt1.toString());
  }

  // set up situation where game is over, O wins on the diagonal, board is not full
  private void diagonalWinHelper() {
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(2, 0); // O takes lower left
    ttt1.move(1, 0); // X takes middle left
    assertNull(ttt1.getWinner());
    ttt1.move(1, 1); // O takes center
    ttt1.move(0, 1); // X takes upper middle
    ttt1.move(0, 2); // O takes upper right
  }

  @Test
  public void testInvalidMove() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
    assertEquals(Player.X, ttt1.getMarkAt(0, 0));
    try {
      ttt1.move(0, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      //assertEquals("Position occupied", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      ttt1.move(-1, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      //assertEquals("Position occupied", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testMoveAttemptAfterGameOver() {
    diagonalWinHelper();
    ttt1.move(2, 2); // 2,2 is an empty position
  }

  @Test
  public void testCatsGame() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
    ttt1.move(1, 1);
    assertEquals(Player.X, ttt1.getTurn());
    ttt1.move(0, 2);
    ttt1.move(0, 1);
    ttt1.move(2, 1);
    ttt1.move(1, 0);
    ttt1.move(1, 2);
    ttt1.move(2, 2);
    ttt1.move(2, 0);
    assertTrue(ttt1.isGameOver());
    assertNull(ttt1.getWinner());
    assertEquals( " X | O | X\n"
            + "-----------\n"
            + " O | O | X\n"
            + "-----------\n"
            + " X | X | O", ttt1.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMarkAtRow() {
    ttt1.getMarkAt(-12, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMarkAtCol() {
    ttt1.getMarkAt(0, -30);
  }

  @Test
  public void testGetBoard() {
    diagonalWinHelper();
    Player[][] bd = ttt1.getBoard();
    assertEquals(Player.X, bd[0][0]);
    assertEquals(Player.O, bd[1][1]);
    assertEquals(Player.X, bd[0][1]);

    // attempt to cheat by mutating board returned by getBoard()
    // check correct preconditions
    assertEquals(Player.O, bd[2][0]);
    assertEquals(Player.O, ttt1.getMarkAt(2, 0));
    bd[2][0] = Player.X;  // mutate
    // check correct post conditions
    assertEquals(Player.O, ttt1.getMarkAt(2, 0));
    Player[][] bd2 = ttt1.getBoard();
    assertEquals(Player.O, bd2[2][0]);
  }

  @Test
  public void testGameWinbyRow() {
    ttt1.move(1, 1);
    assertFalse(ttt1.isGameOver());
    ttt1.move(0, 0);
    ttt1.move(1, 0);
    assertNull(ttt1.getWinner());
    ttt1.move(0, 1);
    ttt1.move(1, 2);
    assertTrue(ttt1.isGameOver());
  }

  @Test
  public void testGameWinByCol() {
    ttt1.move(0,2);
    ttt1.move(0, 0);
    ttt1.move(1,2);
    ttt1.move(1,0);
    assertFalse(ttt1.isGameOver());
    ttt1.move(2,2);
    assertTrue(ttt1.isGameOver());
  }


}
