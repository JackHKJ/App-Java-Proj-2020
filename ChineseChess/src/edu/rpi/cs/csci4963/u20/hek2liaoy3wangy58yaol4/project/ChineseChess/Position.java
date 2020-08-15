package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import java.io.Serializable;

/** this class implements a "position" on the Chinese chess board that allows the user to
 * place a piece on that
 * @version JRE 1.8.0_231
 */
public class Position implements Serializable {
	/** the x coordinate of the position */
	private int x; //
	/** the y coordinate of the position */
	private int y; //
	/** the length of the x times the scalar for locating */
	private int x_len; //
	/** the length of the y times the scalar for locating */
	private int y_len; //
	/** the piece representation on this position */
	private Piece piece;
	/** indicator of whether this position is being taken*/
	private boolean hasPiece;
	/** the board */
	private Board board; //
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

	/** get the len of the x axis
	 * @return the x axis length
	 */
	public int getXLen() {
		return x_len;
	}

	/** get the len of the y axis
	 * @return the y axis length
	 */
	public int getYLen() {
		return y_len;
	}

	public void setBoard(Board b) {
		this.board = b;
		int len = board.getHeight()/10;
		int wid = board.getWidth()/9;
		this.x_len = this.x * len - len/2;
		this.y_len = this.y * wid - wid/2;
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
		this.hasPiece = true;
		int len = board.getHeight()/10;
		int wid = board.getWidth()/9;
		this.x_len = this.x * len - len /2;
		this.y_len = this.y * wid - wid /2;
		this.piece.setLength(len);
		this.piece.setWidth(wid);;
		this.board.add(this.piece); // add the chess piece to the [x][y] coordinate
		// set the position to the center of drawing by halving the unit size
		piece.setBounds(this.x * wid - wid, this.y*len - len, wid, len);
		//this.board.validate();
	}

	/** place the piece p on this (position) on the board b */
	public void scaleBoardPosition() {
		int len = board.getHeight()/10;
		int wid = board.getWidth()/9;
		this.x_len = this.x * len - len/2;
		this.y_len = this.y * wid - wid/2;
		this.piece.setLength(len);
		this.piece.setWidth(wid);

		piece.setBounds(this.x * wid - wid, this.y*len - len, wid, len);
		this.hasPiece = true;
		board.validate();
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
		this.board.remove(piece);
		//TODO: remove the board from the piece;
		//TODO: validate the board
		this.hasPiece = false;
	}



}
