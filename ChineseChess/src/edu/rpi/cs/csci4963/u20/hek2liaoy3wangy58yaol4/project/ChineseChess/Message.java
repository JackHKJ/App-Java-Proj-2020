package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import java.io.Serializable;

/**
 * The message class to implement serializable interface
 */
public class Message implements Serializable {

    // member variables
    private String[][] boardInfo;
    public static final int LOSE = 0;
    public static final int TERMINATE = 1;
    public static final int RUNNING = 2;
    private int state;

    /**
     * The message constructor
     * @param info The string format board information
     * @param state the state of the board
     */
    public Message(String[][] info, int state){
        this.boardInfo = info;
        this.state = state;
    }

    /**
     * The observer to get the content of the message class
     * @return the information of the board information
     */
    public String[][] getBoardInfo(){
        return boardInfo;
    }

    /**
     * The observer to get the state of the message class
     * @return the state of the message object
     */
    public int getState(){
        return state;
    }

    /**
     * The method to evaluate if game over
     * @return true if gameOver, false otherwise
     */
    public boolean gameOver(){
        return state != RUNNING;
    }
}
