package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameApp {
    public static final int SERVER = 1;
    public static final int CLIENT = 0;
    public static ChessClient client;
    public static ChessServer server;
    public static final int WIN = -1;
    public static final int LOSE = -2;
    public static int STATE;
    public static int choose;
    public static GUI gui;
    public static int ChooseClientOrServer(){
        String[] options = {"Client", "Server"};
        return JOptionPane.showOptionDialog(null, "Please choose your option: ",
                "Click a button",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    }
    public static void sendRunningMessage(String[][] boardInfo){
        if (choose == CLIENT){
            client.sendRunningMessage(boardInfo);
        }else{
            server.sendRunningMessage(boardInfo);
        }
    }
    public static void closeSocket(){
        if (choose == CLIENT){
            client.closeSocket();
        }else{
            server.closeServer();
        }
    }

    public static void sendTerminateMessage(String[][] boardInfo){
        if (choose == CLIENT){
            client.sendTerminateMessage(boardInfo);
        }else{
            server.sendTerminateMessage(boardInfo);
        }
    }

    public static void sendLoseMessage(String[][] boardInfo){
        if (choose == CLIENT){
            client.sendLoseMessage(boardInfo);
        }else{
            server.sendLoseMessage(boardInfo);
        }
    }

    public static void main(String[] args) throws IOException{
        choose = ChooseClientOrServer();
        String serverName = "";
        
        if (choose == CLIENT){
            serverName = JOptionPane.showInputDialog("Please input your server name/address: ",
                    "localhost");
        }
        int portNumber = Integer.parseInt(JOptionPane.showInputDialog("Please input your port number: "));
        if (choose == SERVER){
        	/** server will go first */
        	server = new ChessServer(portNumber);
            server.start();
        }else{
            client = new ChessClient(serverName, portNumber);
            client.start();
        }

        gui = new GUI();
        if (choose == SERVER){
            gui.setServer();
            gui.displayMsg("This is a server");
            gui.setBoardMovable();
        }
    }
}
