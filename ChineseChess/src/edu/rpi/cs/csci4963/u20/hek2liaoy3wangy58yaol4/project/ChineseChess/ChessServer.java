package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess.GameApp.gui;

/**
 * The server class for the whole game
 */
public class ChessServer extends Thread{

    // private member variable
    private ServerSocket serverSocket;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    private Socket client = null;

    private int port;

    /**
     * The constructor of the server
     * @param port the port number
     */
    public ChessServer(int port){
        this.port = port;
    }

    /**
     * When you send a normal board during running game
     * @param info the board you want to pass
     */
    public void sendRunningMessage(String[][] info){
        Message message = new Message(info, Message.RUNNING);
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException ioException) {
        }
    }

    /**
     * When you want to HeQi
     * @param info the board you want to pass
     */
    public void sendTerminateMessage(String[][] info){
    	if(client.isClosed()) {
    		return;
    	}
        Message message = new Message(info, Message.TERMINATE);
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException ioException) {
        }
    }

    /**
     * When you want to lose
     * @param info the board you want to pass
     */
    public void sendLoseMessage(String[][] info){
        Message message = new Message(info, Message.LOSE);
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException ioException) {
        }
    }

    /**
     * The close method for the server
     */
    public void closeServer(){
        try {
            client.close();
            serverSocket.close();
        } catch (IOException ioException) {

        }
    }

    /**
     * run the server and continously
     */
    @Override
    public void run(){
        try {
            // utilize socket to accept
            serverSocket = new ServerSocket(port);

            client = serverSocket.accept();
            objectInputStream = new ObjectInputStream(client.getInputStream());
            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            Message message;
            while(true){
                try {
                    message = (Message)objectInputStream.readObject();
                    if (message.gameOver()){
                        gui.boardPanel.loadFromNetStream(message.getBoardInfo());
                        break;
                    }

                    // updating your board
                    gui.boardPanel.loadFromNetStream(message.getBoardInfo());
                    gui.boardPanel.setMovable();
                    System.out.println("Server has been already updated!");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            // When the game terminates
            if (message.getState() == Message.LOSE) {
                JOptionPane.showMessageDialog(null, "You Lose!", "Lose",
                        JOptionPane.INFORMATION_MESSAGE);
                GUI.displayMsg("you Lose");
            }else{
                JOptionPane.showMessageDialog(null, "You Win!", "Win",
                        JOptionPane.INFORMATION_MESSAGE);
                GUI.displayMsg("you Win");
            }

            // close all sockets and GUI
            client.close();            
            serverSocket.close();
            gui.closeProcedure();
        } catch (IOException ioException) {
        }
    }
}
