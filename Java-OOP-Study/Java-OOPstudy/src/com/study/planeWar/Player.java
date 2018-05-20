package com.study.planeWar;

import java.awt.image.BufferedImage;

/**
 * ������ҿ��Ƶķɻ���
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
        sprites = new BufferedImage[]{PlaneGame.player0, PlaneGame.player1}; //ͼƬ����
        sprite = PlaneGame.player0;   
        width = sprite.getWidth();
        height = sprite.getHeight();
        x = 150;
        y = 400;
    }
    
    /** 
     * ��ȡ˫������
     */
    public int isDoubleFire() {
        return doubleFire;
    }

    /**
     *  ����˫������ 
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
     * �ƶ�����
     * @param x
     * @param y
     */
    public void moveTo(int x,int y){   
        this.x = x - width/2;
        this.y = y - height/2;
    }
    
    /** 
     * �����ӵ�
     */
    public Bullet[] shoot(){   
        int xStep = width/4;      //4��
        int yStep = 20;  //��
        if(doubleFire>0){  //˫������
            Bullet[] bullets = new Bullet[2];
            bullets[0] = new Bullet(x+xStep,y-yStep);  
            bullets[1] = new Bullet(x+3*xStep,y-yStep);
            doubleFire --;
            return (Bullet[])bullets;

        }else{      //��������
            Bullet[] bullets = new Bullet[1];
            bullets[0] = new Bullet(x+2*xStep,y-yStep);  
            return (Bullet[])bullets;
        }
    }
	@Override
	public void fly() {
		// TODO Auto-generated method stub
		 if(sprites.length > 0){
			 sprite = sprites[index++/10%sprites.length];  //�л�
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
	
    /** ��ײ�㷨 */
    public boolean hit(FlyObject other){

        int x1 = other.x - this.width/2;                 //x������С����
        int x2 = other.x + this.width/2 + other.width;   //x����������
        int y1 = other.y - this.height/2;                //y������С����
        int y2 = other.y + this.height/2 + other.height; //y����������

        int playerx = this.x + this.width/2;               //Ӣ�ۻ�x�������ĵ����
        int playery = this.y + this.height/2;              //Ӣ�ۻ�y�������ĵ����

        return playerx>x1 && playerx<x2 && playery>y1 && playery<y2;   //���䷶Χ��Ϊ��ײ
    }

}