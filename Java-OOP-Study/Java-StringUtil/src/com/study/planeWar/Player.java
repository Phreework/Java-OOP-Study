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
    	life = 3;   //��ʼ3����
        doubleFire = 0;   //��ʼ����Ϊ0
        sprites = new BufferedImage[]{PlaneGame.player0, PlaneGame.player1}; //Ӣ�ۻ�ͼƬ����
        sprite = PlaneGame.player0;   //��ʼΪhero0ͼƬ
        width = sprite.getWidth();
        height = sprite.getHeight();
        x = 150;
        y = 400;
    }
    
    /** ��ȡ˫������ */
    public int isDoubleFire() {
        return doubleFire;
    }

    /** ����˫������ */
    public void setDoubleFire(int doubleFire) {
        this.doubleFire = doubleFire;
    }

    /** ���ӻ��� */
    public void addDoubleFire(){
        doubleFire = 40;
    }

    /** ���� */
    public void addLife(){  //����
        life++;
    }

    /** ���� */
    public void subtractLife(){   //����
        life--;
    }

    /** ��ȡ�� */
    public int getLife(){
        return life;
    }

    /** ��ǰ�����ƶ���һ�£���Ծ��룬x,y���λ��  */
    public void moveTo(int x,int y){   
        this.x = x - width/2;
        this.y = y - height/2;
    }
    
    /** �����ӵ� */
    public Bullet[] shoot(){   
        int xStep = width/4;      //4��
        int yStep = 20;  //��
        if(doubleFire>0){  //˫������
            Bullet[] bullets = new Bullet[2];
            bullets[0] = new Bullet(x+xStep,y-yStep);  //y-yStep(�ӵ���ɻ���λ��)
            bullets[1] = new Bullet(x+3*xStep,y-yStep);
            return bullets;
        }else{      //��������
            Bullet[] bullets = new Bullet[1];
            bullets[0] = new Bullet(x+2*xStep,y-yStep);  
            return bullets;
        }
    }
	@Override
	public void fly() {
		// TODO Auto-generated method stub
		 if(sprites.length>0){
			 sprite = sprites[index++/10%sprites.length];  //�л�ͼƬhero0��hero1
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

        int herox = this.x + this.width/2;               //Ӣ�ۻ�x�������ĵ����
        int heroy = this.y + this.height/2;              //Ӣ�ۻ�y�������ĵ����

        return herox>x1 && herox<x2 && heroy>y1 && heroy<y2;   //���䷶Χ��Ϊײ����
    }

}