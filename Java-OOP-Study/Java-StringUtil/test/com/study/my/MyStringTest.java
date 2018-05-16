package com.study.my;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyStringTest {

	@Test
	public void testEqualsString() {
		// �����Լ�
		MyString str = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		assertEquals(true, str.equalsString(str));
		// ��Сд
		MyString str1 = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		MyString str2 = new MyString(new char[] { 'P', 'h', 'r', 'e', 'e' });
		assertEquals(false, str1.equalsString(str2));
		// str3��str4��
		MyString str3 = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e', 'e' });
		MyString str4 = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		assertEquals(false, str3.equalsString(str4));
		// str5��str6��
		MyString str5 = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		MyString str6 = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e', 'e' });
		assertEquals(false, str5.equalsString(str6));

		// ���
		MyString str7 = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		MyString str8 = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		assertEquals(true, str7.equalsString(str8));

	}

}