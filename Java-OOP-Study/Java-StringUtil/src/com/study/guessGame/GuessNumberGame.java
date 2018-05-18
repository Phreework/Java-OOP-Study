package com.study.guessGame;

import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {

	public static void main(String[] args) {

		run();
		
	}

	public static void run() {
		// init
		char[] str = new char[5];
		char[] userStr = new char[5];
		char[] all = new char[26];
		boolean[] flag = new boolean[all.length];
		int result[] = new int[2];
		
		System.out.println("--------游戏开始--------\n----用户键入五个字母:"); 
		
		getAllAlphabet(all);

		getFiveRandomChar(str, all, flag);

		userStr = userInputCharArray();

		result = compareCharArray(userStr,str);
		
		printResult(userStr, result);

	}

	private static void printResult(char[] userStr, int[] result) {
		System.out.println("--------你输入的字符是：");
		System.out.println(arraysToString(userStr));
		System.out.println("你猜对了：" + result[0]+"个数");
		System.out.println("你猜对了：" + result[1] + "个位置");
	}



	private static int[] compareCharArray(char[] userStr, char[] str) {
		// TODO Auto-generated method stub
		//0位为猜对字符个数，1位为位置猜对数
		int[] result = new int[2];
		for (int i = 0; i < userStr.length; i++) {
			for (int j = 0; j < str.length; j++) {
				if (userStr[i] == str[j]) {
					result[0]++;
					if (i == j) {
						result[1]++;
						continue;
					}
				}
			}
		}
		return result;
	}

	private static char[] userInputCharArray() {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String inputStr = scan.next().toUpperCase();
/*		if ("EXIT".equals(inputStr)) {
			System.out.println("下次再玩吧");
			return;
		}*/
		char[] userStr = inputStr.toCharArray();
		return userStr;
	}

	private static void getFiveRandomChar(char[] str, char[] all, boolean[] flag) {
		Random ran = new Random();
		for (int i = 0; i < str.length; i++) {
			int index;
			do {
				index = ran.nextInt(all.length);
			} while (flag[index]);
			flag[index] = true;
			str[i] = all[index];
		}
	}

	private static void getAllAlphabet(char[] all) {
		for (int i = 0, num = 65; i < all.length; i++, num++) {
			all[i] = (char) num;
		}
	}

	public static void printStr() {
	}
	
	public static char[] arraysToString(char[] value) {
		char[] str = new char[value.length*2 + 1];
		str[0] = '[';
		str[str.length - 1] = ']';
		for(int i = 0; i<value.length; i++) {
			str[i*2+1] = value[i];
			if(i != value.length-1)
				str[i*2+2] = ',';
		}
		return str;
	}
}
