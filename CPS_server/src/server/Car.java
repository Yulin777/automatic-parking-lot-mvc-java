package server;

import com.mysql.jdbc.PreparedStatement;

import javax.xml.transform.sax.SAXSource;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		ResultSet car = null;
		try {
			stmt = sql.conn.prepareStatement("SELECT * FROM cars WHERE car_ID=?");  // createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, carID);


			car = stmt.executeQuery();

			Statement statement = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet newCar = statement.executeQuery("SELECT * FROM cars;");
			newCar.moveToInsertRow();
			newCar.updateString("client_ID", ownerID);
			newCar.updateString("car_ID", carID);
			newCar.insertRow();

			car.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			return false;
		}
		try {
			System.out.println(car.next() ? "car already exists" : "New car was added succefully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static String getClientId(String carID) {
		Statement stmt;
		String return_res = "";
		try {
			stmt = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("SELECT client_ID FROM cars WHERE car_ID=\"" + carID + "\";");

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

//	public static String getClientCarsById(String id) {
//		Statement stmt;
//		String return_res = "";
//		try {
//			stmt = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//			
//			ResultSet rs = stmt.executeQuery("SELECT * FROM cars WHERE client_ID=" + id + ";");
//			while (rs.next()) {
//				// Print out the values
//				return_res += (rs.getString(1)) + " "; // client id
//			}
//			
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					/* ignored */
//				}
//			}
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (SQLException e) {
//					/* ignored */
//				}
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return return_res;
//	}

	public static List<String> getClientCarsById(String client_id) {
		java.sql.PreparedStatement stmt;
		List<String> results = new ArrayList<String>();
		try {
			stmt = sql.conn.prepareStatement("SELECT car_ID FROM cars WHERE client_ID = ?");
			stmt.setString(1, client_id);

			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			rs.beforeFirst();
			while (rs.next()) {
				results.add(rs.getString(1));
			}

			rs.close();
			stmt.close();
			if (rs != null) {
				try {
				} catch (Exception e) {
					/* ignored */
				}
			}
			if (stmt != null) {
				try {
				} catch (Exception e) {
					/* ignored */
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}

	public static boolean removeCar(String car_id) {
		int res = 0;
		java.sql.PreparedStatement stmt;
		boolean return_res = false;
		try {
			stmt = sql.conn.prepareStatement("DELETE FROM `Group_1`.`cars` WHERE `cars`.`car_ID` = ?");
			stmt.setString(1, car_id);
			res = stmt.executeUpdate();

			if (res == 1) {
				System.out.println("car deleted successfully");
				return_res = true;
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return return_res;
	}

}
