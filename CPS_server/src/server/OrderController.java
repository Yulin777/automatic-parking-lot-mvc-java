package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;

public class OrderController {
	public enum OrderStatus {
		PENDING, ONGOING, COMPLETE, CANCELED;
	}

	public enum OrderType {
		//todo edit more statuses
		OCCASIONAL, IN_ADVANCE;
	}

	public enum PaymentMethod {
		//todo edit more methods
		CREDIT, CASH;
	}

	private static server.sqlConnection sql = server.sqlConnection.getInstant();


	//todo change
	public boolean addNewClient(String id, String firstName, String lastName, String password, String type,
								String email, String telephone) {
		boolean flag = false;
		PreparedStatement stmt;
		try {
			stmt = sql.conn.prepareStatement("SELECT * FROM clients WHERE client_ID=?");  // createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, id);
			ResultSet client = stmt.executeQuery();
			if (!client.next()) {
				Statement statement = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet uprs = statement.executeQuery("SELECT * FROM clients");
				uprs.moveToInsertRow();
				uprs.updateString("client_ID", id);
				uprs.updateString("client_first_name", firstName);
				uprs.updateString("client_last_name", lastName);
				uprs.updateString("client_type", type);
				uprs.updateString("client_email", email);
				uprs.updateString("client_telephone", telephone);
				uprs.updateString("client_password", password);
				uprs.insertRow();

				System.out.println("New client was added succsfully");
				flag = true;

				if (uprs != null) {
					try {
						uprs.close();
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
			} else {
				System.out.println("Client already exists");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static String addNewSubscription(String cliendID, String carID, java.sql.Timestamp startDate,
											java.sql.Timestamp endDate) {
		Statement stmt;
		try {
			stmt = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet c = stmt.executeQuery("SELECT * FROM clients WHERE client_ID=" + cliendID + ";");
			if (!c.next()) {
				System.err.println("no client with such id");
				return ("no client with such id");
			}
			ResultSet client = stmt.executeQuery("SELECT * FROM subscriptions WHERE client_ID=" + cliendID + ";");
			if (!client.next()) {
				ResultSet uprs = stmt.executeQuery("SELECT * FROM subscriptions");
				uprs.moveToInsertRow();
				uprs.updateString("client_ID", cliendID);
				uprs.updateString("car_ID", carID);
				uprs.updateTimestamp("start_date", startDate);
				uprs.updateTimestamp("end_date", endDate);

				uprs.insertRow();

				System.out.println("New subscription was added succsfully");

				if (uprs != null) {
					try {
						uprs.close();
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
			} else {
				return ("client already has subscription");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ("New subscription was added succsfully");
	}

	public static boolean addOccasionalOrder(String carID, String endDate) {
		boolean flag = false;
		PreparedStatement stmt;
		try {
			stmt = sql.conn.prepareStatement("SELECT * FROM orders WHERE order_car_id=?");
			stmt.setString(1, carID);
			ResultSet client = stmt.executeQuery();
			if (!client.next()) {
				Statement statement = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet uprs = statement.executeQuery("SELECT * FROM orders");
				uprs.moveToInsertRow();
				uprs.updateString("order_status", OrderStatus.ONGOING.toString());
				uprs.updateString("order_car_id", carID);
				uprs.updateString("order_type", OrderType.OCCASIONAL.toString());
				uprs.updateString("end_date", endDate);
				uprs.insertRow();

				System.out.println("New order was added succsfully");
				flag = true;

				uprs.close();
				stmt.close();
			} else {
				System.out.println("order already exists for current car");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean addInAdvanceOrder(String carID, String startDate, String endDate) {
		boolean flag = false;
		PreparedStatement stmt;
		try {
			stmt = sql.conn.prepareStatement("SELECT * FROM orders WHERE order_car_id=?");
			stmt.setString(1, carID);
			ResultSet client = stmt.executeQuery();
			if (!client.next()) {
				Statement statement = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet uprs = statement.executeQuery("SELECT * FROM orders");
				uprs.moveToInsertRow();
				uprs.updateString("order_status", OrderStatus.PENDING.toString());
				uprs.updateString("order_car_id", carID);
				uprs.updateString("order_type", OrderType.IN_ADVANCE.toString());
				uprs.updateString("start_date", startDate);
				uprs.updateString("end_date", endDate);
				uprs.insertRow();

				System.out.println("New order was added succsfully");
				flag = true;

				uprs.close();
				stmt.close();
			} else {
				System.out.println("order already exists for current car");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * check for Available Order of given car in current time
	 * @param parkId
	 * @param carId
	 * @return true if there is order
	 */
	public boolean checkAvailableOrder(int parkId,int carId){
		java.sql.PreparedStatement stmt =null;
		int numOfOrders = 0;
			try {
				stmt =  sql.conn.prepareStatement("SELECT count(*) FROM orders WHERE order_parking_id = ? AND order_car_id	= ? AND start_date <= NOW() AND end_date > NOW()");
			stmt.setInt(1,parkId);
			stmt.setInt(2,carId);

			
			ResultSet rs = stmt.executeQuery();
		      if (rs.next()) {
		    	  numOfOrders = rs.getInt(1);
		      }
		      
			if(numOfOrders>0){
				return true;
			}
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	}

}
