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

enum Side{
//	goes first
	red,
//	goes second
	black
}

public class Piece extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** the classification of the piece */
	private PieceName name;
	/** the side of the chess piece */
	private Side side;
	/** the height to be painted */
	private int height;
	/** the width to be painted */
	private int width;
	/** the padding for drawing content*/
	private int padding = 2;
	
	//TODO: Implement the interaction with the chess board 
	
	
	
	/** the constructor of a piece
	 * @param nameIn the classification of the piece
	 * @param thisSide the side of the piece
	 * @param h height of the piece
	 * @param w width of the piece
	 */
	public Piece(PieceName nameIn, Side thisSide, int h, int w) {
		this.name = nameIn;
		this.side = thisSide;
		this.height = h;
		this.width = w;	
		
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
		g.fillOval(padding, padding, width-padding, height-padding);
		g.setColor(Color.white);
		g.setFont(new Font("Georgia", Font.BOLD, 30));
		g.drawString(this.name.toString(), 7, height-8);
		g.setColor(Color.gray);
		g.drawOval(padding, padding, width-padding, height-padding);
		
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

	/** getter for height 
	 * @return height of the piece
	 */
	public int getHeight() {
		return height;
	}

	/** getter for width
	 * @return width of the piece
	 */
	public int getWidth() {
		return width;
	}
	
	
	
	
	
	/** for debugging usage */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100,100,300,300);
		frame.add(new Piece(PieceName.Pawn, Side.red, 50, 50));
		frame.setVisible(true);
		
		
	}
	
	
	
	 
	
	
	
}












