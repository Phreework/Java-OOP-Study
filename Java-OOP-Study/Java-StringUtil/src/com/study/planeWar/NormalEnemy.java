package com.study.planeWar;

import java.util.Random;

/**
 * 普通敌人
 */
public class NormalEnemy extends Enemy {

	/**
	 * 根据难度等级rank增加构造出的敌人速度
	 * @param rank
	 */
	public NormalEnemy(int rank) {
		this.sprite = PlaneGame.airplane; // 等待修改
		width = sprite.getWidth();
		height = sprite.getHeight();
		y = -height;
		Random rand = new Random();
		x = rand.nextInt(PlaneGame.WIDTH - width);
		this.setScore(5);
		this.setSpeed(2 + rank);
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
