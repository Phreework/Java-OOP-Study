package com.study.planeWar;

import java.awt.image.BufferedImage;

public abstract class FlyObject{
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected BufferedImage sprite;
    protected int speed;

    public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	 /**
     * 检查是否出界
     * @return true 出界与否
     */
	public abstract boolean isOutScreen();

	 /**
     * 帧的步事件
     */
    public abstract void step();

   
	/**
     * 检查当前飞行物体是否被子弹(x,y)击(shoot)中
     * @param Bullet 子弹对象
     * @return true表示被击中了
     */
    public boolean isShootBy(Bullet bullet){
    	int x = bullet.x;  //子弹横坐标
        int y = bullet.y;  //子弹纵坐标
        return this.x<x && x<this.x+width && this.y<y && y<this.y+height;
    }
    
    public boolean isShootBy(EnemyBullet bullet){
    	int x = bullet.x;  //子弹横坐标
        int y = bullet.y;  //子弹纵坐标
        return this.x<x && x<this.x+width && this.y<y && y<this.y+height;
    }
    
    public abstract void fly();

}
