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

	public static String getClientById(String id) {
		Statement stmt;
		String return_res = null;
		try {
			stmt = EchoServer.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs = stmt.executeQuery("SELECT * FROM clients WHERE client_ID=" + id + ";");
			while (rs.next()) {
				// Print out the values
				return_res = (rs.getString(1) + "  " + rs.getString(2) + " " + rs.getString(3) + " , " + rs.getString(4)
						+ " , " + rs.getString(5) + " , " + rs.getString(6));
			}

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					/* ignored */}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return return_res;
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
