package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameApp {
    public static final int SERVER = 1;
    public static final int CLIENT = 0;
    public static ChessClient client;
    public static ChessServer server;
    public static int choose;
    public static GUI gui;
    public static int ChooseClientOrServer(){
        String[] options = {"Client", "Server"};
        return JOptionPane.showOptionDialog(null, "Please choose your option: ",
                "Click a button",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    }
    public static void sendRunningMessage(Board board){
        if (choose == CLIENT){
            client.sendRunningMessage(board);
        }else{
            server.sendRunningMessage(board);
        }
    }

    public static void sendTerminateMessage(Board board){
        if (choose == CLIENT){
            client.sendTerminateMessage(board);
        }else{
            server.sendTerminateMessage(board);
        }
    }

    public static void sendLoseMessage(Board board){
        if (choose == CLIENT){
            client.sendLoseMessage(board);
        }else{
            server.sendLoseMessage(board);
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
        	gui.setServer();
        	server = new ChessServer(portNumber);
            server.start();  
            gui.setBoardMovable();            
        }else{
            client = new ChessClient(serverName, portNumber);
            client.start();
        }
   
        
        
    }
}
