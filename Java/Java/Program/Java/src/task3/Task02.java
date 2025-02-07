package task3;

public class Task02 {
	
	public static void perimeter(int radius) {
		System.out.println(2 * Math.PI * radius);
	}
	
	public static void circle(int radius) {
		System.out.println(Math.PI * radius * radius);
	}
	
	public static void main(String[] args) {
		perimeter(5);
		circle(10);
	}

}
