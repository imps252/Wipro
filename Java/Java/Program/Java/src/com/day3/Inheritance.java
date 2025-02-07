//Design a class hierarchy for a Vehicle base class and its derived classes Car and Bike. 
//Add attributes and methods specific to each. 
//Demonstrate the concept of inheritance by overriding a method.




package com.day3;

class Vehicle{
	
	void fuel() {
        System.out.println("This vehicle can use both Petrol and Diesel.");
	}
	
}

class Bike extends Vehicle{
	void fuel() {
		System.out.println("petrol");
	}
	
}

class Car extends Vehicle{
	void fuel() {
		System.out.println("diesel");
	}
	
}

public class Inheritance {
	public static void main(String[] args) {
	//	Car obj = new Car();
	//	obj.c();
	//	obj.fuel();

		Vehicle v=new Vehicle();
		v.fuel();
		
	}

}
