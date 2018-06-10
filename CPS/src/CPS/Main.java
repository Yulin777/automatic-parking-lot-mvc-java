package CPS;

public class Main {

	public static void main(String[] args) {
		Person p = new Customer("2222", "aa", "aa", "1", "1", "1", "1");
		Person w = new Worker("2222", "aa", "aa", "1", "1");
		Person a = new Worker("1234", "ab", "cd", "5", "6");
		Car c = new Car("0101", "1");
		System.out.println(c.getOwner());
	}
}
