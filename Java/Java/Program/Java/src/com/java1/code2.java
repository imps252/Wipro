package com.java1;

public class code2 {
	
	// Instance variables
    int rollno;
    String name;

    // Static variable
    static String city;

    // Static method
    static void demo() {
        System.out.println("hi");
    }

    // Instance method to calculate and return the sum
    int sum() {
        int z = 20 + 50;
        return z;
    }

    // Main method
    public static void main(String[] args) 
    {
        // Call the static method
        code2.demo();

        // Print the static variable (will print null if not initialized)
        System.out.println(code2.city);

        // Create an object of the Code2 class
        code2 c = new code2();

        // Print the instance variable (will print null if not initialized)
        System.out.println(c.name);

        // Call the sum method and print the result
        int x = c.sum();
        System.out.println(x);
        
    }   


}
