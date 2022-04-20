import static org.junit.Assert.assertEquals;

import cs5004.tictactoe.FailingAppendable;
import cs5004.tictactoe.TicTacToe;
import cs5004.tictactoe.TicTacToeConsoleController;
import cs5004.tictactoe.TicTacToeController;
import cs5004.tictactoe.TicTacToeModel;
import java.io.StringReader;
import java.util.Arrays;
import org.junit.Test;

/**
 * Test cases for the tic tac toe controller, using mocks for readable and appendable.
 */
public class TicTacToeControllerTest {

  // ADDITIONAL TEST CASES TO IMPLEMENT:
  // Play game to completion, where there is a winner - Done
  // Input where the q comes instead of an integer for the row - Done
  // Input where the q comes instead of an integer for the column - Done
  // Input where non-integer garbage comes instead of an integer for the row - Already done
  // Input where non-integer garbage comes instead of an integer for the column - Done
  // Input where the move is integers, but outside the bounds of the board - Done
  // Input where the move is integers, but invalid because the cell is occupied - Done
  // Multiple invalid moves in a row of various kinds
  // Input including valid moves interspersed with invalid moves, game is played to completion
  // What happens when the input ends "abruptly" -- no more input,
  // but not quit, and game not over? - what does this mean?
  // THIS IS NOT AN EXHAUSTIVE LIST

  @Test
  public void testSingleValidMove() {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    // gamelog appends all string / game states
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("2 2 q"), gameLog);
    c.playGame(m);
    assertEquals("   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for O:\n"
        + "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n", gameLog.toString());
  }

  @Test
  public void testGameToCompletion() {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("2 2 1 "
            + "1 2 3 1 2 2 1"), gameLog);
    c.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   | X |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + " O |   |  \n"
            + "-----------\n"
            + "   | X |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " O |   |  \n"
            + "-----------\n"
            + "   | X | X\n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + " O | O |  \n"
            + "-----------\n"
            + "   | X | X\n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " O | O |  \n"
            + "-----------\n"
            + " X | X | X\n"
            + "-----------\n"
            + "   |   |  \n"
            + "Game is over! X wins.\n", gameLog.toString());
  }

  @Test
  public void testRowInputQ() {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("q"), gameLog);
    c.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n", gameLog.toString());
    StringBuilder gameLog2 = new StringBuilder();
    TicTacToeController q = new TicTacToeConsoleController(new StringReader("2 2 q"), gameLog2);
    q.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   | X |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + "Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   | X |  \n"
            + "-----------\n"
            + "   |   |  \n" ,gameLog2.toString() );
  }

  @Test
  public void testQuitAsColumn() {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("q"), gameLog);
    c.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n", gameLog.toString());
    StringBuilder gameLog2 = new StringBuilder();
    TicTacToeController q = new TicTacToeConsoleController(new StringReader("3 3 q"), gameLog2);
    q.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   | X\n"
            + "Enter a move for O:\n"
            + "Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   | X\n" ,gameLog2.toString() );
  }

  @Test
  public void testRowOutOfBounds() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("4 2 3 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Invalid move 4,2\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   | X |  \n"
            + "Enter a move for O:\n"
            + "Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   | X |  \n" , gameLog.toString());
  }

  @Test
  public void testColOutOfBounds() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 2 3 5 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "   | X |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + "Invalid move 3,5\n"
            + "Game quit! Ending game state:\n"
            + "   | X |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n" , gameLog.toString());
  }

  @Test
  public void testFullGameWithErrors() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 3 100 2 2 2 1 -67 3 3 "
            + "32.56 3 3 -82.1 3 3 0 2 3 word 3 2 #21$ 2 3" );
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "   |   | X\n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + "Invalid move 100,2\n"
            + "   |   | X\n"
            + "-----------\n"
            + "   | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Invalid move 1,-67\n"
            + "   |   | X\n"
            + "-----------\n"
            + "   | O |  \n"
            + "-----------\n"
            + "   |   | X\n"
            + "Enter a move for O:\n"
            + "Invalid number 32.56\n"
            + "Invalid move 3,3\n"
            + "Invalid number -82.1\n"
            + "Invalid move 3,3\n"
            + "Invalid move 0,2\n"
            + "Invalid number word\n"
            + "   |   | X\n"
            + "-----------\n"
            + "   | O |  \n"
            + "-----------\n"
            + "   | O | X\n"
            + "Enter a move for X:\n"
            + "Invalid number #21$\n"
            + "   |   | X\n"
            + "-----------\n"
            + "   | O | X\n"
            + "-----------\n"
            + "   | O | X\n"
            + "Game is over! X wins.\n", gameLog.toString());
  }



  @Test
  public void testBogusInputAsRow() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("!#$ 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    // check that it's the correct number of lines
    assertEquals(13, lines.length);
    // check that the last 6 lines are correct
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ", lastMsg);
    // note no trailing \n here, because of the earlier split
  }

  @Test
  public void testBogusColumn() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 &$(*QSD q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Invalid number &$(*QSD\n"
            + "Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n", gameLog.toString());
  }


  @Test
  public void testTieGame() {
    TicTacToe m = new TicTacToeModel();
    // note the entire sequence of user inputs for the entire game is in this one string:
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(60, lines.length);
    assertEquals("Game is over! Tie game.", lines[lines.length - 1]);
  }

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }

}
