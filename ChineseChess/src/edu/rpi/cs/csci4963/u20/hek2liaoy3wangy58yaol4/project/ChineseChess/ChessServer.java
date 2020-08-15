package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

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
    public void sendRunningMessage(Board board){
        Message message = new Message(board, Message.RUNNING);
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * When you want to HeQi
     * @param board the board you want to pass
     */
    public void sendTerminateMessage(Board board){
        Message message = new Message(board, Message.TERMINATE);
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
    public void sendLoseMessage(Board board){
        Message message = new Message(board, Message.LOSE);
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void run(){
        try {
            serverSocket = new ServerSocket(port);

            client = serverSocket.accept();
            objectInputStream = new ObjectInputStream(client.getInputStream());
            objectOutputStream = new ObjectOutputStream(client.getOutputStream());

            while(true){
                try {
                    Message message = (Message)objectInputStream.readObject();
                    if (message.gameOver()){
                        break;
                    }

                    // updating your board
                    gui.updateBoard(message.getBoard());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            client.close();
            serverSocket.close();

            // following will coming soon :)
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
