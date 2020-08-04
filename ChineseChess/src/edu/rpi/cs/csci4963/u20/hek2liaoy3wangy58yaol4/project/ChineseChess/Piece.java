package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import javax.swing.*;
import java.awt.*;

/** this is a class for the general chessPiece implementation
 * @version JRE 1.8.0_231 * 
 */

//  The list for the name of the pieces
enum PieceName{
//	帅，将
	General,
//	仕， 士
	Advisor,
//	相， 象
	Bishop,
//	傌， 馬
	Knight,
//	俥， 車	
	Chariot,
//	炮， 砲
	Cannon,
//	兵， 卒
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
		
		g.setFont(new Font("宋体", Font.BOLD, length-8*padding));
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
				return "帅";
			case Advisor:
				return "仕";
			case Bishop:
				return "相";
			case Knight:
				return "傌";
			case Chariot:
				return "俥";
			case Cannon:
				return "炮";
			case Pawn:
				return "兵";
			}
		}
		else {
			switch (this.name) {
			case General:
				return "将";
			case Advisor:
				return "士";
			case Bishop:
				return "象";
			case Knight:
				return "馬";
			case Chariot:
				return "車";
			case Cannon:
				return "砲";
			case Pawn:
				return "卒";
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












