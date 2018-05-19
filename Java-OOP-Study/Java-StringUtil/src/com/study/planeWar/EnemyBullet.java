package com.study.planeWar;

public class EnemyBullet extends FlyObject implements BulletObject{

	private int bulletType;
	 /** 初始化数据 */
    public EnemyBullet(int x,int y){
        this.x = x;
        this.y = y;
        this.sprite = PlaneGame.enemybullet;
        this.bulletType = ENEMY_BULLET;
        setSpeed(2);
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
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return bulletType;
	}

}
