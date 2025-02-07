//Write a program that includes multiple catch blocks to handle exceptions

//like ArrayIndexOutOfBoundsException and NumberFormatException.



package com.day3;
import java.util.Scanner;



public class MultipleCatch {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		try {
			int[] a = new int[4];
            System.out.println(a[4]);
            
            System.out.print("Enter a number: ");
            String input = scanner.nextLine();
      
		}

		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		}
		
		catch (NumberFormatException e) {
			System.out.println("name"+e);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		
		
	}

}
