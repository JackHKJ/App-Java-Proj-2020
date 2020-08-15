package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/** this is a class for the general chessPiece implementation
 * @version JRE 1.8.0_231 *
 */



/** the single chess piece to be implemented */
public class Piece extends JLabel{

	private static final long serialVersionUID = 1L;
	private PieceName name; // the classification of the piece
	private Side side; // the side of the chess piece
	private int height; // the height of the piece to be painted
	private int width; // the width of the piece to be painted
	private int padding = 2; // the padding for drawing content
	private Board board; // the board that this piece lies on

	public File folderInput;
	public BufferedImage folderImage;


	//  The list for the name of the pieces
 	enum PieceName{
	 General, //jiang
	 Advisor, //shi
	 Bishop, //xiang
	 Knight, //ma
	 Chariot, //ju
	 Cannon, //pao
	 Pawn //bing
	}

	// indicator of which side the piece belongs to
	enum Side{
		Han, // moves first
		Chu // moves second
	}

	/** the constructor of a piece
	 * @param nameIn the classification of the piece
	 * @param side the side of the piece
	 * @param height height of the piece (height parameter)
	 * @param width the width of the piece
	 * @param board the board this piece lies on
	 */
	public Piece(PieceName nameIn, Side side, int height, int width, Board board, String imageName) {
		this.name = nameIn;
		this.side = side;
		this.height = height;
		this.width = width;
		this.board = board;
		this.folderInput = new File(imageName);
		this.addMouseListener(board);
		this.addMouseMotionListener(board);
	}

	/** the paint method will be called when initialize chess and dragging chess
	 * @param g Graphics
	 */
	@Override
	public void paint(Graphics g) {
		//System.out.println("chess paint: " + this.getName() + " | side: " + this.getSide());
		System.out.println("paint by " + Board.press + " (should equal to set): " + this.getX() + " | " + this.getY() );
		//super.paint(g);
		if(this.side.toString().equals("Han")) {
			g.setColor(Board.colorHan); // getting public static variable from Board
		}
		else {
			g.setColor(Board.colorChu); // getting public static variable from Board
		}
		g.fillOval(padding, padding, height-padding, height-padding);
		g.setColor(Color.white);

		//g.setFont(new Font("", Font.BOLD, height-8*padding));
		g.drawString(this.nameConverter(), 2*padding, height - 8 * padding);
		g.setColor(Color.gray);
		g.drawOval(padding, padding, height-padding, height-padding);
//		*/
		try {
			// System.out.println("chess paint image");
			folderImage = ImageIO.read(folderInput);
			//g.drawImage(folderImage, 0, 0, this.getWidth(), this.getHeight(),  null);
		}
		catch (IOException e) {
			System.out.println("Failed to load chess image");
		}

	}


	/** helper function that gets the string representation of the chess piece
	 * @return the Chinese String representation of the piece
	 */
	private String nameConverter() {

		if(this.side == Side.Han ) {
			switch (this.name) {
			case General:
				return "Shuai";
			case Advisor:
				return "Shi";
			case Bishop:
				return "Xiang";
			case Knight:
				return "Ma";
			case Chariot:
				return "Ju";
			case Cannon:
				return "Pao";
			case Pawn:
				return "Bing";
			}
		}
		else {
			switch (this.name) {
			case General:
				return "Jiang";
			case Advisor:
				return "Shi";
			case Bishop:
				return "Xiang";
			case Knight:
				return "Ma";
			case Chariot:
				return "Ju";
			case Cannon:
				return "Pao";
			case Pawn:
				return "Zu";
			}
		}
		return null;

	}



	/** getter for name
	 * @return name of the piece
	 */
	public String getName() {
		return this.name.toString();
	}


	/** getter for side
	 * @return side of the piece
	 */
	public Side getSide() {
		return this.side;
	}

	/** getter for height
	 * @return height of the piece
	 */
	public int getHeight() {
		return this.height;
	}

	/** getter for width
	 * @return width of the piece
	 */
	public int getWidth() {
		return this.width;
	}

	public void setLength(int len) {
		this.height = len;
	}

	public void setWidth(int wid) {
		this.width = wid;
	}








}
