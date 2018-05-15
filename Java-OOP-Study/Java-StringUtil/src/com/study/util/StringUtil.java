package com.study.util;

/**
 * @author Phree
 * @Description 简单的String工具类，实现equal，trim等常用功能
 */
public class StringUtil {

	/**
	 * 去除字符串头尾的空格，中间的空格保留
	 * 
	 * @Description: TODO 
	 */
	public static String trim(String s) {
		int i = s.length();											// 字符串最后一个字符的位置
		int j = 0;													// 字符串第一个字符
		char[] charArray = s.toCharArray();							// 将字符串转换成字符数组
		while ((j < i) && (charArray[(j)] == ' '))
			++j;													// 确定字符串前面的空格数
		while ((j < i) && (charArray[(i - 1)] == ' '))
			--i;													// 确定字符串后面的空格数
		if ((j > 0) || (i < s.length())) {
			return s.substring(j, i);
		} else {
			return s;
		}
	}
	
	
	

}
