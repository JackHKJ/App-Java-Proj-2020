package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import javax.swing.*;
import java.awt.*;

/** this is a class for the general chessPiece implementation
 * @version JRE 1.8.0_231 *
 */



/** the single chess piece to be implemented */
public class Piece extends JLabel{

	private static final long serialVersionUID = 1L;
	private PieceName name; // the classification of the piece
	private Side side; // the side of the chess piece
	private int length; // the length of the piece to be painted
	private int padding = 2; // the padding for drawing content
	private Board board; // the board that this piece lies on
	//TODO: Implement the interaction with the chess board(not yet implemented)

	//  The list for the name of the pieces
	public enum PieceName{
	 General,
	 Advisor,
	 Bishop,
	 Knight,
	 Chariot,
	 Cannon,
	 Pawn
	}

	// indicator of which side the piece belongs to
	enum Side{
		Han, // move first
		Chu // move second
	}

	/** the constructor of a piece
	 * @param nameIn the classification of the piece
	 * @param side the side of the piece
	 * @param length length of the piece (length parameter)
	 * @param board the board this piece lies on
	 */
	public Piece(PieceName nameIn, Side side, int length, Board board) {
		this.name = nameIn;
		this.side = side;
		this.length = length;
		this.board = board;
		setSize(length,length);
		setBackground(Color.PINK);
		System.out.println("piece init " + length);
	}


	@Override
	public void paint(Graphics g) {
//		set the color according to the side
	System.out.println("paint");
		if(this.side.toString().equals("Han")) {
			g.setColor(Board.colorHan); // getting public static variable from Board
		}
		else {
			g.setColor(Board.colorChu); // getting public static variable from Board
		}
		g.fillOval(padding, padding, length-padding, length-padding);
		g.setColor(Color.white);

		//g.setFont(new Font("", Font.BOLD, length-8*padding));
		g.drawString(this.nameConverter(), 2*padding, length - 8 * padding);
		g.setColor(Color.gray);
		g.drawOval(padding, padding, length-padding, length-padding);

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
		return name.toString();
	}


	/** getter for side
	 * @return side of the piece
	 */
	public Side getSide() {
		return side;
	}

	/** getter for length
	 * @return length of the piece
	 */
	public int getLength() {
		return length;
	}




	/** for debugging usage */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100,100,300,300);
		frame.add(new Piece(PieceName.General, Side.Han, 100, new Board(200,100,10)));
		frame.setVisible(true);
	}







}
