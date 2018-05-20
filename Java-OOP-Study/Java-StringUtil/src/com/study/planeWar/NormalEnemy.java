package com.study.planeWar;

import java.util.Random;

/**
 * ��ͨ����
 */
public class NormalEnemy extends Enemy {

	/**
	 * �����Ѷȵȼ�rank���ӹ�����ĵ����ٶ�
	 * @param rank
	 */
	public NormalEnemy(int rank) {
		this.sprite = PlaneGame.airplane; // �ȴ��޸�
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
