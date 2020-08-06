package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

// for debugging right now
public class ChineseChess {
  public static void main(String[] args){
    EventQueue.invokeLater(() ->
      {
        frame = new MainFrame();
        frame.setTitle("Chinese Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

      });
  }

}

class MainFrame extends JFrame {
  // variables
  protected int screenHeight;
  protected int screenWidth;
  
  public MainFrame(){
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    screenHeight = screenSize.height;
    screenWidth = screenSize.width;
    setSize(screenWidth/2, screenHeight/2);
  }

}
