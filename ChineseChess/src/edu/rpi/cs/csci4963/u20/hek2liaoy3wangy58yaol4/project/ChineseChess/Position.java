package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

/** this class implements a "position" on the Chinese chess board that allows the user to
 * place a piece on that
 * @version JRE 1.8.0_231
 */
public class Position {
	/** the x coordinate of the position */
	private int x;
	/** the y coordinate of the position */
	private int y;
	/** the piece representation on this position */
	private Piece piece;
	//TODO: implement the relationship of this point and the chess board(not yet implemented)
	/** indicator of whether this position is being taken*/
	private boolean hasPiece;
	
	/** the constructor of a position, which initialize the position without a piece 
	 * @param x_coord the x coordinate of the position
	 * @param y_coord the y coordinate of the position
	 */
	public Position(int x_coord, int y_coord) {
		this.x = x_coord;
		this.y = y_coord;
		this.hasPiece = false;
	}
	
	/** getter of whether this position is taken
	 * @return true if this position is taken, false otherwise
	 */
	public boolean hasPiece() {
		return this.hasPiece;
	}
	
	/** getter of x_coordinate
	 * @return x the x coordinate
	 */
	public int getX() {
		return this.x;
	}
	
	/** getter of y_coordinate
	 * @return y the y coordinate
	 */
	public int getY() {
		return this.y;
	}
	
	/** setter of the hasPiece boolean
	 * @param tf the boolean to be loaded
	 */
	public void setHasPiece(boolean tf) {
		this.hasPiece = tf;
	}
	
	
	//TODO: placePiece, interaction with the board
	
	

}
