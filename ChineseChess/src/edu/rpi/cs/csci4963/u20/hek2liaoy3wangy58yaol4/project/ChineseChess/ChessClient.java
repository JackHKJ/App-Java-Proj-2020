package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import static edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess.GameApp.gui;

/**
 * The client class for the chess game
 */
public class ChessClient extends Thread{

    // private member variable
    private Socket server;
    private String serverAddress;
    private int port;

    private ObjectInputStream objectInputStream= null;
    private ObjectOutputStream objectOutputStream= null;

    /**
     * The constructor of the Chess client class
     * @param serverAddress the servername of the client
     * @param port the port number of the client
     */
    public ChessClient(String serverAddress, int port){
        this.serverAddress = serverAddress;
        this.port = port;
    }

    /**
     * The sending normal running message to the clients
     * @param info the board information as string format
     */
    public void sendRunningMessage(String[][] info){
        Message message = new Message(info, Message.RUNNING);
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException ioException) {
        }
    }

    /**
     * The sending terminate message to the client
     * @param info the board information as string format
     */
    public void sendTerminateMessage(String[][] info){
    	if(server.isClosed()) { // When the destination server is closed
    		return;
    	}
        Message message = new Message(info, Message.TERMINATE);
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException ioException) {
        }
    }

    /**
     * The method to close all sockets
     */
    public void closeSocket(){
        try {
            server.close();
        } catch (IOException ioException) {
        }
    }

    /**
     * The method to send lose message
     * @param info the string format for the board
     */
    public void sendLoseMessage(String[][] info){
        Message message = new Message(info, Message.LOSE);
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException ioException) {
        }
    }

    /**
     * The method to evaluate if it is connected
     * @return true if it is connected, false otherwise
     */
    public boolean isConnected() {
    	return server.isConnected();
    }

    /**
     * run the client continously
     */
    @Override
    public void run(){
        try {
            // build the client socket
            server = new Socket(this.serverAddress, this.port);
            objectOutputStream = new ObjectOutputStream(server.getOutputStream());
            objectInputStream = new ObjectInputStream(server.getInputStream());
            Message message;
            while(true){ // keep receiving the message
                try {
                    message = (Message)objectInputStream.readObject();
                    if (message.gameOver()){
                        gui.boardPanel.loadFromNetStream(message.getBoardInfo());
                        break;
                    }

                    // updating your board
                    gui.boardPanel.loadFromNetStream(message.getBoardInfo());
                    gui.boardPanel.setMovable();
                    System.out.println("Client has been updated!");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            // Evaluate the state of message
            if (message.getState() == Message.LOSE) {
                JOptionPane.showMessageDialog(null, "You Lose!", "Lose",
                        JOptionPane.INFORMATION_MESSAGE);
                GUI.displayMsg("you Lose");
            }else{
                JOptionPane.showMessageDialog(null, "You Win!", "Win",
                        JOptionPane.INFORMATION_MESSAGE);
                GUI.displayMsg("you Win");
            }
//            disable all the button and make piece not movable            
    		GameApp.closeSocket();
    		server.close();
    		gui.closeProcedure();
            
            
          
            // following will coming soon :)
        } catch (IOException ioException) {
        }
    }
}
