package CPS;

import java.sql.Timestamp;

public class Main {
	public static void main(String[] args) {
//		Person p = new Customer("2222", "aa", "aa", "1", "1", "1", "1");
//		Person w = new Worker("2222", "aa", "aa", "1", "1");
//		Person a = new Worker("1234", "ab", "cd", "5", "6");
//		Car c = new Car("0101", "1");
//		System.out.println(c.getOwner());
		int myYear = 2019;
		int myMonth = 3;
		int myDay = 24;
		java.sql.Timestamp ts = java.sql.Timestamp.valueOf(
		        java.time.LocalDate.of(myYear, myMonth, myDay).atStartOfDay()
		);
		Subscription s = new Subscription("1","123",new Timestamp(System.currentTimeMillis()), ts);
	}
}
