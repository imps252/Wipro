package com.day3;

class InvalidException extends Exception {
	InvalidException (String message){
		super(message);
	}
	
}
public class CustomExceptions {
	
	public static void main(String[] args) {
		int age = 18;
		try {
			System.out.println(age);
			if(age<20) {
				throw new InvalidException("Age is invalid");
			}
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

}
