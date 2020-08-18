package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import static edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess.GameApp.gui;

public class ChessClient extends Thread{

    private Socket server;
    private String serverAddress;
    private int port;

    private ObjectInputStream objectInputStream= null;
    private ObjectOutputStream objectOutputStream= null;

    public ChessClient(String serverAddress, int port){
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public void sendRunningMessage(String[][] info){
        Message message = new Message(info, Message.RUNNING);
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException ioException) {
        }
    }

    public void sendTerminateMessage(String[][] info){
        Message message = new Message(info, Message.TERMINATE);
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException ioException) {
        }
    }
    public void closeSocket(){
        try {
            server.close();
        } catch (IOException ioException) {
        }
    }

    public void sendLoseMessage(String[][] info){
        Message message = new Message(info, Message.LOSE);
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException ioException) {
        }
    }

    @Override
    public void run(){
        try {
            server = new Socket(this.serverAddress, this.port);
            objectOutputStream = new ObjectOutputStream(server.getOutputStream());
            objectInputStream = new ObjectInputStream(server.getInputStream());
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
                    System.out.println("Client has been updated!");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
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
