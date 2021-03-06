package com.study.planeWar;

import java.util.Random;

public class GameManager {
	// 游戏等级
	private static final int LEVEL_EASY = 0;
	private static final int LEVEL_NORMAL = 1;
	private static final int LEVEL_DIFF = 2;
	private int rank = LEVEL_EASY;
	private final String[] rankInfo = { "简单", "普通", "困难" };
	private int chance = 2;
	private Range range = new Range();

	class Range {
		boolean beeRange(int type) {
			return (type == 0);
		}

		boolean superRange(int type) {
			return (type >= 1 && type < chance);
		}

		boolean NormalRange(int type) {
			return type >= chance;
		}
	}

	public FlyObject getNextEnemy() {

		Random random = new Random();
		int type = random.nextInt(22);
		FlyObject enemy;
		if (range.beeRange(type)) {
			enemy = new Bee();
		} else if (range.superRange(type)) {
			enemy = new SuperEnemy(rank);
		} else {
			enemy = new NormalEnemy(rank);
		}
		return enemy;
	}

	public String getRankinfo() {
		return rankInfo[rank];
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void checkRank(int score) {
		// 使用卫语句，rank改变时只会执行一次，可提高性能
		if (score <= 500)
			return;

		if ((score >= 500 && score <= 1000) && (rank == LEVEL_EASY)) {
			rank = LEVEL_NORMAL;
			chance = 3;
		}

		if ((score > 1000) && (rank == LEVEL_NORMAL)) {
			rank = LEVEL_DIFF;
			chance = 4;
		}
	}

}
