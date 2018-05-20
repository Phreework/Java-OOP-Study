package com.study.planeWar;

import java.awt.image.BufferedImage;

/**
 * 这是玩家控制的飞机类
 * @author Phree
 *
 */
public class Player extends FlyObject {
    private BufferedImage[] sprites;
    private int index;
    private int doubleFire;
    private int life;

    public Player() {
    	life = 3;   
        doubleFire = 0;   
        sprites = new BufferedImage[]{PlaneGame.player0, PlaneGame.player1}; //图片数组
        sprite = PlaneGame.player0;   
        width = sprite.getWidth();
        height = sprite.getHeight();
        x = 150;
        y = 400;
    }
    
    /** 
     * 获取双倍火力
     */
    public int isDoubleFire() {
        return doubleFire;
    }

    /**
     *  设置双倍火力 
     */
    public void setDoubleFire(int doubleFire) {
        this.doubleFire = doubleFire;
    }


    public void addDoubleFire(){
        doubleFire = 40;
    }


    public void addLife(){  
        life++;
    }


    public void subtractLife(){ 
        life--;
    }


    public int getLife(){
        return life;
    }

    /**
     * 移动方法
     * @param x
     * @param y
     */
    public void moveTo(int x,int y){   
        this.x = x - width/2;
        this.y = y - height/2;
    }
    
    /** 
     * 发射子弹
     */
    public Bullet[] shoot(){   
        int xStep = width/4;      //4半
        int yStep = 20;  //步
        if(doubleFire>0){  //双倍火力
            Bullet[] bullets = new Bullet[2];
            bullets[0] = new Bullet(x+xStep,y-yStep);  
            bullets[1] = new Bullet(x+3*xStep,y-yStep);
            doubleFire --;
            return (Bullet[])bullets;

        }else{      //单倍火力
            Bullet[] bullets = new Bullet[1];
            bullets[0] = new Bullet(x+2*xStep,y-yStep);  
            return (Bullet[])bullets;
        }
    }
	@Override
	public void fly() {
		// TODO Auto-generated method stub
		 if(sprites.length > 0){
			 sprite = sprites[index++/10%sprites.length];  //切换
	     }
	}

	@Override
	public boolean isOutScreen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void step() {
		fly();	
	}
	
    /** 碰撞算法 */
    public boolean hit(FlyObject other){

        int x1 = other.x - this.width/2;                 //x坐标最小距离
        int x2 = other.x + this.width/2 + other.width;   //x坐标最大距离
        int y1 = other.y - this.height/2;                //y坐标最小距离
        int y2 = other.y + this.height/2 + other.height; //y坐标最大距离

        int playerx = this.x + this.width/2;               //英雄机x坐标中心点距离
        int playery = this.y + this.height/2;              //英雄机y坐标中心点距离

        return playerx>x1 && playerx<x2 && playery>y1 && playery<y2;   //区间范围内为碰撞
    }

}
