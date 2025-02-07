package com.task4;

public class Task01 {

	
	String name;
	int rollnum;
	
	static String clgname = "abc";
	
	public static void student() {
		System.out.println(clgname);

		
	}
	
	public static void main(String[] args) {
		
		
		Task01 s = new Task01();
		
		s.name="ps";
		s.rollnum=112;
		System.out.println(s.name );
		System.out.println(s.rollnum);
		
		Task01.student();
		

		
	}
}
