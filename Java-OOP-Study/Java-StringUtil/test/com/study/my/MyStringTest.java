package com.study.my;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyStringTest {

	@Test
	public void testEqualsString() {
		// 传入自己
		MyString str = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		assertEquals(true, str.equalsString(str));
		// 大小写
		MyString str1 = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		MyString str2 = new MyString(new char[] { 'P', 'h', 'r', 'e', 'e' });
		assertEquals(false, str1.equalsString(str2));
		// str3比str4多
		MyString str3 = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e', 'e' });
		MyString str4 = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		assertEquals(false, str3.equalsString(str4));
		// str5比str6少
		MyString str5 = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		MyString str6 = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e', 'e' });
		assertEquals(false, str5.equalsString(str6));

		// 相等
		MyString str7 = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		MyString str8 = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		assertEquals(true, str7.equalsString(str8));
		

	}

	@Test
	public void testArraysToString() {
		//输出成[a,b,c,d]形式
		MyString str = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		MyString finalStr = new MyString(new char[]{'[','p',',','h',',','r',',' ,'e',',', 'e',']'});
		MyString arrayStr = new MyString(str.arraysToString());
		System.out.println("------------输出形式测试--------------");
		System.out.println("原数组-------------------------------");
		System.out.println(str.value);
		System.out.println("操作后数组---------------------------");
		System.out.println(arrayStr.value);
		
		assertEquals(true, finalStr.equalsString(arrayStr));
	}
	
	@Test 
	public void testSubString() {
		//截取一段字符串
		MyString str = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		MyString finalStr = new MyString(new char[] {  'h', 'r', 'e' });
		MyString arrayStr = new MyString(str.subString(1, 3));
		assertEquals(true, finalStr.equalsString(arrayStr));
	}
	
	@Test
	public void testStrCopy() {
		//把str2中的一段拷贝进str，结果应该为phtesree,用assertEquals检验
		MyString str = new MyString(new char[] { 'p', 'h', 'r', 'e', 'e' });
		MyString str2 = new MyString(new char[] { 't', 't', 'e', 's', 't' });
		MyString finalStr = new MyString(new char[] { 'p', 'h', 't', 'e', 's', 'r', 'e', 'e' });
		
		System.out.println("-----------字符串拷贝测试-------------");
		System.out.println("字符串1:-------------------------------");
		System.out.println(str.value);
		System.out.println("字符串2:-------------------------------");
		System.out.println(str2.value);
		
		MyString.strCopy(str, str2, 1, 3, 1);
		System.out.println("把字符串2中第二至第四个字符拷贝进字符串第二位字符后:---------------------------");
		System.out.println(str.value);
		
		assertEquals(true,finalStr.equalsString(str));
		
	}
}
