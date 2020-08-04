package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import javax.swing.*;
import java.awt.*;

/** this is a class for the general chessPiece implementation
 * @version JRE 1.8.0_231 * 
 */

//  The list for the name of the pieces
enum PieceName{
//	˧����
	General,
//	�ˣ� ʿ
	Advisor,
//	�࣬ ��
	Bishop,
//	�أ� �R
	Knight,
//	�e�� ܇	
	Chariot,
//	�ڣ� �h
	Cannon,
//	���� ��
	Pawn
}


// indicator of which side the piece belongs to
enum Side{
//	goes first
	red,
//	goes second
	black
}

/** the single chess piece to be implemented */
public class Piece extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** the classification of the piece */
	private PieceName name;
	/** the side of the chess piece */
	private Side side;
	/** the size of the piece to be painted */
	private int length;
	/** the padding for drawing content*/
	private int padding = 2;
	/** the board that this piece lies on */
	private Board board;
	//TODO: Implement the interaction with the chess board(not yet implemented)
	
	
	
	/** the constructor of a piece
	 * @param nameIn the classification of the piece
	 * @param thisSide the side of the piece
	 * @param l length of the piece (size parameter)
	 * @param b the board this piece lies on
	 */
	public Piece(PieceName nameIn, Side thisSide, int l, Board b) {
		this.name = nameIn;
		this.side = thisSide;
		this.length = l;
		this.board = b;
		//TODO: add the interaction with the board
	}
	
	
	@Override
	public void paint(Graphics g) {
//		set the color according to the side
		if(this.side.toString().equals("red")) {
			g.setColor(Color.RED);			
		}
		else {
			g.setColor(Color.black);
		}
		g.fillOval(padding, padding, length-padding, length-padding);
		g.setColor(Color.white);
		
		g.setFont(new Font("����", Font.BOLD, length-8*padding));
		g.drawString(this.nameConverter(), 2*padding, length - 8 * padding);		
		g.setColor(Color.gray);
		g.drawOval(padding, padding, length-padding, length-padding);
		
	}
	
	
	/** helper function that gets the string representation of the chess piece
	 * @return the Chinese String representation of the piece
	 */
	private String nameConverter() {
		
		if(this.side == Side.red ) {
			switch (this.name) {
			case General:
				return "˧";
			case Advisor:
				return "��";
			case Bishop:
				return "��";
			case Knight:
				return "��";
			case Chariot:
				return "�e";
			case Cannon:
				return "��";
			case Pawn:
				return "��";
			}
		}
		else {
			switch (this.name) {
			case General:
				return "��";
			case Advisor:
				return "ʿ";
			case Bishop:
				return "��";
			case Knight:
				return "�R";
			case Chariot:
				return "܇";
			case Cannon:
				return "�h";
			case Pawn:
				return "��";
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
	public int getHeight() {
		return length;
	}


	
	
	/** for debugging usage */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100,100,300,300);
		frame.add(new Piece(PieceName.General, Side.red, 100, new Board(200,100,10)));
		frame.setVisible(true);
		
		
	}
	
	
	
	 
	
	
	
}












