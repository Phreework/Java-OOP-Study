package com.study.planeWar;

import java.util.Random;

public class Bee extends Enemy implements Award {

	private int xSpeed = 1; // x坐标移动速度
	private int ySpeed = 2; // y坐标移动速度
	private int awardType; // 奖励类型

	/** 
	 * 初始化数据
	 */
	public Bee() {
		this.sprite = PlaneGame.bee;
		width = sprite.getWidth();
		height = sprite.getHeight();
		y = -height;
		Random rand = new Random();
		x = rand.nextInt(PlaneGame.WIDTH - width);
		awardType = rand.nextInt(2); // 初始化时给奖励
	}

	@Override
	public void fly() {
		// TODO Auto-generated method stub
		 x += xSpeed;
	        y += ySpeed;
	        if(x > PlaneGame.WIDTH-width){  
	            xSpeed = -1;
	        }
	        if(x < 0){
	            xSpeed = 1;
	        }
	}

	@Override
	public boolean isOutScreen() {
		// TODO Auto-generated method stub
		 return y>PlaneGame.HEIGHT;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return awardType;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		fly();
	}

}
