package com.day3;

public class Book {
	
	static String title;
	static String author;
	static int price;
	
	public static void displayDetails() {
		System.out.println(title+" "+author+" "+price);
	}
	
	public static void main(String[] args) {
		//Book b = new Book();
		//b.title = "Leo";
		//b.author = "Partha";
		//b.price = 2000;
		
		//b.displayDetails();
		
		System.out.println("Book1");
		
		title = "leo";
		author = "Ps";
		price = 100;
		
		displayDetails();
		
		
		
	}
	


}
