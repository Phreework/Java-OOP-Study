package com.study.ATM;

public class ATMDemo {
	public static void main(String[] args) {
		ATM atm = new ATM();
		ICBC card1 = new ICBC();
		ABC card2 = new ABC();
		atm.insertCard(card1);
		atm.insertCard(card2);
	}
	
}
