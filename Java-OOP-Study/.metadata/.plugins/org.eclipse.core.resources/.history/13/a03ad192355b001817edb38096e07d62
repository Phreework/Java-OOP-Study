package com.study.planeWar;

import java.util.Random;

public class SuperEnemy extends Enemy implements Attack {

	public SuperEnemy() {
		  this.sprite = PlaneGame.superenemy;			
	        width = sprite.getWidth();
	        height = sprite.getHeight();
	        y = -height;          
	        Random rand = new Random();
	        x = rand.nextInt(PlaneGame.WIDTH - width);
	        this.setScore(10);
	        this.setSpeed(1);
	}
	@Override
	public void fly() {
		// TODO Auto-generated method stub
		y += speed;
	}

	@Override
	public boolean isOutScreen() {
		 return y>PlaneGame.HEIGHT;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		fly();
	}
	@Override
	public BulletObject[] shoot() {
		// TODO Auto-generated method stub
		int xStep = width/4;      //4°ë
        int yStep = 20;  //²½

            EnemyBullet[] bullets = new EnemyBullet[1];
            bullets[0] = new EnemyBullet(x+2*xStep,y+yStep);  
            return (BulletObject[]) bullets;
        }


}
