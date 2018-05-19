package com.study.planeWar;

import java.util.Random;

/**
 * 普通敌人
 */
public class NormalEnemy extends Enemy {

	/**
	 * 普通难度构造
	 */
	public NormalEnemy() {
		this.sprite = PlaneGame.airplane; // 等待修改
		width = sprite.getWidth();
		height = sprite.getHeight();
		y = -height;
		Random rand = new Random();
		x = rand.nextInt(PlaneGame.WIDTH - width);
		this.setScore(5);
		this.setSpeed(3);
	}

	/**
	 * 困难难度调用构造增加速度
	 * @param spd
	 */
	public NormalEnemy(int spd) {
		this.sprite = PlaneGame.airplane; // 等待修改
		width = sprite.getWidth();
		height = sprite.getHeight();
		y = -height;
		Random rand = new Random();
		x = rand.nextInt(PlaneGame.WIDTH - width);
		this.setScore(5);
		this.setSpeed(3);
		this.setSpeed(spd);
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

}
