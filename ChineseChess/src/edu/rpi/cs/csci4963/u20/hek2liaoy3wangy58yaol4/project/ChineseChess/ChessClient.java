package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

    public void sendRunningMessage(Board board){
        Message message = new Message(board, Message.RUNNING);
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void sendTerminateMessage(Board board){
        Message message = new Message(board, Message.TERMINATE);
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

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
            server = new Socket(this.serverAddress, this.port);
            objectOutputStream = new ObjectOutputStream(server.getOutputStream());
            objectInputStream = new ObjectInputStream(server.getInputStream());

            while(true){
                try {
                    Message message = (Message)objectInputStream.readObject();
                    if (message.gameOver()){
                        break;
                    }

                    // updating your board
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            // following will coming soon :)
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
