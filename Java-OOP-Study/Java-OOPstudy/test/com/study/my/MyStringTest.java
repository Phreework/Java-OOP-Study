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

	@Test
	public void testArraysToString() {
		//�����[a,b,c,d]��ʽ
		MyString str = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		MyString finalStr = new MyString(new char[]{'[','p',',','h',',','r',',' ,'e',',', 'e',']'});
		MyString arrayStr = new MyString(str.arraysToString());
		System.out.println("------------�����ʽ����--------------");
		System.out.println("ԭ����-------------------------------");
		System.out.println(str.value);
		System.out.println("����������---------------------------");
		System.out.println(arrayStr.value);
		
		assertEquals(true, finalStr.equalsString(arrayStr));
	}
	
	@Test 
	public void testSubString() {
		//��ȡһ���ַ���
		MyString str = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		MyString finalStr = new MyString(new char[] {  'h', 'r', 'e' });
		MyString arrayStr = new MyString(str.subString(1, 3));
		assertEquals(true, finalStr.equalsString(arrayStr));
	}
	
	@Test
	public void testStrCopy() {
		//��str2�е�һ�ο�����str�����Ӧ��Ϊpteshree,��assertEquals����
		MyString str = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		MyString str2 = new MyString(new char[] { 't', 't', 'e', 's', 't' });
		MyString finalStr = new MyString(new char[] { 'p', 't', 'e', 's', 'h', 'r', 'e', 'e' });
		
		System.out.println("-----------�ַ�����������-------------");
		System.out.println("�ַ���1:-------------------------------");
		System.out.println(str.value);
		System.out.println("�ַ���2:-------------------------------");
		System.out.println(str2.value);
		
		MyString.strCopy(str, str2, 1, 3, 1);
		System.out.println("���ַ���2�еڶ������ĸ��ַ��������ַ����ڶ�λ�ַ���λ��:---------------------------");
		System.out.println(str.value);
		
		assertEquals(true,finalStr.equalsString(str));
		
	}
}