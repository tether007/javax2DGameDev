package entity;

import java.awt.image.BufferedImage;

// super class for any type of entity included in the game(ie players monsters and so on..)
public class Entity {
    public int x,y;
    public int speed;
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;//for player
    public String direction;

    public int spriteCounter =0;
    public int spriteNum =1;
}
