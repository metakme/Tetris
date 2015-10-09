package tetris; 

import javax.swing.JFrame;

public class MainClass { 

    public static void main(String[] args) {
        Tetris t = new Tetris();
        JFrame mainFrame = new JFrame("Tetris");
        mainFrame.setResizable(false);
        mainFrame.setContentPane(t);
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        new Thread(t).start();
    } 

} 