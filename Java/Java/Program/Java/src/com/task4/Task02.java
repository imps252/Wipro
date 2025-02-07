package com.task4;

public class Task02 {
	
	String accountHolderName;
	int balance;
	
	static String bankName = "sbi";
	
	public static void bank() {
		System.out.println(bankName);
	}
	
	public static void deposit() {
		
	}
	
	public static void withdraw() {
		
	}
	
	public static void main(String[] args) {
		
		Task02 b=new Task02();
		b.accountHolderName="ps";
		b.balance=4321;
		
		System.out.println(b.accountHolderName);
		System.out.println(b.balance);
		
		Task02.bank();
		
		
		
		
	}

}
