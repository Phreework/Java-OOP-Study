package com.study.innerClass;
/*
 * �����ڲ���
 */
public class AnonymousInnerClassDemo {
public static void main(String[] args) {
	Inner1 i1 = new Inner1Imp1();
	
	/**
	 * 1.д��һ��ʵ���࣬ʵ��Inner1�ӿ�
	 * 2.����ʵ���ഴ����һ������
	 * 3.��ʵ���������
	 */
	final String name = "123";
	Inner1 i2 = new Inner1() {
		public void sayHi() {
			System.out.println(name);
		}
	};
	i2.sayHi();
	
}

}

interface Inner1{
	public void sayHi();
}

class Inner1Imp1 implements Inner1{

	@Override
	public void sayHi() {
		// TODO Auto-generated method stub
		
	}
	
}