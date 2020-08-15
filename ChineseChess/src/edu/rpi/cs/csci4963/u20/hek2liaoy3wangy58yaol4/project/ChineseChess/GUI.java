package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/** this class implements an user interface that can load and display the board
 *  and also display critical information, common buttons
 * @author Kejie Jack He
 * @version JRE 1.8.0_231
 */
public class GUI extends JFrame {
//	GLOBAL DATA SEGMENT
	/** the reference of the board */
	private Board board;
	/** the count down of a single step */
	private int countDown;
	/** the user name to be displayed */
	private String userName = "Guest";
	/** the rival name to be displayed; */
	private String rivalName = "Rival";
	/** dimension of the user device*/
	private int deviceHeight, deviceWidth;
	/** indicator of completeness */
	private boolean actionPerformed;
	/** double check of whether user wanna select defeat */
	private boolean defeatSelection;
	/** the constant of the full GUI */
	private int UNIT_SIZE_CONSTANT = 80;

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
			// hides the configuration bar
			private JMenuItem jmiHideConfigBar;


// the main board area
	private Board boardPanel;

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

	/** the constructor of the GUI*/
	public GUI() throws IOException{
		//setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.deviceHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.deviceWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
//		this.setBounds(deviceWidth/2 -deviceHeight*9/20 - 225, 5, deviceHeight/13*9 + 450,deviceHeight/13*10+113);
		this.setLocation(deviceWidth/2 -deviceHeight*9/20 - 225, 5);
//		Data segment settings
		this.countDown = 30;
		this.actionPerformed = false;
		this.defeatSelection = false;
		// set up frame icon
		File folderInput = new File("./image/chess2.png");
		BufferedImage folderImage = ImageIO.read(folderInput);
		this.setIconImage(folderImage);
//		GUI settings
		this.c = this.getContentPane();
		c.setLayout(new BorderLayout());
//		north the JMenuBar
		this.menuBar = new JMenuBar();
			this.jmGame = new JMenu("Game Settings");
			this.jmGame.setFont(new Font("Georgia",Font.BOLD, UNIT_SIZE_CONSTANT/2));
				this.jmiSetCountDown = new JMenuItem("Set Count Down");
				this.jmiSetCountDown.setFont(new Font("Georgia",Font.BOLD, UNIT_SIZE_CONSTANT/2));
				this.jmiAdmitDefeat = new JMenuItem("Admit Defeat");
				this.jmiAdmitDefeat.setFont(new Font("Georgia",Font.BOLD, UNIT_SIZE_CONSTANT/2));
				this.jmiCallDraw = new JMenuItem("Call a Draw");
				this.jmiCallDraw.setFont(new Font("Georgia",Font.BOLD, UNIT_SIZE_CONSTANT/2));
				this.jmGame.add(jmiSetCountDown);
				this.jmGame.add(jmiAdmitDefeat);
				this.jmGame.add(jmiCallDraw);
			this.menuBar.add(jmGame);

			this.jmUtilities = new JMenu("Utilities");
			this.jmUtilities.setFont(new Font("Georgia",Font.BOLD, UNIT_SIZE_CONSTANT/2));
				this.jmiHideConfigBar = new JMenuItem("Hide Config Bar");
				this.jmiHideConfigBar.setFont(new Font("Georgia",Font.BOLD, UNIT_SIZE_CONSTANT/2));
				this.jmiSetUIColor = new JMenuItem("Set UI color");
				this.jmiSetUIColor.setFont(new Font("Georgia",Font.BOLD, UNIT_SIZE_CONSTANT/2));
				this.jmUtilities.add(jmiHideConfigBar);
				this.jmUtilities.add(jmiSetUIColor);
			this.menuBar.add(jmUtilities);
			c.add(menuBar, BorderLayout.NORTH);

//			Center the board panel (pre-filled in Gray)
//			Original Version
//			this.boardPanel = new Board(9, 10, (this.deviceHeight-113)/10);
//			boardPanel.setPreferredSize(new Dimension(this.deviceHeight/12*9,this.deviceHeight/12*10));

//			hardcoded: (col, row, chess size)
			this.boardPanel = new Board(9, 10, UNIT_SIZE_CONSTANT);
			boardPanel.setPreferredSize(new Dimension(9*UNIT_SIZE_CONSTANT,10*UNIT_SIZE_CONSTANT));
			//boardPanel.setLayout(null);


//			boardPanel.setPreferredSize(new Dimension(this.getWidth()-450, this.getHeight()-113));
//			boardPanel.add(new JLabel("Load Board Needed"));
//			boardPanel.setBackground(Color.gray);
			c.add(boardPanel, BorderLayout.CENTER); // paintComponent of Board at this moment

//			East the Console
			this.console = new JTextArea("---Console---\n");
			this.console.setEditable(false);
			this.console.setWrapStyleWord(true);
			this.console.setLineWrap(true);
			this.console.setFont(new Font("Georgia",Font.BOLD, UNIT_SIZE_CONSTANT/4*3));
			this.consolePane = new JScrollPane(console);
			this.consolePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			this.consolePane.setPreferredSize(new Dimension(280,10*UNIT_SIZE_CONSTANT));
			c.add(consolePane, BorderLayout.EAST);

//			South the configuration panel
			this.configPanel = new JPanel();
			configPanel.setLayout(new GridLayout(1,3));
				this.defeatBtn = new JButton("Admit Defeat");
				this.defeatBtn.setFont(new Font("Georgia",Font.BOLD, UNIT_SIZE_CONSTANT/2));
				this.drawBtn = new JButton("Call Draw");
				this.drawBtn.setFont(new Font("Georgia",Font.BOLD, UNIT_SIZE_CONSTANT/2));
				this.hideBtn = new JButton("hide Config"); //TODO:
				this.hideBtn.setFont(new Font("Georgia",Font.BOLD, UNIT_SIZE_CONSTANT/2));
				configPanel.add(defeatBtn);
				configPanel.add(drawBtn);
				configPanel.add(hideBtn);
			c.add(configPanel, BorderLayout.SOUTH);
//			window listener of this game
			this.addWindowListener(new WindowListener() {
				@Override
				public void windowOpened(WindowEvent e) {}
				@Override
				public void windowIconified(WindowEvent e) {}
				@Override
				public void windowDeiconified(WindowEvent e) {}
				@Override
				public void windowDeactivated(WindowEvent e) {}
				@Override
				public void windowClosing(WindowEvent e) {}
				@Override
				public void windowClosed(WindowEvent e) {
					// TODO implement NET operation, signal losing game
				}
				@Override
				public void windowActivated(WindowEvent e) {}
			});

//			add the listeners to the GUI

//			UI color operation
			this.jmiSetUIColor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					changeUIColor();
				}
			});
//			set visible/hidden of the config bar
			this.jmiHideConfigBar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					changeConfigPaneStatus();
				}
			});
			this.hideBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					changeConfigPaneStatus();
				}
			});
//			set countdown
			this.jmiSetCountDown.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setCountDown();
				}
			});
//			admit defeat
			this.jmiAdmitDefeat.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!defeatSelection) {
						displayMsg("Click again to admit defeat");
						defeatSelection = true;
						return;
					}
					displayMsg("You LOSE");
					defeat();
				}
			});

			this.defeatBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!defeatSelection) {
						displayMsg("Click again to admit defeat");
						defeatSelection = true;
						return;
					}
					displayMsg("You LOSE");
					defeat();
				}
			});

//			call draw
			this.jmiCallDraw.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					displayMsg("Asking for a DRAW");
					callDraw();
				}
			});
			this.drawBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					displayMsg("Asking for a DRAW");
					callDraw();
				}
			});


//			end of the listeners
			this.pack();
			this.setResizable(true);
			this.setVisible(true);
	}


	/** display the message by appending to the end of the console
	 * @param message message to be appended
	 */
	public void displayMsg(String message) {
		this.console.append(message);
		this.console.append("\n");
	}

	/** use the color chooser to change the color of the UI	 */
	private void changeUIColor() {
		JDialog action = new JDialog(this, "Select the Color using Color Chooser", true);
		action.setBounds(deviceWidth/2 -deviceHeight*9/20 - 225,100,600,500);
		action.setLayout(new BorderLayout());
		JColorChooser jcc = new JColorChooser();
		action.add(jcc,BorderLayout.CENTER);
		JButton confirm = new JButton("Confirm");
		confirm.setMnemonic('c');
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color thisColor = jcc.getColor();
				setColor(thisColor);
				action.dispose();
				return;
			}
		});
		action.add(confirm,BorderLayout.PAGE_END);
		action.setVisible(true);

	}

	/** helper function for setting the color
	 * @param color the color to be modified
	 */
	private void setColor(Color color) {
		this.setBackground(color);
		this.boardPanel.setBackground(color);
		this.console.setBackground(color);
		this.displayMsg("Color modified");
	}

	/** helper function for changing the configpane's status */
	private void changeConfigPaneStatus() {
		if(this.configPanel.isVisible()) {
			this.jmiHideConfigBar.setText("Display Config");
		}
		else {
			this.jmiHideConfigBar.setText("Hide Config");
		}
		this.configPanel.setVisible(!this.configPanel.isVisible());
	}

	private void setCountDown() {
		JDialog action = new JDialog(this, true);
		action.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		action.setTitle("Set a new countdown time");
		action.setBounds(deviceWidth/2 -deviceHeight*9/20 - 225,5 , UNIT_SIZE_CONSTANT/20, 200);
		action.setLayout(new FlowLayout());
		JLabel tempLabel = new JLabel("New countdown:");
		tempLabel.setFont(new Font("Georgia",Font.BOLD, UNIT_SIZE_CONSTANT/2));
		action.add(tempLabel);
		JTextArea countdownArea = new JTextArea("30");
		countdownArea.setFont(new Font("Georgia",Font.BOLD, UNIT_SIZE_CONSTANT/2));
		countdownArea.setSize(new Dimension(200,50));
		action.add(countdownArea);
		JButton confirm = new JButton("Confirm");
		confirm.setFont(new Font("Georgia",Font.BOLD, UNIT_SIZE_CONSTANT/2));
		this.actionPerformed = false;
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int newCountDown = 0;
				try {
					newCountDown = Integer.valueOf(countdownArea.getText().trim());
				}
				catch(NumberFormatException nfe) {
					action.setTitle("Wrong number format, please re-enter");
					countdownArea.setText("30");
					actionPerformed = true;
				}
				countDown = newCountDown;
				actionPerformed = true;
				displayMsg("Set countdown to: "+ String.valueOf(countDown));
				//TODO: implement NET operation of the new countDown
			}
		});
		action.add(confirm);
		action.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {}
			@Override
			public void windowIconified(WindowEvent e) {}
			@Override
			public void windowDeiconified(WindowEvent e) {}
			@Override
			public void windowDeactivated(WindowEvent e) {}
			@Override
			public void windowClosing(WindowEvent e) {}
			@Override
			public void windowClosed(WindowEvent e) {
				if(!actionPerformed) {
					displayMsg("Canceled");
				}
			}
			@Override
			public void windowActivated(WindowEvent e) {}
		});
		action.setVisible(true);
	}

	/** net operation of defeat procedure */
	private void defeat() {
		//TODO: implement the NET procedure of defeat
	}

	/** net operation of the draw procedure */
	public void callDraw() {
		//TODO implement the NET procedure of draw
	}

	public void reloadBoard() {
		boardPanel.removeAll();
		//TODO: implement this operation, boardPane shall be used as an container
		boardPanel.validate();
	}

	public static void main(String[] args) throws IOException {
		GUI gui = new GUI();
		//Piece p1 = gui.boardPanel.positionBoard[1][1].getPiece();
		//gui.boardPanel.positionBoard[1][1].removePiece(p1, gui.boardPanel);
		//gui.boardPanel.positionBoard[1][2].placePiece(p1, gui.boardPanel);
//		gui.boardPanel.positionBoard[1][1]
//		gui.boardPanel.initializeAllPieces();
//		gui.displayMsg("Hello");
//		gui.displayMsg("LONGGGGGGGGGGGG msg example");
	}




}
