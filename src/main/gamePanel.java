package main;

import javax.swing.*;
import java.awt.*;


public class gamePanel extends JPanel implements Runnable{
    //Screen settings

    final int originalTileSize = 16;
    final int scale= 3;
    final int tileSize=originalTileSize*scale; //48 is the apparent tile size
    final int maxScreencol=16;
    final int maxScreenrow=12;
    final int screenWidth=tileSize*maxScreencol;
    final int screenHeight=tileSize*maxScreenrow;
    //KeyHandler:
    KeyHandler keyHandler=new KeyHandler();
    //FPS:
    int FPS = 60;

    Thread gameThread;
    public gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }
    int PlayerX=100;
    int PlayerY=100;
    int speed=4;
    @Override
    public void run() {

        double drawInterval = (double) 1000000000 /FPS;//0.016666 sec time required  to change its position and repaint
        double nextDrawTime = System.nanoTime()+drawInterval;


        while(gameThread!=null){

            //gameLoop:
            update();

            repaint();


            try {
                double remainingTime=nextDrawTime-System.nanoTime();
                remainingTime=remainingTime/1000000;

                if(remainingTime<0){
                    remainingTime=0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime+=drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }
    public void update(){
        if(keyHandler.downPressed==true){
            PlayerY +=speed;
        }
        if(keyHandler.upPressed==true){
            PlayerY -=speed;
        }
        if(keyHandler.leftPressed==true){
            PlayerX -=speed;
        }
        if(keyHandler.rightPressed==true){
            PlayerX +=speed;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(PlayerX,PlayerY,tileSize,tileSize);
        g2.dispose();
        }

    }

