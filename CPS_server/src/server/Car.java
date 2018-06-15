package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Car {
	String carID;
	private static sqlConnection sql = sqlConnection.getInstant();

	public Car(String carID, String ownerID) {
		this.carID = carID;
		Car.addNewCarToClient(ownerID, carID);
	}

	public static String addNewCarToClient(String clientID, String carID) {
		Statement stmt;
		try {
			stmt = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet car = stmt.executeQuery("SELECT * FROM cars WHERE car_ID=" + carID + ";");
			if (car.next()) {
				return ("car already exists");
			}
			ResultSet client = stmt.executeQuery("SELECT * FROM cars WHERE client_ID=" + clientID + ";");
			if (client.next()) {
				ResultSet uprs = stmt.executeQuery("SELECT * FROM cars");
				uprs.moveToInsertRow();
				uprs.updateString("client_ID", clientID);
				uprs.updateString("car_ID", carID);

				uprs.insertRow();

				System.out.println("New car was added succsfully");

				if (uprs != null) {
					try {
						uprs.close();
					} catch (SQLException e) {
						/* ignored */}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						/* ignored */}
				}
			} else {
				System.out.println("Client does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ("New car was added succsfully");
	}

	public static String getClientCarsById(String id) {
		Statement stmt;
		String return_res = "";
		try {
			stmt = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs = stmt.executeQuery("SELECT * FROM cars WHERE client_ID=" + id + ";");
			while (rs.next()) {
				// Print out the values
				return_res += (rs.getString(1)) + " "; // client id
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

	public String getOwner() {
		//return echoServer.getCarOwnerByCarId(carID);
		return "";
	}
}
