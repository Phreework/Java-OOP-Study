package com.study.my;

class MyString {

	char[] value;

	public MyString(char[] value) {
		this.value = value; 
	}

	public boolean equalsString(Object obj) {

		if (this == obj) return true; 					// 若是自身
		if (!(obj instanceof MyString)) return false; 	// 若类型不同

		MyString str = (MyString) obj;
		int length = this.value.length;

		if (length != str.value.length) return false;	// 判断长度关系
		
		char v1[] = this.value;
		char v2[] = str.value;
		int i = 0;
		while (length-- != 0) { 						// 比较每一个字符
			if (v1[i] != v2[i]) return false;
			i++;
		}
		return true; 									// 相等

	}

}
