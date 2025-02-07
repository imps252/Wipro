
package task3;

import java.util.Scanner; 



public class Task03 {
	
	public static void main(String[] args) {
		
		//int n=10;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a number");
		int n = scanner.nextInt();
		
		
		boolean prime = true;
		
		for(int i=2;i<n;i++)
		{
			if(n%i==0) {
				prime = false;
				break;
			}
			
			
			}
		if(prime) {
			System.out.println(n+ " is prime");

			
		}
		else
		{
			System.out.println(n+ " is not prime");

		}
	}

}
