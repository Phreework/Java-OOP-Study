package com.study.planeWar;

public class EnemyBullet extends FlyObject{


	 /** ��ʼ������ */
    public EnemyBullet(int x,int y){
        this.x = x;
        this.y = y;
        this.sprite = PlaneGame.enemybullet;
        setSpeed(3);
    }
	@Override
	public void fly() {
		// TODO Auto-generated method stub
		 y+=speed;
	}

	@Override
	public boolean isOutScreen() {
		// TODO Auto-generated method stub
		return y>PlaneGame.HEIGHT;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		fly();
	}

}
