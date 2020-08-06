package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import javax.swing.*;
import java.awt.*;

/** this class implements an user interface that can load and display the board
 *  and also display critical information, common buttons
 * @author Kejie Jack He
 * @version JRE 1.8.0_231
 */
public class GUI extends JFrame{
//	GLOBAL DATA SEGMENT
	/** the reference of the board */
	private Board board;
	/** the count down of a single step */
	private int countDown;
	/** the user name to be displayed */
	private String userName = "Guest";
	/** the rival name to be displayed; */
	private String rivalName = "Rival";
	
//	GUI SEGMENT 
	
	private Container c;
	
	
//	The JMenu Bar 
	private JMenuBar menuBar;
		// the game selection
		private JMenu jmGame;
			// set the count down of a single round, will be in effect from next round
			private JMenuItem jmiSetCountDown;
			// set self to be defeated and continues to the nextGame stage
			private JMenuItem jmiAdmitDefeat;
			// send Draw request to the rival, if confirmed then both party gets a point,
			// otherwise, game continues
			private JMenuItem jmiCallDraw;
		// the utilities selection
		private JMenu jmUtilities;
			// set the UI Color;
			private JMenuItem jmiSetUIColor;
			// hides the config bar
			private JMenuItem jmiHideCongigBar;
			
	
// the main board area
	private JPanel boardPanel;
	
//	the Console
	private JScrollPane consolePane;
	private JTextArea console;
	
//	the Configuration Panel
	private JPanel configPanel;
		// defeat
		private JButton defeatBtn;
		// draw
		private JButton drawBtn;
		// hide
		private JButton hideBtn;
	
		
	
		
	
	
	
	
}
