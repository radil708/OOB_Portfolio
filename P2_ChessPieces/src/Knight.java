/**
 * This represents a knight chess piece.
 */
public class Knight extends AbstractChessPiece {
  private final String ID = "knight";

  /**
   * This is the constructor for the knight class. It relies on the AbstractChessPiece super
   * constructor. The parameters are passed in to the constructor of the AbstractChessPiece class.
   * @param initRow the row to be passed into the super constructor as an int.
   * @param initCol the column to be passed to the super constructor as an int.
   * @param thisColor the color enum to be passed to the super constructor.
   */
  public Knight(int initRow, int initCol, Color thisColor) {
    super(initRow, initCol, thisColor);
  }

  @Override
  public boolean canMove(int newRow, int newCol) {
    if (this.isBeyondBoard(newRow, newCol)) {
      return false;
    }
    int deltaX = Math.abs(this.getRow() - newRow);
    int deltaY = Math.abs(this.getColumn() - newCol);
    if (deltaX == 2 && deltaY == 1) {
      return true;
    }
    else {
      return deltaX == 1 && deltaY == 2;
    }
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    if (isBeyondBoardOrSameColor(piece)) {
      return false;
    }
    int otherRow = piece.getRow();
    int otherCol = piece.getColumn();

    return this.canMove(otherRow, otherCol);

  }

  @Override
  public String toString() {
    String str;
    str = "A " + this.getColor().toString()  + " " + this.ID
            + " at row: " + this.getRow() + ", col: " + this.getColumn();
    return str;
  }
}
