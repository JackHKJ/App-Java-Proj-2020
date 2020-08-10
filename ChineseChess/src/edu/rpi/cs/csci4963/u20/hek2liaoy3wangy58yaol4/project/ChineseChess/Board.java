package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess.Piece.PieceName;
import edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess.Piece.Side;



/** the main board of the Chinese chess game
 * @author JRE 1.8.0_231
 *
 */
public class Board extends JPanel implements MouseListener, MouseMotionListener {
	// the global variable segment
	public Position positionBoard[][]; // use position to represent the layout of the board
	public int rowSize; // the row number of the board
	public int colSize; // the column number of the board
	public int unitX; // the length of the horizontal (X) axis
	public int unitY; // the length of the vertical (Y) axis
	public int pieceSize; // the size of a single piece to be created
	public boolean move;
	public static Color colorChu;
	public static Color colorHan;
	public File folderInput = new File("./image/chessBoard.png");
	public BufferedImage folderImage;
	public int i = 0;

	// chess piece image names:
	// Chu
	private String B_R = "./image/chess4.png";
	private String B_K = "./image/chess3.png";
	private String B_B = "./image/chess2.png";
	private String B_A = "./image/chess1.png";
	private String B_G_i = "./image/chess0.png";
	private String B_C = "./image/chess5.png";
	private String B_P = "./image/chess6.png";
 // Han
	private String R_R = "./image/chess11.png";
	private String R_K = "./image/chess10.png";
	private String R_B = "./image/chess9.png";
	private String R_A = "./image/chess8.png";
	private String R_G_i = "./image/chess7.png";
	private String R_C = "./image/chess12.png";
	private String R_P = "./image/chess13.png";
	/*
	naming convention of the Pieces
	B        _   R     1
	^  			 ^     ^
	BlackSide	 Rook  No.1
	*/
	// all pieces from the Chu side
	private Piece B_R1, B_K1, B_B1, B_A1, B_G ,B_A2, B_B2, B_K2, B_R2;
	private Piece       B_C1,                              B_C2;
	private Piece B_P1,       B_P2,       B_P3,      B_P4,       B_P5;
	// all pieces from the Han side
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
		this.setLayout(null);

		addMouseListener(this);
		addMouseMotionListener(this);
		// initializing the positionBoard
		positionBoard = new Position[rowSize+1][colSize+1]; // start at 1 for visualizzation
		for (int i = 1; i <= rowSize; i++) {
			for (int j = 1; j <= colSize; j++) {
				//System.out.println(i + " | " + j);
				positionBoard[i][j] = new Position(i, j);
			}
		}

		initializeAllPieces();

	}

	/** this is the method that initialize all the pieces and place them to the desired position
	 */
	public void initializeAllPieces() {
		System.out.println(" initializeAllPieces ");

		// Han side chess
		// piece parameters: (PieceName, Side, size, Board)
		R_R1 = new Piece(PieceName.Chariot, Side.Han, pieceSize,pieceSize, this, R_R);
		R_K1 = new Piece(PieceName.Knight, Side.Han, pieceSize,pieceSize, this, R_K);
		R_B1 = new Piece(PieceName.Bishop, Side.Han, pieceSize,pieceSize, this, R_B);
		R_A1 = new Piece(PieceName.Advisor, Side.Han, pieceSize,pieceSize, this, R_A);
		R_G = new Piece(PieceName.General, Side.Han, pieceSize,pieceSize, this, R_G_i);
		R_A2 = new Piece(PieceName.Advisor, Side.Han, pieceSize,pieceSize, this, R_A);
		R_B2 = new Piece(PieceName.Bishop, Side.Han, pieceSize,pieceSize, this, R_B);
		R_K2 = new Piece(PieceName.Knight, Side.Han, pieceSize,pieceSize, this, R_K);
		R_R2 = new Piece(PieceName.Chariot, Side.Han, pieceSize,pieceSize, this, R_R);

		R_C1 = new Piece(PieceName.Cannon, Side.Han, pieceSize,pieceSize, this, R_C);
		R_C2 = new Piece(PieceName.Cannon, Side.Han, pieceSize,pieceSize, this, R_C);

		R_P1 = new Piece(PieceName.Pawn, Side.Han, pieceSize,pieceSize, this, R_P);
		R_P2 = new Piece(PieceName.Pawn, Side.Han, pieceSize,pieceSize, this, R_P);
		R_P3 = new Piece(PieceName.Pawn, Side.Han, pieceSize,pieceSize, this, R_P);
		R_P4 = new Piece(PieceName.Pawn, Side.Han, pieceSize,pieceSize, this, R_P);
		R_P5 = new Piece(PieceName.Pawn, Side.Han, pieceSize,pieceSize, this, R_P);

		// Chu side chess
		// piece parameters: (PieceName, Side, size, Board)
		B_R1 = new Piece(PieceName.Chariot, Side.Chu, pieceSize,pieceSize, this, B_R);
		B_K1 = new Piece(PieceName.Knight, Side.Chu, pieceSize,pieceSize, this, B_K);
		B_B1 = new Piece(PieceName.Bishop, Side.Chu, pieceSize,pieceSize, this, B_B);
		B_A1 = new Piece(PieceName.Advisor, Side.Chu, pieceSize,pieceSize, this, B_A);
		B_G = new Piece(PieceName.General, Side.Chu, pieceSize,pieceSize, this, B_G_i);
		B_A2 = new Piece(PieceName.Advisor, Side.Chu, pieceSize,pieceSize, this, B_A);
		B_B2 = new Piece(PieceName.Bishop, Side.Chu, pieceSize,pieceSize, this, B_B);
		B_K2 = new Piece(PieceName.Knight, Side.Chu, pieceSize,pieceSize, this, B_K);
		B_R2 = new Piece(PieceName.Chariot, Side.Chu, pieceSize,pieceSize, this, B_R);

		B_C1 = new Piece(PieceName.Cannon, Side.Chu, pieceSize,pieceSize, this, B_C);
		B_C2 = new Piece(PieceName.Cannon, Side.Chu, pieceSize,pieceSize, this, B_C);

		B_P1 = new Piece(PieceName.Pawn, Side.Chu, pieceSize,pieceSize, this, B_P);
		B_P2 = new Piece(PieceName.Pawn, Side.Chu, pieceSize,pieceSize, this, B_P);
		B_P3 = new Piece(PieceName.Pawn, Side.Chu, pieceSize,pieceSize, this, B_P);
		B_P4 = new Piece(PieceName.Pawn, Side.Chu, pieceSize,pieceSize, this, B_P);
		B_P5 = new Piece(PieceName.Pawn, Side.Chu, pieceSize,pieceSize, this, B_P);

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
	}


	@Override
	// called automatically
	public void paintComponent(Graphics g) {

		System.out.println(" ------------------------------------------------ ");
		System.out.println(" paintComponent ");
		/*
		//----------- horizontal line--------------/
		super.paintComponent(g);
		for(int j = 1; j <= colSize; j++) {
			// drawline (x1, y1) -> (x2, y2)
			g.drawLine(positionBoard[1][j].getXLen(), positionBoard[1][j].getYLen(),
			positionBoard[rowSize][j].getXLen(), positionBoard[rowSize][j].getYLen());
		}
		//----------- vertical line--------------/
		for(int i = 1; i <= rowSize; i++){
				if(i != 1 && i != rowSize){
					// first half of vertical line (Chu area)
					g.drawLine(positionBoard[i][1].getXLen(), positionBoard[i][1].getYLen(),
					positionBoard[i][colSize-5].getXLen(), positionBoard[i][colSize-5].getYLen());
					// second half of the vertical line (Han area)
					g.drawLine(positionBoard[i][colSize-4].getXLen(), positionBoard[i][colSize-4].getYLen(),
					positionBoard[i][colSize].getXLen(), positionBoard[i][colSize].getYLen());
				}
				else{
					// veritcal line for two border sides
					g.drawLine(positionBoard[i][1].getXLen(), positionBoard[i][1].getYLen(),
					positionBoard[i][colSize].getXLen(), positionBoard[i][colSize].getYLen());
				}
		}
		//----------- X mark --------------/
		// for Chu
		g.drawLine(positionBoard[4][1].getXLen(),positionBoard[4][1].getYLen(),positionBoard[6][3].getXLen(),positionBoard[6][3].getYLen());
		g.drawLine(positionBoard[6][1].getXLen(),positionBoard[6][1].getYLen(),positionBoard[4][3].getXLen(),positionBoard[4][3].getYLen());
		// for Han
		g.drawLine(positionBoard[4][8].getXLen(),positionBoard[4][8].getYLen(),positionBoard[6][colSize].getXLen(),positionBoard[6][colSize].getYLen());
		g.drawLine(positionBoard[4][colSize].getXLen(),positionBoard[4][colSize].getYLen(),positionBoard[6][8].getXLen(),positionBoard[6][8].getYLen());
		//----------- coordinate marks --------------/
		for(int i = 1; i <= rowSize; i++){
			g.drawString("" + i, i*pieceSize, pieceSize/2);
		}

		int j = 1;
		for(char c = 'A'; c <= 'J'; c++){
			g.drawString("" + c, pieceSize/4, j*pieceSize);
			j++;
		}
		*/

		for (int i = 1; i <= rowSize; i++) {
			for (int k = 1; k <= colSize; k++) {
				if(positionBoard[i][k].getPiece() != null) {
				//	System.out.println(positionBoard[i][k].getPiece().getName() + " | "
				//	+ positionBoard[i][k].getPiece().getSide() + " -> (" + i + ", " + k + ")");
					positionBoard[i][k].scaleBoardPosition();
				}
			}
		//	System.out.println();
		}
		System.out.println(i);
		i += 1;


		try {
			folderImage = ImageIO.read(folderInput);
			g.drawImage(folderImage, 0, 0, this.getWidth(), this.getHeight(), null);
		}
		catch (IOException e) {
			System.out.println("Failed to load chess board image");
		}

//		System.out.printf("Boardlen: %d, Boardwid: %d\n", this.getHeight(), this.getWidth());



	}



	// override for the MouseListener
	public void mousePressed(MouseEvent e){

		Piece piece=null;
		Rectangle rect=null;

		if(e.getSource() == this){
			System.out.println("Mouse Pressed");
		}
		if(e.getSource() instanceof Piece){
			System.out.println("Chess Pressed");
		}

	}

	// override for the MouseMotionListener
	public void mouseDragged(MouseEvent e){

	}

	// override for the MouseListener
	public void mouseReleased(MouseEvent e){

	}



	public void mouseEntered(MouseEvent e){} // override for the MouseListener
	public void mouseExited(MouseEvent e){} // override for the MouseListener
	public void mouseMoved(MouseEvent e){} // override for the MouseMotionListener
	public void mouseClicked(MouseEvent e){} // override for the MouseMotionListener





}
