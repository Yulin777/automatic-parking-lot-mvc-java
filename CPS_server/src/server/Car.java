package server;

import com.mysql.jdbc.PreparedStatement;

import javax.xml.transform.sax.SAXSource;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Car implements Serializable {
	private static final long serialVersionUID = 1L;
	public String ownerID;
	public String carID;
	private static sqlConnection sql = sqlConnection.getInstant();

	public Car(String carID, String ownerID) {
		this.ownerID = ownerID;
		this.carID = carID;
//        addNewCarToClient(ownerID, carID);
	}

	public boolean addNewCarToClient() {
		java.sql.PreparedStatement stmt;
		try {
			stmt = sql.conn.prepareStatement("SELECT * FROM cars WHERE car_ID=?");  // createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, carID);


			ResultSet car = stmt.executeQuery();
			if (car.next()) {
				System.err.println("car already exists");
				return false;
			}
			Statement statement = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet newCar = statement.executeQuery("SELECT * FROM cars;");
			newCar.moveToInsertRow();
			newCar.updateString("client_ID", ownerID);
			newCar.updateString("car_ID", carID);
			newCar.insertRow();
			if (car != null) {
				try {
					car.close();
				} catch (SQLException e) {
					/* ignored */
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					/* ignored */
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("New car was added succefully");
		return true;
	}
	
	public static String getClientId(String carID) {
		Statement stmt;
		String return_res = "";
		try {
			stmt = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("SELECT client_ID FROM cars WHERE car_ID=" + carID + ";");
			
			if (rs.next()) {
				return_res += (rs.getString(1)); // client id
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return return_res;
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
					/* ignored */
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					/* ignored */
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return return_res;
	}
}
