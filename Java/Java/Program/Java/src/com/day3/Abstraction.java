//Create an abstract class Shape with an abstract method calculateArea().
//Implement this method in subclasses Circle and Rectangle.



package com.day3;

abstract class C {
	abstract void area();
}

class Circle extends C{
	
	double radius = 5;
	
	void area(){
		double area = Math.PI * radius * radius;
		System.out.println(area);
		
	}

}

class Rectangle extends C{
	
	int length = 10;
	int widht =20;
	
	void area() {
		double area= length * widht;
		System.out.println(area);
	}
}

public class Abstraction {
	public static void main(String[] args) {
		
		C obj1 = new Circle();
		C obj2 = new Rectangle();
		
		obj1.area();
		obj2.area();	
		
	}

		
}
