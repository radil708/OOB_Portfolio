/**
 * This represents the pawn chess piece.
 */
public class Pawn extends AbstractChessPiece {
  private final String ID = "pawn";

  /**
   * This is the constructor for the pawn class. It relies on the AbstractChessPiece super
   * constructor. The parameters are passed in to the constructor of the AbstractChessPiece class.
   * @param initRow the row to be passed into the super constructor as an int.
   * @param initCol the column to be passed to the super constructor as an int.
   * @param thisColor the color enum to be passed to the super constructor.
   */
  public Pawn(int initRow, int initCol, Color thisColor) {
    super(initRow, initCol, thisColor);
  }

  @Override
  public boolean canMove(int newRow, int newCol) {
    // Will throw an error if new positions are beyond the board
    // or moving horizontally in anyway
    if (!(this.pawnColorBlindCanMoveChecker(newRow, newCol))) {
      return false; }

    // Case for white pawn
    if  ( this.getColor() == Color.WHITE ) {
      // Will only return true if the pawn moves vertically upward by one space.
      return ( ( newRow == this.getRow() + 1 ) && ( this.getColumn() == newCol ) );
    }
    // Case for black pawn
    else if ( this.getColor() == Color.BLACK ) {
      // Will only return true if the pawn move vertically downward by one space.
      return ( ( newRow == this.getRow() - 1 ) && ( this.getColumn() == newCol ) );
    }
    else {
      return false;
    }

  }

  /**
   * This is a helper function for the canMoveMethod. It will return false if the newRow and newCol
   * are values beyond the board. i.e. ( less than 0 or greater than 7 ) or if the if the
   * pawn is moving horizontally i.e. change in col value.
   * @param newRow the row value of the new position as an int.
   * @param newCol the col value of the new position as an int.
   * @return true if the new position as defined by the parameters is within the board and is a
   *         vertical move.
   */
  private boolean pawnColorBlindCanMoveChecker(int newRow, int newCol) {
    if ( this.isBeyondBoard(newRow, newCol ) ) {
      return false;
    }
    else {
      return this.getColumn() == newCol; }
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    // Check if the chess pieces are the same color
    if (piece.getColor() == this.getColor()) {
      return false;
    }

    int otherRow = piece.getRow();
    int otherCol = piece.getColumn();

    // For white chess pieces, they can only move upward vertically.
    if ( this.getColor() == Color.WHITE ) {
      return (otherRow == this.getRow() + 1) && (otherCol == this.getColumn() + 1
              || otherCol == this.getColumn() - 1);
    }
    // For black chess pieces, they can only move downward.
    else if (this.getColor() == Color.BLACK) {
      return ( (otherRow == this.getRow() - 1) && (otherCol == this.getColumn() + 1
              || otherCol == this.getColumn() - 1 ) );
    }
    return false;
  }

  @Override
  public String toString() {
    String str;
    str = "A " + this.getColor().toString()  + " " + this.ID
            + " at row: " + this.getRow() + ", col: " + this.getColumn();
    return str;
  }
}