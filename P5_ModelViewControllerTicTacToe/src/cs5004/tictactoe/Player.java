package cs5004.tictactoe;

/**
 * This is an enum class that represents the player.
 */
public enum Player { X, O;


  @Override
  public String toString() {
    String str;
    if (this == Player.X) {
      str = "X";
      return str;
    }
    else {
      return "O";
    }
  }
}