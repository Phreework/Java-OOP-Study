package com.study.innerClass;
/*
 * 匿名内部类
 */
public class AnonymousInnerClassDemo {
public static void main(String[] args) {
	Inner1 i1 = new Inner1Imp1();
	
	/**
	 * 1.写了一个实现类，实现Inner1接口
	 * 2.给该实现类创建了一个对象
	 * 3.该实现类的类体
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