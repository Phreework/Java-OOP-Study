package com.study.planeWar;

public class Bullet extends FlyObject{


	 /** 
	  * ��ʼ������ 
	  */
    public Bullet(int x,int y){
        this.x = x;
        this.y = y;
        this.sprite = PlaneGame.bullet;
        setSpeed(3);
    }
	@Override
	public void fly() {
		// TODO Auto-generated method stub
		 y-=speed;
	}

	@Override
	public boolean isOutScreen() {
		// TODO Auto-generated method stub
		  return y < -10;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		fly();
	}


}
