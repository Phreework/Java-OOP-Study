package com.study.planeWar;

public class Bullet extends FlyObject implements BulletObject{

	private int bulletType;
	 /** ��ʼ������ */
    public Bullet(int x,int y){
        this.x = x;
        this.y = y;
        this.sprite = PlaneGame.bullet;
        bulletType = MY_BULLET;
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
		  return y<-height;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		fly();
	}
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return bulletType;
	}

}
