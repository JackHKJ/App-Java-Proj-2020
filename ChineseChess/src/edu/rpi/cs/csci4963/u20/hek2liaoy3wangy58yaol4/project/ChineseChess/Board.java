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
import java.io.Serializable;

import edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess.Piece.PieceName;
import edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess.Piece.Side;



/** the main board of the Chinese chess game
 * @author JRE 1.8.0_231
 *
 */
public class Board extends JPanel implements MouseListener, MouseMotionListener, Serializable {
	// the global variable segment
	public Position positionBoard[][]; // use position to represent the layout of the board
	public int colSize; // the row number of the board
	public int rowSize; // the column number of the board
	public int unitX; // the length of the horizontal (X) axis
	public int unitY; // the length of the vertical (Y) axis
	public int pieceSize; // the size of a single piece to be created
	public boolean movable;
	public static Color colorChu;
	public static Color colorHan;
	public File folderInput = new File("./image/chessBoard.png");
	public BufferedImage folderImage;
	public int i = 0;

	private Piece currentPiece;

	private boolean isServer;
	private boolean debugMode = false;

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

//	the variable in operation for chess
	Rule rule = null;
	public int currentX; 	// the current x position after pressed
	public int currentY;	// the current y position after pressed
	public int draggedX; // the current x position while dragging
	public int draggedY; // the current y position while dragging
	public int startI; // start I position of pressed chess
	public int startJ; // start J position of pressed chess
	public int endI; // end I position of released chess
	public int endJ; // end J position of released chess
	static public int press;


	// network
	//GameAPP network = null;

	/** the constructor of the board
	 * @param col the row size (up to down) of the board
	 * @param row the col size (left to right) of the board
	 * @param unitSize the size of a single piece
	 */
	public Board(int col, int row, int unitSize) {
		//setLayout(null);
		this.colSize = col;
		this.rowSize = row;
		this.pieceSize = unitSize;
		this.colorChu = new Color(0, 0, 0);
		this.colorHan = new Color(255, 0, 0);
		this.setLayout(null);

		addMouseListener(this);
		addMouseMotionListener(this);
		// initializing the positionBoard
		positionBoard = new Position[colSize+1][rowSize+1]; // start at 1 for visualizzation
		System.out.println("colSize " + colSize + " | " + "rowSize " + rowSize);
		for (int i = 1; i <= colSize; i++) {
			for (int j = 1; j <= rowSize; j++) {
				//System.out.println(i + " | " + j);
				positionBoard[i][j] = new Position(i, j);
			}
		}

		initializeAllPieces();
		rule = new Rule(this, positionBoard);
		this.movable = false;

	}

	/** this is the method that initialize all the pieces and place them to the desired position
	 */
	public void initializeAllPieces() {
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
		// System.out.println("Board Paint Component called");
		for (int i = 1; i <= colSize; i++) {
			for (int j = 1; j <= rowSize; j++) {
//				System.out.println(i + "-" + j);
				positionBoard[i][j].setBoard(this);
			}
		}

		//----------- horizontal line--------------/
		super.paintComponent(g);
		for(int j = 1; j <= rowSize; j++) {
			// drawline (x1, y1) -> (x2, y2)
			g.drawLine(positionBoard[1][j].getXLen(), positionBoard[1][j].getYLen(),
			positionBoard[colSize][j].getXLen(), positionBoard[colSize][j].getYLen());
		}
		//----------- vertical line--------------/
		for(int i = 1; i <= colSize; i++){
				if(i != 1 && i != colSize){
					// first half of vertical line (Chu area)
					g.drawLine(positionBoard[i][1].getXLen(), positionBoard[i][1].getYLen(),
					positionBoard[i][rowSize-5].getXLen(), positionBoard[i][rowSize-5].getYLen());
					// second half of the vertical line (Han area)
					g.drawLine(positionBoard[i][rowSize-4].getXLen(), positionBoard[i][rowSize-4].getYLen(),
					positionBoard[i][rowSize].getXLen(), positionBoard[i][rowSize].getYLen());
				}
				else{
					// veritcal line for two border sides
					g.drawLine(positionBoard[i][1].getXLen(), positionBoard[i][1].getYLen(),
					positionBoard[i][rowSize].getXLen(), positionBoard[i][rowSize].getYLen());
				}
		}
		//----------- X mark --------------/
		// for Chu
		g.drawLine(positionBoard[4][1].getXLen(),positionBoard[4][1].getYLen(),positionBoard[6][3].getXLen(),positionBoard[6][3].getYLen());
		g.drawLine(positionBoard[6][1].getXLen(),positionBoard[6][1].getYLen(),positionBoard[4][3].getXLen(),positionBoard[4][3].getYLen());
		// for Han
		g.drawLine(positionBoard[4][8].getXLen(),positionBoard[4][8].getYLen(),positionBoard[6][rowSize].getXLen(),positionBoard[6][rowSize].getYLen());
		g.drawLine(positionBoard[4][rowSize].getXLen(),positionBoard[4][rowSize].getYLen(),positionBoard[6][8].getXLen(),positionBoard[6][8].getYLen());
		//----------- coordinate marks --------------/
		for(int i = 1; i <= colSize; i++){
			g.drawString("" + i, i*pieceSize, pieceSize/2);
		}

		int j = 1;
		for(char c = 'A'; c <= 'J'; c++){
			g.drawString("" + c, pieceSize/4, j*pieceSize);
			j++;
		}



		for (int i = 1; i <= colSize; i++) {
			for (int k = 1; k <= rowSize; k++) {
				if(positionBoard[i][k].getPiece() != null) {
				//	System.out.println(positionBoard[i][k].getPiece().getName() + " | "
				//	+ positionBoard[i][k].getPiece().getSide() + " -> (" + i + ", " + k + ")");
					if(positionBoard[i][k].getPiece().equals(currentPiece)){
						continue;
					}
					positionBoard[i][k].scaleBoardPosition();
				}
			}
		}

//		try {
//			folderImage = ImageIO.read(folderInput);
//			g.drawImage(folderImage, 0, 0, this.getWidth(), this.getHeight(), null);
//		}
//		catch (IOException e) {
//			System.out.println("Failed to load chess board image");
//		}

		// System.out.printf("Boardlen: %d, Boardwid: %d\n", this.getHeight(), this.getWidth());
		// System.out.printf("PieceSize: %s\n", positionBoard[1][1].getPiece().getHeight());


	}

	/** set the moveable as true to enable client select next step */
	public void setMovable() {
		this.movable = true;
	}

	/** setter of server */
	public void setServer() {
		this.isServer = true;
//		System.out.println("Set as server");
	}

	public String[][] forNetTransport(){
		String[][] ret = new String[this.colSize+1][this.rowSize+1];
		for(int i = 0; i < colSize+1; i++) {
			for(int j = 0; j < rowSize +1; j++) {
				if(i == 0 || j == 0) {
					ret[i][j] = null;
					continue;
				}
				if(positionBoard[i][j].hasPiece()) {
					ret[i][j] = positionBoard[i][j].getPiece().getName() + "@" + positionBoard[i][j].getPiece().getSide();
//					positionBoard[i][j].removePiece(positionBoard[i][j].getPiece(), this); ///J lang failure
				}
				else {
					ret[i][j] = null;
				}
			}
		}
		return ret;

	}


	public void loadFromNetStream(String[][] inputGrid) {
//		this.positionBoard = new Position[this.colSize+1][this.rowSize+1];
		for(int i = 1; i < colSize+1; i++) {
			for(int j = 1; j < rowSize +1; j++) {
				if(positionBoard[i][j].hasPiece()) {
					positionBoard[i][j].removePiece(positionBoard[i][j].getPiece(), this);
				}
			}
		}


		for(int i = 0; i < colSize+1; i++) {
			for(int j = 0; j < rowSize +1; j++) {

				Position thisPosition = new Position(i, j);

				if(inputGrid[i][j] != null) {
					try {
						String thisInfo = inputGrid[i][j];
						String thisPieceName = thisInfo.substring(0,thisInfo.indexOf("@"));
						String thisPieceSide = thisInfo.substring(thisInfo.indexOf("@") +1);
						Piece thisPiece = new Piece(PieceName.valueOf(thisPieceName), Side.valueOf(thisPieceSide), pieceSize, pieceSize, this, "");
						thisPosition.placePiece(thisPiece, this);
						}
					catch(IndexOutOfBoundsException iob) {
						System.out.println("Error(in loadfromnetStream): index out of range");
					}
				}
				positionBoard[i][j] = thisPosition;
				positionBoard[i][j].setBoard(this);
			}
		}
		this.repaint();
		this.validate();
	}


	@Override
	public void mousePressed(MouseEvent e) {
		/** if not this round then return */
		if(!this.movable && !debugMode) {
			return;
		}

		Piece piece = null;
		Rectangle area = null;
		press = 0;
		if(e.getSource() == this){
			System.out.println("Board Pressed");
			return;
		}
		if(e.getSource() instanceof Piece){
			System.out.println();
			// store the pressed coordinate of the chess
			piece = (Piece) e.getSource(); // the piece gets pressed
//			decide which side this piece belongs to and check isServer
			if(piece.getSide().equals(Side.Han) && !isServer) {
				return;
			}
			if(piece.getSide().equals(Side.Chu) && isServer) {
				return;
			}
			area = piece.getBounds(); // area of pressed
			currentX = area.x;
			currentY = area.y;
			for (int i = 1; i <= colSize; i++) {
				for (int j = 1; j <= rowSize; j++) {
					int x = positionBoard[i][j].getXLen();
					int y = positionBoard[i][j].getYLen();
					if(area.contains(x, y)){ // press within the area
						System.out.println("press: " + x + " | " + y );
						System.out.println("start: " + piece.getName() + "(" + piece.getSide() + ")" +
						" -> " + i + " | " + j);
						startI = i;
						startJ = j;
						break;
					}
				}
			}

		}


	}

	// setLocation is method of JLabel (chess)
	// set the location to the center point of the chess image
	@Override
	public void mouseDragged(MouseEvent e) {
		/** if not this round then return */
		if(!this.movable && !debugMode) {
			return;
		}
		// TODO: use move to interact with network

		Piece piece = null;
		press = 1;
		if(e.getSource() instanceof Piece){
			//if(moveable == false) return;
			piece = (Piece)e.getSource();
			if(piece.getSide().equals(Side.Han) && !isServer) {
				return;
			}
			if(piece.getSide().equals(Side.Chu) && isServer) {
				return;
			}
			// When dragging the chess, the coordiantes updates simultaneously
			e = SwingUtilities.convertMouseEvent(piece, e, this);
		}
		if(e.getSource() instanceof Board && piece != null){ // dragging within the area of board
			draggedX = e.getX();
			draggedY = e.getY();
			// System.out.println("-------------------------------");
//			 System.out.println(" dragged: " + draggedX + " | " + draggedY);
			// System.out.println("set to: " + (draggedX - piece.getWidth()/2) + " | " +
			// (draggedY - piece.getHeight()/2) );
//			Outside of board condition
			if(draggedX < pieceSize/2 || draggedY< pieceSize/2 ||
					draggedX > (this.getWidth() - pieceSize/2) || draggedY > (this.getHeight()-pieceSize/2)) {
				return;
			}
			piece.setLocation( draggedX - piece.getWidth()/2, draggedY - piece.getHeight()/2 );
			//System.out.println("CurrentLocation:" +  piece.getX() + "-" + piece.getY());
			currentPiece = piece;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		/** if not this round then return */
		if(!this.movable && !debugMode) {
			return;
		}
		Piece piece = null;
		Rectangle area = null;
		boolean containChessPoint = false;
		if(e.getSource() instanceof Piece){
			piece = (Piece)e.getSource();
			if(piece.getSide().equals(Side.Han) && !isServer) {
				return;
			}
			if(piece.getSide().equals(Side.Chu) && isServer) {
				return;
			}
			area = piece.getBounds();
			// When dragging the chess, the coordiantes updates simultaneously
			e = SwingUtilities.convertMouseEvent(piece, e, this);
		}
		// dragging within the area of board
		if(e.getSource() instanceof Board && piece != null){ // instanceof Board && piece != null
			// store the released coordinate of the chess
			for (int i = 1; i <= colSize; i++) {
				for (int j = 1; j <= rowSize; j++) {
					int x = positionBoard[i][j].getXLen();
					int y = positionBoard[i][j].getYLen();
					if(area.contains(x, y)){ // press within the area
						System.out.println("released: " + x + " | " + y );
						System.out.println("New pos: " + piece.getName() + "(" + piece.getSide() + ")" +
						" -> (col, row) " + i + " | " + j);
						containChessPoint = true;
						endI = i;
						endJ = j;
						break;
					}
				}
			}
			// the released position is valid on the board
			if(containChessPoint){
				Position releasedPosition = positionBoard[endI][endJ];
				//-----------------move the chess ontop of another chess----------------
				if(releasedPosition.hasPiece() ){
					// same side, reset to where it starts
					if( piece.getSide().equals( releasedPosition.getPiece().getSide() ) ){
						System.out.print("same side, reset to where it starts: ");
						System.out.println(startI + " | " + startJ);
						piece.setLocation(startI,startJ);
						(positionBoard[startI][startJ]).setHasPiece(true);
						positionBoard[startI][startJ].scaleBoardPosition();
						return;
					}
					// not the same side chess and interactive with rule
					System.out.println("end(I,J): " + endI + " | " + endJ );
					boolean move = rule.moveJudge(piece, startI, startJ, endI, endJ);
					// eat a chess and move to its position
					if(move){
						System.out.println("eat a chess and move to its position");
						Piece removedPiece = positionBoard[endI][endJ].getPiece();
						System.out.println("removed chess: " + removedPiece.getName()
						+ " (" + removedPiece.getSide() + ")");
						if(removedPiece.getName().equals( "General" ) ){
							 GameApp.sendLoseMessage(forNetTransport());
						}
						positionBoard[endI][endJ].removePiece(removedPiece, this);
						(positionBoard[startI][startJ]).setHasPiece(false);
						positionBoard[endI][endJ].placePiece(piece, this);
						(positionBoard[endI][endJ]).setHasPiece(true);
						positionBoard[endI][endJ].scaleBoardPosition();

						GameApp.sendRunningMessage(forNetTransport());
						//TODO: change side and interact with network
						// sendRunningMessage(this);
					//	validate();
					//	repaint();
					}
					// unable to eat(replace) the opponent's chess
					else if(!move){
						System.out.println("unable to eat(replace) the opponent's chess");
						piece.setLocation(startI,startJ);
						positionBoard[startI][startJ].setHasPiece(true);
						positionBoard[startI][startJ].scaleBoardPosition();
						//return;
					}
				}
				//-----------------move the chess to a new position--------------------
				else{
					System.out.println(piece.getName() );
					boolean move = rule.moveJudge(piece, startI, startJ, endI, endJ);
					// able to move the chess to a new position
					if(move){
						System.out.println("able to move the chess to a new position");
						System.out.println("new position:" + endI + " | " + endJ );
						(positionBoard[startI][startJ]).setHasPiece(false);
						positionBoard[endI][endJ].placePiece(piece, this);
						(positionBoard[endI][endJ]).setHasPiece(true);
						positionBoard[endI][endJ].scaleBoardPosition();
						// sendRunningMessage(this);
//						Made a move, set movable as false, then pass message
						if(debugMode) {
							return;
						}
						this.movable = false;
						GameApp.sendRunningMessage(forNetTransport());
					}
					 // unable to move, reset to where it starts
					else if(!move){
						System.out.println("unable to move, reset to where it starts");
						System.out.println("start :" + startI + " | " + startJ );
						piece.setLocation(startI,startJ);
						(positionBoard[startI][startJ]).setHasPiece(true);
						positionBoard[startI][startJ].scaleBoardPosition();
						//return;
					}

				}

			} // containChessPoint

		}

	}

	// ------------------------- Unused override --------------------------------/
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}






}
