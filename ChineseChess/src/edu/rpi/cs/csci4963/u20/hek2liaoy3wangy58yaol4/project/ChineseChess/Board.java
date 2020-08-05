package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/** the main board of the Chinese chess game
 * @author JRE 1.8.0_231
 *
 */
public class Board {
	// the global variable segment
	public Position positionBoard[][]; // use position to represent the layout of the board
	public int rowSize; // the row number of the board
	public int colSize; // the column number of the board
	public int unitX; // the length of the horizontal (X) axis
	public int unitY; // the length of the vertical (Y) axis
	public int pieceSize; // the size of a single piece to be created
	public static Color colorChu;
	public static Color colorHan;
	/*
	naming convention of the Pieces
	B        _   R     1
	^  			 ^     ^
	BlackSide	 Rook  No.1
	*/
	// all pieces from the black side
	private Piece B_R1, B_K1, B_B1, B_A1, B_G ,B_A2, B_B2, B_K2, B_R2;
	private Piece       B_C1,                              B_C2;
	private Piece B_P1,       B_P2,       B_P3,      B_P4,       B_P5;
	// all pieces from the red side
	private Piece R_P1,       R_P2,       R_P3,      R_P4,       R_P5;
	private Piece       R_C1,                              R_C2;
	private Piece R_R1, R_K1, R_B1, R_A1, R_G ,R_A2, R_B2, R_K2, R_R2;

//	the variable in operation
	/** the current x coordinate under operation */
	public int currentX;
	/** the current y coordinate under operation */
	public int currentY;

	/** the constructor of the board
	 * @param l the row size (up to down) of the board
	 * @param w the col size (left to right) of the board
	 * @param s the size of a single piece
	 */
	public Board(int row, int col, int unitSize) {
		this.rowSize = row;
		this.colSize = col;
		this.pieceSize = unitSize;
		this.colorChu = new Color(0, 0, 0);
		this.colorHan = new Color(255, 0, 0);
		// add ActionListeners
		// initializing the positionBoard
		positionBoard = new Position[rowSize+1][colSize+1]; // start at 1 for visualizzation
		for (int i = 1; i <= rowSize; i++) {
			for (int j = 1; i <= colSize; j++) {
				positionBoard[i][j] = new Position(i*pieceSize, j*pieceSize);
			}
		}

		/*/

		// Red side chess
		// piece parameters: (PieceName, Side, size, Board)
		R_R1 = new Piece("Chariot", "red", pieceSize, this);
		R_K1 = new Piece("Knight", "red", pieceSize, this);
		R_B1 = new Piece("Bishop", "red", pieceSize, this);
		R_A1 = new Piece("Advisor", "red", pieceSize, this);
		R_G = new Piece("General", "red", pieceSize, this);
		R_A2 = new Piece("Advisor", "red", pieceSize, this);
		R_B2 = new Piece("Bishop", "red", pieceSize, this);
		R_K2 = new Piece("Knight", "red", pieceSize, this);
		R_R2 = new Piece("Chariot", "red", pieceSize, this);

		R_C1 = new Piece("Cannon", "red", pieceSize, this);
		R_C2 = new Piece("Cannon", "red", pieceSize, this);

		R_P1 = new Piece("Pawn", "red", pieceSize, this);
		R_P2 = new Piece("Pawn", "red", pieceSize, this);
		R_P3 = new Piece("Pawn", "red", pieceSize, this);
		R_P4 = new Piece("Pawn", "red", pieceSize, this);
		R_P5 = new Piece("Pawn", "red", pieceSize, this);

		// black side chess
		// piece parameters: (PieceName, Side, size, Board)
		B_R1 = new Piece("Chariot", "red", pieceSize, this);
		B_K1 = new Piece("Knight", "red", pieceSize, this);
		B_B1 = new Piece("Bishop", "red", pieceSize, this);
		B_A1 = new Piece("Advisor", "red", pieceSize, this);
		B_G = new Piece("General", "red", pieceSize, this);
		B_A2 = new Piece("Advisor", "red", pieceSize, this);
		B_B2 = new Piece("Bishop", "red", pieceSize, this);
		B_K2 = new Piece("Knight", "red", pieceSize, this);
		B_R2 = new Piece("Chariot", "red", pieceSize, this);

		B_C1 = new Piece("Cannon", "black", pieceSize, this);
		B_C2 = new Piece("Cannon", "black", pieceSize, this);

		B_P1 = new Piece("Pawn", "black", pieceSize, this);
		B_P2 = new Piece("Pawn", "black", pieceSize, this);
		B_P3 = new Piece("Pawn", "black", pieceSize, this);
		B_P4 = new Piece("Pawn", "black", pieceSize, this);
		B_P5 = new Piece("Pawn", "black", pieceSize, this);

		// (x , y)
		positionBoard[1][10].placePiece(R_R1, this);
		positionBoard[2][10].placePiece(R_K1,this);
		positionBoard[3][10].placePiece(R_B1,this);
		positionBoard[4][10].placePiece(R_A1,this);
		positionBoard[5][10].placePiece(R_G,this);
		positionBoard[6][10].placePiece(R_A2,this);
		positionBoard[7][10].placePiece(R_B2,this);
		positionBoard[8][10].placePiece(R_K2,this);
		positionBoard[9][10].placePiece(R_R2,this);
		positionBoard[2][8].placePiece(R_C1,this);
		positionBoard[8][8].placePiece(R_C2,this);
		positionBoard[1][7].placePiece(R_P1,this);
		positionBoard[3][7].placePiece(R_P2,this);
		positionBoard[5][7].placePiece(R_P3,this);
		positionBoard[7][7].placePiece(R_P4,this);
		positionBoard[9][7].placePiece(R_P5,this);

		positionBoard[1][1].placePiece(B_R1, this);
		positionBoard[2][1].placePiece(B_K1,this);
		positionBoard[3][1].placePiece(B_B1,this);
		positionBoard[4][1].placePiece(B_A1,this);
		positionBoard[5][1].placePiece(B_G,this);
		positionBoard[6][1].placePiece(B_A2,this);
		positionBoard[7][1].placePiece(B_B2,this);
		positionBoard[8][1].placePiece(B_K2,this);
		positionBoard[9][1].placePiece(B_R2,this);
		positionBoard[2][3].placePiece(B_C1,this);
		positionBoard[8][3].placePiece(B_C2,this);
		positionBoard[1][4].placePiece(B_P1,this);
		positionBoard[3][4].placePiece(B_P2,this);
		positionBoard[5][4].placePiece(B_P3,this);
		positionBoard[7][4].placePiece(B_P4,this);
		positionBoard[9][4].placePiece(B_P5,this);
		*/
	}

	/*
	//@Override
	// called automatically
	public void paintComponent(Graphics g) {
		// horizontal line
		super.paintComponent(g);
		for(int i = 1; i <= colSize;i++) {
				g.drawLine(positionBoard[1][i].getX(), positionBoard[1][i].getY(),
				positionBoard[rowSize][i].getX(), positionBoard[rowSize][i].getY());
			}

	}
*/



}
