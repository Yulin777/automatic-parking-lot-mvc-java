package CPS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import client.EchoServer;

public class Customer extends Person {
	ArrayList<Car> cars;

	public Customer(String id, String firstName, String lastName, String password, String type, String email,
			String telephone) {
		super(id, firstName, lastName, password, type, email, telephone);
		cars = new ArrayList<Car>();
	}

	public int orderParking() {
		return 0;
	}

	public boolean subscribe() {
		return false;
	}

	// TODO: return type is Unknown
	public void trackOrder() {

	}

	public ArrayList<Car> getCars() {
		return cars;
	}

	public void addCar(Car car) {
		cars.add(car);
	}

	public boolean placeOrder() {
		return false;
	}

}
