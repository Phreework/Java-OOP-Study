package com.study.planeWar;

public interface Award {
	int DOUBLE_FIRE = 0; // 双倍火力
	int LIFE = 1; // 加1条命

	/** 
	 * 获得奖励类型
	 */
	int getType();

}
