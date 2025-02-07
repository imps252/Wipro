//Create an interface Animal with methods sound() and eat().
//Implement this interface in classes Dog and Cat.



package com.day3;

interface Animal{
	void sound();
	void eat();
}

class Leo implements Animal{

	@Override
	public void sound() {
		System.out.println("Gurr");
		// TODO Auto-generated method stub
		
	}


	@Override
	public void eat() {
		System.out.println("Bloody sweet");
	// TODO Auto-generated method stub
	
	}
	
}

class Hyena implements Animal{

	@Override
	public void sound() {
		
	System.out.println("Laughs");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eat() {
		System.out.println( "Meat" );
	}
		// TODO Auto-generated method stub
		
	}

public class Interface {
	public static void main(String[] args) {
	Animal obj=new Leo();
	Animal obji=new Hyena();

	
	obj.sound();
	obj.eat();
	
	obji.sound();
	obji.eat();
	
	}

}
