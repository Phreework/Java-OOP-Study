package com.study.my;

class MyString {

	char[] value;

	public MyString(char[] value) {
		this.value = value; 
	}

	public boolean equalsString(Object obj) {

		if (this == obj) return true; 					// ��������
		if (!(obj instanceof MyString)) return false; 	// �����Ͳ�ͬ

		MyString str = (MyString) obj;
		int length = this.value.length;

		if (length != str.value.length) return false;	// �жϳ��ȹ�ϵ
		
		char v1[] = this.value;
		char v2[] = str.value;
		int i = 0;
		while (length-- != 0) { 						// �Ƚ�ÿһ���ַ�
			if (v1[i] != v2[i]) return false;
			i++;
		}
		return true; 									// ���

	}

}
