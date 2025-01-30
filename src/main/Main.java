package main;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
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