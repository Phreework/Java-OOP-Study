package com.study.util;

/**
 * @author Phree
 * @Description �򵥵�String�����࣬ʵ��equal��trim�ȳ��ù���
 */
public class StringUtil {

	/**
	 * �ж������ַ����Ƿ���ȫ��ͬ
	 * @param s1
	 * @param s2
	 * @return �Ƿ����
	 */
	public static boolean equal(String s1, String s2) {
		char[] charArray1 = s1.toCharArray();
		char[] charArray2 = s2.toCharArray();

		if (charArray1.length !=  charArray2.length) return false;
		for (int i = 0; i < charArray1.length; i++){ 				//�����ַ�
			if (charArray1[i] != charArray2[i]) return false;		//������κ�һ���ַ���ͬ�������ж�Ϊ�����
		}
		return true;												
	}
	/**
	 * ȥ���ַ���ͷβ�Ŀո��м�Ŀո���
	 * @param s
	 */
	public static String trim(String s) {
		int i = s.length();											// �ַ������һ���ַ���λ��
		int j = 0;													// �ַ�����һ���ַ�
		char[] charArray = s.toCharArray();							// ���ַ���ת�����ַ�����
		while ((j < i) && (charArray[(j)] == ' '))
			++j;													// ȷ���ַ���ǰ��Ŀո���
		while ((j < i) && (charArray[(i - 1)] == ' '))
			--i;													// ȷ���ַ�������Ŀո���
		if ((j > 0) || (i < s.length())) {
			return s.substring(j, i);
		} else {
			return s;
		}
	}
	
	
	

}