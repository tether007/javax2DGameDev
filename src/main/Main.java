package main;
// main class that loads up the window and main components of all the classes
import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();//window which contains the content
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Adventure time");

        gamePanel GamePanel = new gamePanel();
        window.add(GamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        GamePanel.startGameThread();
    }
}