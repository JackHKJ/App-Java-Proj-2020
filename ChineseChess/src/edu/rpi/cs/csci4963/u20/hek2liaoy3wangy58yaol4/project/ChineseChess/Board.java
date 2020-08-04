package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;


/** the main board of the Chinese chess game
 * @author JRE 1.8.0_231
 *
 */
public class Board {
//	the global variable segment	 ///////////////////////////////////////////////
	/** use position to represent the layout of the board */
	public Position board_layout[][];
	/** the length of the vertical (Y) axis */
	public int length;
	/** the length of the horizontal (X) axis */
	public int width;
	/** the size of a single piece to be created */
	public int pieceSize;
	
//	naming convention of the Pieces
//	B        _   R     1
//	^  			 ^     ^
//	BlackSide	 Rook  No.1
	
	/** all pieces from the black side */	
	private Piece B_R1, B_K1, B_B1, B_A1, B_G ,B_A2, B_B2, B_K2, B_R2;
	private Piece       B_C1,                              B_C2;
	private Piece B_P1,       B_P2,       B_P3,      B_P4,       B_P5;	
	/** all pieces from the red side */
	private Piece R_P1,       R_P2,       R_P3,      R_P4,       R_P5;
	private Piece       R_C1,                              R_C2;
	private Piece R_R1, R_K1, R_B1, R_A1, R_G ,R_A2, R_B2, R_K2, R_R2;
	
	
	
//	the variable in operation //////////////////////////////////////////////////
	/** the current x coordinate under operation */
	public int currentX;
	/** the current y coordinate under operation */
	public int currentY;
	
	/** the constructor of the board
	 * @param l the length (up to down) of the board
	 * @param w the width (left to right) of the board
	 * @param s the size of a single piece
	 */
	public Board(int l, int w, int s) {
		this.length = l;
		this.width = w;
		this.pieceSize = s;
		
		//TODO: initialize the board
		
		

		
	}
	
	
	
	
}
