package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess.GameApp.gui;


public class ChessServer extends Thread{
    private ServerSocket serverSocket;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    private Socket client = null;

    private int port;

    public ChessServer(int port){
        this.port = port;
    }

    /**
     * When you send a normal board during running game
     * @param board the board you want to pass
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
     * @param board the board you want to pass
     */
    public void sendTerminateMessage(String[][] info){
        Message message = new Message(info, Message.TERMINATE);
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * When you want to lose
     * @param board the board you want to pass
     */
    public void sendLoseMessage(String[][] info){
        Message message = new Message(info, Message.LOSE);
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void closeServer(){
        try {
            client.close();
            serverSocket.close();
        } catch (IOException ioException) {

        }

    }

    @Override
    public void run(){
        try {
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
            if (message.getState() == Message.LOSE) {
                JOptionPane.showMessageDialog(null, "You Lose!", "Lose",
                        JOptionPane.INFORMATION_MESSAGE);
                GUI.displayMsg("you Lose");
            }else{
                JOptionPane.showMessageDialog(null, "You Win!", "Win",
                        JOptionPane.INFORMATION_MESSAGE);
                GUI.displayMsg("you Win");
            }
            client.close();
            serverSocket.close();

            // following will coming soon :)
        } catch (IOException ioException) {
        }
    }
}
