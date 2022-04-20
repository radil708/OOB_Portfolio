/**
 * This represents a chess piece. It will be extended by all concrete
 * chess piece class.
 */
public abstract class AbstractChessPiece implements ChessPiece {
  private int row;
  private int col;
  private Color chessColor;

  /**
   * This makes an abstract chess piece at a particular row and position.
   * It will throw IllegalArgumentExceptions if the initRow and initCol
   * is beyond the board size (less than 0 or greater than 7 for row or column).
   * @param initRow the row of the abstract chess piece object as an int.
   * @param initCol the column of the abstract chess piece object as an int.
   * @param thisColor the color of the abstract chess piece object as a Color.
   */
  public AbstractChessPiece(int initRow, int initCol, Color thisColor) {
    // Throw error if row or column position is beyond the board
    if ( this.isBeyondBoard(initRow, initCol) ) {
      throw new IllegalArgumentException("Row value cannot be less than 0 or greater than 7");
    }
    else {
      this.row = initRow;
      this.col = initCol;
      this.chessColor = thisColor;
    }
  }

  /**
   * This methods checks if parameters passes in are beyond the size of the board.
   * @param passedRow the row value to analyze as an int.
   * @param passedCol the column value to analyze as an int.
   * @return true if the parameters are within the boundaries of the board.
   */
  public boolean isBeyondBoard(int passedRow, int passedCol ) {
    if ( passedRow < 0 || passedRow > 7 ) {
      return true;
    }
    else {
      return  passedCol < 0 || passedCol > 7; }
  }

  private boolean isSameColor(ChessPiece other) {
    return this.getColor() == other.getColor();
  }

  /**
   * This checks is the other object passed in is the same color as the current object and
   * if the other objects row and column are within the board.
   * @param other the ChessPiece object the current object is being compared to.
   * @return true if the two objects are the same color or if the row and column of the other
   *         object is beyond the board.
   */
  public boolean isBeyondBoardOrSameColor(ChessPiece other) {
    return ( isBeyondBoard(other.getRow(), other.getColumn()) || isSameColor(other) );
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getColumn() {
    return this.col;
  }

  @Override
  public Color getColor() {
    return this.chessColor;
  }


}



