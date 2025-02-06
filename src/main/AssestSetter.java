package main;

import object.OBJ_Key;
import object.OBJ_chest;
import object.OBJ_door;

public class AssestSetter {
    gamePanel gp;
    public AssestSetter(gamePanel gp){
        this.gp=gp;
    }
    public void setObject(){
        gp.obj[0]=new OBJ_Key();
        gp.obj[0].worldX=23*gp.tileSize;
        gp.obj[0].worldY=7*gp.tileSize;

        gp.obj[1]=new OBJ_Key();
        gp.obj[1].worldX=23*gp.tileSize;
        gp.obj[1].worldY=40*gp.tileSize;

        gp.obj[2]=new OBJ_door();
        gp.obj[2].worldX=10*gp.tileSize;
        gp.obj[2].worldY=11*gp.tileSize;

        gp.obj[3]=new OBJ_chest();
        gp.obj[3].worldX=10*gp.tileSize;
        gp.obj[3].worldY=8*gp.tileSize;



    }
}
