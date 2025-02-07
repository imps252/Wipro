package com.day3;

public class ExceptionHandling {
	
	public static void main(String[] args) {
		
		
		int a=10;
		int b=0;
		
		try {
		System.out.println(a/b);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		//if(a/b != 0 ) {
		//	System.out.println("successfully got ur answer");
		//}
		
		
		
	}
	

}
