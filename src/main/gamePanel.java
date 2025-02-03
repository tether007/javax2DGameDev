package main;

import javax.swing.*;
import java.awt.*;
import entity.Player;
import tile.Tile;
import tile.TileManager;

public class gamePanel extends JPanel implements Runnable{

    //Screen settings
    final int originalTileSize = 16;
    final int scale= 3;
    final public int tileSize=originalTileSize*scale; //48 is the apparent tile size
    final public int maxScreencol=16;
    final public int maxScreenrow=12;
    final public int screenWidth=tileSize*maxScreencol;
    final public int screenHeight=tileSize*maxScreenrow;

    //World Settings:
    public final int maxWorldCol=50;
    public final int maxWorldRow=50;
    public final int WorldWidth=tileSize*maxWorldCol;
    public final int WorldHeight=tileSize*maxWorldRow;


    //External classes instanciation:
    TileManager tileM =new TileManager(this);
    KeyHandler keyHandler=new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this,keyHandler);

    //FPS:
    int FPS = 60;

    //gamePanel's constructor
    public gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    //starting the game is the task of this thread
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }


    //Game thread to update and repaint the 2d object for every 0.0166666 sec
    @Override
    public void run() {

        double drawInterval = (double) 1000000000 /FPS;//0.016666 sec time required to change its position and repaint
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
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
        }

    }

