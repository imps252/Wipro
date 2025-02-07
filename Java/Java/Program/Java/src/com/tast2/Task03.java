package com.tast2;

//import java.util.Scanner;

public class Task03 {
	
	public static void main(String[] args) {
		
		//Scanner scanner = new Scanner(System.in);
		//System.out.println("Enter the number");
		//int n = scanner.nextInt();
		
		int n = 10;
		
		int fact = 1;
		
		while (n>0) {
				fact *=n;
				n--;
		}
		System.out.println("Factorial = " + fact);
	}

}
