/**
 * This class represents a bishop chess piece.
 */
public class Bishop extends AbstractChessPiece {
  private final String ID = "bishop";

  /**
   * This is the constructor for the bishop class. It relies on the AbstractChessPiece super
   * constructor. The parameters are passed in to the constructor of the AbstractChessPiece class.
   * @param initRow the row to be passed into the super constructor as an int.
   * @param initCol the column to be passed to the super constructor as an int.
   * @param thisColor the color enum to be passed to the super constructor.
   */
  public Bishop(int initRow, int initCol, Color thisColor) {
    super(initRow, initCol, thisColor);
  }

  @Override
  public boolean canMove(int newRow, int newCol) {
    // check if new position is outside board
    if ( isBeyondBoard(newRow, newCol) ) {
      return false;
    }
    int deltaX = Math.abs(this.getRow() - newRow);
    int deltaY = Math.abs(this.getColumn() - newCol);
    // bishops only move diagonally i.e. |change is x = change in| y
    return deltaX == deltaY;
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    // check if peice to kill is same color or if piece is beyond board
    if ( isBeyondBoardOrSameColor(piece) ) {
      return false;
    }
    return this.canMove(piece.getRow(), piece.getColumn());
  }

  @Override
  public String toString() {
    String str;
    str = "A " + this.getColor().toString()  + " " + this.ID
            + " at row: " + this.getRow() + ", col: " + this.getColumn();
    return str;
  }
}
