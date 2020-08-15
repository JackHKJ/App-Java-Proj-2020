package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import java.io.Serializable;

public class Message implements Serializable {
    private String[][] boardInfo;
    public static final int LOSE = 0;
    public static final int TERMINATE = 1;
    public static final int RUNNING = 2;
    private int state;

    public Message(String[][] info, int state){
        this.boardInfo = info;
        this.state = state;
    }

    public String[][] getBoardInfo(){
        return boardInfo;
    }

    public int getState(){
        return state;
    }

    public boolean gameOver(){
        return state != RUNNING;
    }
}
