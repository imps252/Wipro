package com.java1;

class code1 {
	
	int n;
	String m;
	
	static String name ="pns";
	
	
	code1(int n,String m){
		this.n=n;
		this.m=m;
	}
	
	void display() {
		System.out.println(n+ " " +m+ " " +name);
	}
	
	public static void main(String[] args) {
		code1 ob1 = new code1(1,"ps");
		code1 ob2 = new code1(1,"ps");

		ob1.display();
		ob2.display();
		
		code1.display();
		
		
	}
	  
}
	

