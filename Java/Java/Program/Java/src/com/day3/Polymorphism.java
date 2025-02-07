//Implement method overloading for a 
//class named Calculator to perform 
//addition of two integers, three integers, and two double values.



package com.day3;

class Calculator{
	void add(int a, int b) {
		System.out.println(a+b);
			
	}
	
	void add(int a,int b, int c) {
		System.out.println(a+b+c);
	
	}
	
	void add(double a, double b) {
		System.out.println(a+b);
			
	}
	
}

public class Polymorphism {
	public static void main(String[] args) {
		Calculator obj = new Calculator();
		obj.add(10, 300);
		obj.add(5.5, 10.5);
	}

}
