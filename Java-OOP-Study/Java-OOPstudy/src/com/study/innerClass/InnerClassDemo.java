package com.study.innerClass;

public class InnerClassDemo {

	public static void main(String[] args) {
		Mama mama = new Mama();
//		Baby baby = new Baby();�ڲ�����ⲻ�߱��ɼ���
		System.out.println(mama.name);
		System.out.println(mama.baby.name);
		
		Mama.Baby baby = mama.new Baby();
		baby.sayHi();

	}
}

//�ڲ���ɷ����ⲿ�࣬�ⲿ�಻�ɷ����ڲ����Ա

//�ⲿ��
class Mama {
	String name = "̷����";
	Baby baby = new Baby();
	//�ڲ���
	class Baby{
		String name = "������";
		void sayHi() {
			String name = "����";
			System.out.println(name);
			System.out.println(this.name);
			System.out.println(Mama.this.name);
		}
	}
}