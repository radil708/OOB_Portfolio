/**
 * This class represents a rook chess piece.
 */
public class Rook extends AbstractChessPiece {
  private final String ID = "rook";

  /**
   * This is the constructor for the rook class. It relies on the AbstractChessPiece super
   * constructor. The parameters are passed in to the constructor of the AbstractChessPiece class.
   * @param initRow the row to be passed into the super constructor as an int.
   * @param initCol the column to be passed to the super constructor as an int.
   * @param thisColor the color enum to be passed to the super constructor.
   */
  public Rook(int initRow, int initCol, Color thisColor) {
    super(initRow, initCol, thisColor);
  }

  @Override
  public boolean canMove(int newRow, int newCol) {
    // check if new position is outside board
    if (isBeyondBoard(newRow, newCol)) {
      return false;
    }

    int deltaX = Math.abs(newRow - this.getRow());
    int deltaY = Math.abs(newCol - this.getColumn());
    if (deltaX == 0) {
      return deltaY >= 0 && deltaY <= 7;
    }
    else if (deltaY == 0) {
      return (deltaX >= 0 && deltaX <= 7);
    }
    else {
      return false; }
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    if (isBeyondBoardOrSameColor(piece)) {
      return false;
    }
    int otherRow = piece.getRow();
    int otherCol = piece.getColumn();

    return canMove(otherRow, otherCol);

  }

  @Override
  public String toString() {
    String str;
    str = "A " + this.getColor().toString()  + " " + this.ID
            + " at row: " + this.getRow() + ", col: " + this.getColumn();
    return str;
  }
}

