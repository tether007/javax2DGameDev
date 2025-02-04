package tile;

import main.gamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    gamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(gamePanel gp){
        this.gp=gp;
        tile=new Tile[10];
        getTileImage();
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/maps/world01.txt");
    }

    public void getTileImage(){
        System.out.println("start");
        try{
            tile[0] = new Tile();
            tile[0].image= ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));

            tile[1] = new Tile();
            tile[1].image= ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision=true;

            tile[2] = new Tile();
            tile[2].image= ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png"));
            tile[2].collision=true;

            tile[3]=new Tile();
            tile[3].image=ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4]=new Tile();
            tile[4].image=ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision=true;

            tile[5]=new Tile();
            tile[5].image=ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("end");
    }

    public void loadMap(String filePath){
        try{
            InputStream is=getClass().getResourceAsStream(filePath);
            BufferedReader br= new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;
            while(col< gp.maxWorldCol && row< gp.maxWorldRow){
                String line = br.readLine();

                while (col< gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num=Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;

                }
                if(col== gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();


        }


        catch (Exception e){

        }
    }

    public void draw(Graphics2D g2){
        //g2.drawImage(tile[0].image,0,0,gp.tileSize, gp.tileSize, null);
        //g2.drawImage(tile[1].image,48,0,gp.tileSize,gp.tileSize,null);
        //g2.drawImage(tile[2].image,96,0,gp.tileSize,gp.tileSize,null);
        int WorldCol=0,WorldRow=0;

        while(WorldCol< gp.maxWorldCol && WorldRow<gp.maxWorldRow){

            int tileNum = mapTileNum[WorldCol][WorldRow];
            int worldX=WorldCol*gp.tileSize;
            int worldY=WorldRow*gp.tileSize;
            int screenX=worldX-gp.player.worldX+gp.player.screenX;
            int screenY=worldY-gp.player.worldY+gp.player.screenY;

            if(worldX+gp.tileSize>gp.player.worldX-gp.player.screenX && worldX-gp.tileSize<gp.player.worldX+gp.player.worldX&&
               worldY+gp.tileSize>gp.player.worldY-gp.player.screenY && worldY-gp.tileSize<gp.player.worldY+gp.player.screenY ) {

               g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
            }
            WorldCol++;


            if(WorldCol==gp.maxWorldCol){
                WorldCol=0;
                WorldRow++;
            }
        }


    }
}
