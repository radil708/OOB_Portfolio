/**
 * This class contains all methods that are mandated in all chess peiece
 * subtype.
 */
public interface ChessPiece {

  /**
   * This returns the current row position of a chess piece.
   * @return the row position of the chess piece as an integer.
   */
  public int getRow();

  /**
   * This returns the current column position of a chess piece.
   * @return the column position of the chess piece as an integer.
   */
  public int getColumn();

  /**
   * This returns the color value of the of a chess piece.
   * @return the color of a chess piece as a Color enum.
   */
  public Color getColor();

  /**
   * This determines if a chess piece can move to a new position.
   * @param newRow the row value of the new position as an integer.
   * @param newCol the column value of the new position as an integer.
   * @return true if the current chess piece can move the new position,
   *         false otherwise.
   */
  public boolean canMove(int newRow, int newCol);

  /**
   * This determines if the current chess piece can "capture"/"kill" another
   * chess piece object.
   * @param piece the chess piece that the current object is trying to capture.
   * @return true if the current chess piece object can capture piece.
   */
  public boolean canKill(ChessPiece piece);

}
