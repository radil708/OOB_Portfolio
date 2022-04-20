package cs5004.tictactoe;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class is the controller for the TicTacToeModel. It takes input and creates output.
 */
public class TicTacToeConsoleController implements TicTacToeController {
  private final Readable in;
  private Appendable out;

  /**
   * This constructor creates a TicTacToe controller.
   * @param in1 is the input parameter as a readable class. This is so that
   *            it can take System.in inputs. This class will throw an error
   *            if in1 is null. This parameter is of the type "Readable".
   * @param out1 is the output as an appendable class. This is so that it
   *             can take System.out data. Appendable, auto prints info to the terminal.
   */
  public TicTacToeConsoleController(Readable in1, Appendable out1) {
    if (in1 == null || out1 == null ) { // Throw an error here if the parameters are null.
      throw new IllegalArgumentException("Cannot be null"); }
    this.in = in1;
    this.out = out1;
  }

  @Override
  public void playGame(TicTacToe m) {
    if (m == null) {
      throw new IllegalArgumentException("TicTacToe object cannot be null;");
    }
    // Making a new scanner object to look at the input.
    Scanner scan = new Scanner(in);
    //This outer try block prints state of board.
    try {
      // At the beginning of every round the board should be displayed
      out.append(m.toString()).append("\n");
      // After displaying board, prompt the player for a move for the current player
      out.append("Enter a move for " + m.getTurn().toString()).append(":\n");
      // Here we declare the row and column inputs as variables.
      // We need to declare them as Integer class and NOT integer primitive type
      // because we need to be able to use parseInt and integers are required to have a value.
      // i.e. integers cannot be null but we cannot give them a default value to be.
      Integer rowInput = null;
      Integer columnInput = null;
      // We declare a token as string because it will be passed to the the scanner object.
      String token = "";
      // We need this while loop to run while game is not over.
      while (!m.isGameOver()) {
        //scan.next() assigns value of input to the token variable
        // scanner.next() can never return an empty string or null or only whitespaces.
        // Here we ask for actual user input and the value gets assigned to token.
        token = scan.next(); // This will handle every piece of input.
        // Here is the case if the player wants to quit the game.
        if (token.equalsIgnoreCase("q")) {
          break; // End the loop and print out end of the game + game state here.
        } // Here we need to assign the inputs to variables we can use.
        try { //We convert string input from scan.next() into usable data i.e. integers.
          int var = Integer.parseInt(token); //Parsing token and converting it to an integer.
          // Here we want to make sure any first input, as long as it is valid is set
          // as the row input variable.
          if (rowInput == null) {
            rowInput = var;
          } else {
            // Here we assign any next valid input as the column variable.
            columnInput = var;
            // Call the move method, but we need to subtract each variable by 1 as our valid
            // values are 1 to 3, but the move method's valid values are 0 to 2 because of
            //array positioning.
            m.move((rowInput - 1), (columnInput - 1));
            // After the valid inputs have been entered, the controller makes a output.
            // First it checks if the game is over and then makes the corresponding output.
            if (m.isGameOver()) { // Here the game is over
              Player z = m.getWinner();
              out.append(m.toString()).append("\n");
              out.append("Game is over! ");
              if (z != null) { // Here the game is over but there is no winner.
                out.append(m.getWinner().toString()).append(" wins.\n");
              } else {
                out.append("Tie game.\n");
              }
              break; // We need to break the loop because the model knows the game is over
              // but the controller does not until here.
            }
            // Here the game is not over so it needs to continue updating the output
            // i.e. make the current game state and ask for input for the current player.
            out.append(m.toString()).append("\n");
            out.append("Enter a move for " + m.getTurn().toString()).append(":\n");
            rowInput = columnInput = null; //We need to reset the values to accept new valid values.
          } // End of Try portion of try block.
        }
        catch (NumberFormatException e) {
          // this is what catches numerical input that cannot be converted to integers
          // like floats.
          out.append("Invalid number " + token + "\n");
          rowInput = columnInput = null; // Reset input values
        }
        catch (IllegalArgumentException e) {
          // This catches any non-numerical inputs.
          out.append("Invalid move " + rowInput + "," + columnInput + "\n");
          rowInput = columnInput = null; // Reset input values
        }

      }
      //The code below occurs outside of the while loop.
      // This case happens when "q" is the input. The game is not yet over. i.e. there are still
      // slots that can be filled by x or o
      if (!m.isGameOver() && token.equalsIgnoreCase("q")) {
        out.append(("Game quit! Ending game state:") + "\n" + m.toString() + "\n");
      } // Here this the case
      else if (!m.isGameOver()) {
        throw new IllegalStateException("No more inputs.");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Failure to append.");
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("Failure to read from readable");
    }
    scan.close();

  }
}
