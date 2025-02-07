//Design a class Employee with private attributes like name, id, and salary.
//Provide getter and setter methods to access and modify the attributes. Validate the salary to ensure it is positive.




package com.day3;

class Employee{
	
	private int id = 01;
	private String name = "ps";
	private int salary = 2000000;
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
}

public class Encapsulations {

	
	public static void main(String[] args) {
		Employee obj=new Employee();
		System.out.println(obj.getId());
		System.out.println(obj.getName());
		System.out.println(obj.getSalary());

	}

}
