package com.study.planeWar;

public abstract class Enemy extends FlyObject {
    protected int score;

    public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}


}
