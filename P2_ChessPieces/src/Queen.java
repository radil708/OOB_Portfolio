/**
 * This represents a queen chess piece.
 */
public class Queen extends AbstractChessPiece {
  private final String ID = "queen";

  /**
   * This is the constructor for the queen class. It relies on the AbstractChessPiece super
   * constructor. The parameters are passed in to the constructor of the AbstractChessPiece class.
   * @param initRow the row to be passed into the super constructor as an int.
   * @param initCol the column to be passed to the super constructor as an int.
   * @param thisColor the color enum to be passed to the super constructor.
   */
  public Queen(int initRow, int initCol, Color thisColor) {
    super(initRow, initCol, thisColor);
  }

  @Override
  public boolean canMove(int newRow, int newCol) {
    if ( this.isBeyondBoard( newRow, newCol) ) {
      return false; }
    int deltaX = Math.abs(this.getRow() - newRow);
    int deltaY = Math.abs(this.getColumn() - newCol);
    if (deltaX == 0) {
      return deltaY >= 0 && deltaY <= 7;
    }
    else if (deltaY == 0) {
      return (deltaX >= 0 && deltaX <= 7);
    }
    else {
      return deltaX == deltaY; }
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
