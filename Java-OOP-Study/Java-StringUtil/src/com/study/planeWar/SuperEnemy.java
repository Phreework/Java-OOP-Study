package com.study.planeWar;

import java.util.Random;

public class SuperEnemy extends Enemy {

	int life = 5;
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
	public SuperEnemy(int life) {
		this.sprite = PlaneGame.superenemy;
		width = sprite.getWidth();
		height = sprite.getHeight();
		y = -height;
		Random rand = new Random();
		x = rand.nextInt(PlaneGame.WIDTH - width);
		this.setScore(10);
		this.setSpeed(1);
		this.life = life;
	}

	@Override
	public void fly() {
		// TODO Auto-generated method stub
		y += speed;
	}

	@Override
	public boolean isOutScreen() {
		return y > PlaneGame.HEIGHT;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		fly();
	}

	public EnemyBullet[] shoot() {
		// TODO Auto-generated method stub
		int xStep = width / 4; 
		int yStep = 20; 

		EnemyBullet[] bullets = new EnemyBullet[1];
		bullets[0] = new EnemyBullet(x + 2 * xStep, y + width + yStep);
		return bullets;
	}

}
