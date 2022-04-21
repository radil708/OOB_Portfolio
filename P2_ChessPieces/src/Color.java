/**
 * This represents the color of a chess piece.
 * The color can only be black or white.
 */
public enum Color {
  BLACK, WHITE;

  /**
   * A method to make a string that represents the color of the enum object.
   * @return the color of the enum object as a string.
   */
  public String toString() {
    if (this == BLACK) {
      return "black";
    } else {
      return "white";
    }
  }
}


