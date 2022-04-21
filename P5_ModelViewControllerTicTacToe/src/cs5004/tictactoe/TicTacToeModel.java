package cs5004.tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This represents a tictactoe game and contains all methods mandated by the TicTacToe interface.
 */
public class TicTacToeModel implements TicTacToe {
  Player[][] board; //2 dim array
  private Player currentPlayer;
  private final int boardSize = 3;

  /**
   * This constructs the tictactoe board and sets the first player to "x".
   */
  public TicTacToeModel() {
    this.board = new Player[boardSize][boardSize];//establish array for board
    this.currentPlayer = Player.X;
  }



  @Override
  public String toString() {
    return Arrays.stream(getBoard()).map(row -> " "
            + Arrays.stream(row).map( p -> p == null ? " "
            : p.toString()).collect(Collectors.joining(" | ")))
            .collect(Collectors.joining("\n-----------\n"));
  }

  @Override
  public void move(int r, int c) {
    if (r > 2 || r < 0 || c > 2 || c < 0) {
      throw new IllegalArgumentException("Row or column"
              + "outside boundaries");
    }
    boolean checkDone = this.isGameOver();
    if (checkDone) {
      throw new IllegalStateException("The game is over!"); }
    if (board[r][c] != null) {
      throw new IllegalArgumentException("Space is occupied");
    }
    board[r][c] = this.currentPlayer;//marks space as x or O

    if (this.currentPlayer == Player.X) {
      this.currentPlayer = Player.O;
    } else {
      this.currentPlayer = Player.X;
    }

  }

  /**
   * enums are passed by value. This will return current player.
   *
   * @return the player whose turn it is.
   */
  @Override
  public Player getTurn() {
    return this.currentPlayer;
  }

  @Override
  public boolean isGameOver() {
    if (this.isThreeInARow()) {
      return true;
    }
    if (this.isThreeInAColumn()) {
      return true;
    }
    if (this.diagonalWin1()) {
      return true;
    }
    if (this.diagonalWin2()) {
      return true;
    }
    return this.isBoardFull();
  }

  /**
   * This checks if there are 3 x's or o's in a row.
   *
   * @return a boolean dependent on if there are 3 in a row or not.
   */
  private boolean isThreeInARow() {
    for (int i = 0; i < boardSize; i++) {
      if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
        boolean check = board[i][0] != null; //if its not equal true it's true
        if (check) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This is a helper function that checks if there are 3 x's or o's in a column.
   * @return a boolean dependent on if there are 3 in a column or not.
   */
  private boolean isThreeInAColumn() {
    for (int i = 0; i < boardSize; i++) {
      if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
        boolean check =  board[0][i] != null;
        if (check) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This is a helper function that returns the player on the winning row.
   * @return the player who won if a row victory was reached.
   */
  private Player getWinningRow() {
    for (int i = 0; i < boardSize; i++) {
      if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
        int winningRow = i;
        if ( board[winningRow][0] != null ) {
          return board[winningRow][0];
        }
      }
    }
    return board[1][1];
  }

  /**
   * This is a helper function that returns the player on the winning column.
   * @return the player who won if a column victory was reached.
   */
  private Player getWinningCol() {
    for (int i = 0; i < boardSize; i++) {
      if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
        int winningCol = i;
        if (board[0][i] != null) {
          return board[0][winningCol];
        }
      }
    }
    return board[1][1];
  }

  /**
   * This is a helper function that returns the player on the winning diagonal.
   * @return the player who won if a left to right diagonal victory was reached.
   */
  private Player getWinningDiag1() {
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
      if (board[0][0] != null ) {
        return board[0][0];
      }
    }
    return board[1][1];
  }

  /**
   * This is a helper function that returns the player on the winning diagonal.
   * @return the player who won if a right to left diagonal victory was reached.
   */
  private Player getWinningDiag2() {
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
      if (board[0][2] != null) {
        return board[0][2];
      }
    }
    return board[1][1];
  }

  /**
   * This is a helper function that determines if a win by left to right diagonal
   * has occured.
   * @return a boolean dependent on if a left to right diagonal victory has been reached.
   */
  private boolean diagonalWin1() {
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
      return board[0][0] != null;
    }
    return false;
  }

  /**
   * This is a helper function that determines if a win by right to left diagonal
   * has occured.
   * @return a boolean dependent on if a right to left diagonal victory has been reached.
   */
  private boolean diagonalWin2() {
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
      return board[0][2] != null;
    }
    return false;
  }

  /**
   * This is a helper method that checks if the board is full.
   *
   * @return a boolean dependent on if the board is full - true if it is, false otherwise.
   */
  private boolean isBoardFull() {
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (board[i][j] == null) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * This is a helper function that determines how the game was won - win by row, column, etc.
   * @return the win condition as a string.
   */
  private String determineWinCondition() {
    if (this.isThreeInARow()) {
      return "Row";
    }
    if (this.isThreeInAColumn()) {
      return "Col";
    }
    if (this.diagonalWin1()) {
      return "diag1";
    }
    if (this.diagonalWin2()) {
      return "diag2";
    }
    return "";
  }

  @Override
  public Player getWinner() {
    if (this.determineWinCondition().equals("Row")) {
      return this.getWinningRow();
    } else if (this.determineWinCondition().equals("Col")) {
      return this.getWinningCol();
    } else if (this.determineWinCondition().equals("diag1")) {
      return this.getWinningDiag1();
    } else if (this.determineWinCondition().equals("diag2")) {
      return this.getWinningDiag2();
    }
    return null;
  }

  @Override
  public Player[][] getBoard() {
    Player[][] tempBoard;
    tempBoard = new Player[boardSize][boardSize];
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        tempBoard[i][j] = board[i][j];
      }
    }
    return tempBoard;
  }

  @Override
  public Player getMarkAt(int r, int c) {
    if (r > 2 || r < 0 || c > 2 || c < 0) {
      throw new IllegalArgumentException("Row or colum"
              + "outside boundaries");
    }
    return board[r][c];
  }
}


