package com.study.my;

class MyString {

	char[] value;

	public MyString(char[] value) {
		this.value = value; 
	}

	/**
	 * 判断字符串是否相等
	 * @param obj
	 * @return
	 */
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
	
	/**
	 * 输出成[a,b,c,d]形式
	 * @return
	 */
	public char[] arraysToString() {
		char[] str = new char[value.length*2 + 1];
		str[0] = '[';
		str[str.length - 1] = ']';
		for(int i = 0; i<value.length; i++) {
			str[i*2+1] = value[i];
			if(i != value.length-1)
				str[i*2+2] = ',';
		}
		return str;
	}
	
	public char[] subString(int beginIndex,int endIndex) {
		char[] str = new char[endIndex-beginIndex+1];
		for(int i = 0; i <str.length; i++) {
			str[i] = value[beginIndex + i];
		}
		return str;
	}

}
