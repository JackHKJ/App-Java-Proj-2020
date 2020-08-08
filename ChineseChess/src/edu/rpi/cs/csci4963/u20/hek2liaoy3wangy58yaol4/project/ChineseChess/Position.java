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
	/** indicator of whether this position is being taken*/
	private boolean hasPiece;
	/** the board */
	private Board board;
	//TODO: implement the relationship of this point and the chess board(not yet implemented)


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


	/** place the piece p on this (position) on the board b */
	public void placePiece(Piece p, Board b) {
		this.piece = p;
		this.board = b;
		int size = p.getLength();	
		board.add(piece); // add the chess piece to the [x][y] coordinate
		// set the position to the center of drawing by halving the unit size
		piece.setBounds(this.x - size/2, this.y - size/2, size, size);
		this.hasPiece = true;
		//this.board.validate();
	}

	/** getter of the piece on this position
	 * @return the piece if has one or null otherwise
	 */
	public Piece getPiece() {
		if(this.hasPiece) {
			return this.piece;
		}
		else {
			return null;
		}
	}

	/** remove the piece on this position if possible
	 * @param p piece to be removed
	 * @param b board that contains this piece
	 */
	public void removePiece(Piece p, Board b) {
		this.board = b;
		this.piece = p;
		//TODO: remove the board from the piece;
		//TODO: validate the board
		this.hasPiece = false;
	}



}
