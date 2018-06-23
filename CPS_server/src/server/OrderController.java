package server;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class OrderController {
	public enum OrderStatus {
		PENDING, ONGOING;
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


	public static String getOrderStatus(int order_id){
		java.sql.PreparedStatement stmt =null;

		try {
			stmt =  sql.conn.prepareStatement("SELECT * FROM orders WHERE order_id = ?");

		stmt.setInt(1, order_id);
		ResultSet rs = stmt.executeQuery();

		if (!rs.next()) {
			return null;
		}
		return rs.getString("order_status");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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

	public static boolean addOccasionalOrder(String carID, String endDate, String parkingName, String paymentMethod) {
		boolean flag = false;
		PreparedStatement stmt;
		try {
			stmt = sql.conn.prepareStatement("SELECT * FROM orders WHERE order_car_id=?");
			stmt.setString(1, carID);
			ResultSet client = stmt.executeQuery();
			if (!client.next()) {

				stmt = sql.conn.prepareStatement("INSERT INTO orders (order_status,order_car_id,order_type,end_date,order_parking_id) VALUES (?,?,?,?,?)"
						, Statement.RETURN_GENERATED_KEYS);

				stmt.setString(1, OrderStatus.PENDING.toString());
				stmt.setString(2, carID);
				stmt.setString(3, OrderType.IN_ADVANCE.toString());
				stmt.setString(4, endDate);
				stmt.setInt(5, getOrderParkingId(parkingName));
				stmt.executeUpdate();

				ResultSet uprs = stmt.getGeneratedKeys();
				if (uprs.next()) {
					calcAndUpdatePrice(uprs.getInt(1));
				}

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

	public static int getOrderParkingId(String parkingName) {
		int res = -1;
		PreparedStatement stmt;
		try {
			stmt = sql.conn.prepareStatement("SELECT parking_id FROM ParkingStation WHERE parking_address=?;");
			stmt.setString(1, parkingName);
			ResultSet client = stmt.executeQuery();
			if (client.next()) {
				return client.getInt(1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public static boolean addInAdvanceOrder(String carID, String startDate, String endDate, String parkingName, String paymentMethod) {
		boolean flag = true;
		PreparedStatement stmt;
		try {
			stmt = sql.conn.prepareStatement("SELECT * FROM orders WHERE order_car_id=?");
			stmt.setString(1, carID);
			ResultSet client = stmt.executeQuery();
			if (/*!client.next()*/ OrderOverlaps(carID, startDate, endDate, parkingName) == false) {

				stmt = sql.conn.prepareStatement("INSERT INTO orders (order_status,order_car_id,order_type,start_date,end_date,order_parking_id,order_payment_method ) VALUES (?,?,?,?,?,?,?)"
						, Statement.RETURN_GENERATED_KEYS);

				stmt.setString(1, OrderStatus.PENDING.toString());
				stmt.setString(2, carID);
				stmt.setString(3, OrderType.IN_ADVANCE.toString());
				stmt.setString(4, startDate);
				stmt.setString(5, endDate);
				stmt.setInt(6, getOrderParkingId(parkingName));
				stmt.setString(7, paymentMethod);
				stmt.executeUpdate();

				ResultSet uprs = stmt.getGeneratedKeys();
				if (uprs.next()) {
					calcAndUpdatePrice(uprs.getInt(1));
				}

				System.out.println("New order was added successfully");
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

	public static boolean OrderOverlaps(String carID, String startDate, String endDate, String parkingName) {
		java.sql.PreparedStatement stmt = null;
		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			java.util.Date start = (java.util.Date) dateFormat.parse(startDate);
			Timestamp start_date_timestamp = new java.sql.Timestamp(start.getTime());


			dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			java.util.Date end = (java.util.Date) dateFormat.parse(endDate);
			Timestamp end_date_timestamp = new java.sql.Timestamp(end.getTime());


			stmt = sql.conn.prepareStatement("SELECT * FROM orders WHERE order_car_id = ?");
			stmt.setString(1, carID);


			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int numColumns = rs.getMetaData().getColumnCount();
				for (int i = 1; i <= numColumns; i++) {
					// Column numbers start at 1.
					// Also there are many methods on the result set to return
					//  the column as a particular type. Refer to the Sun documentation
					//  for the list of valid conversions.

					Timestamp order_start_time = rs.getTimestamp(7);
					Timestamp order_end_time = rs.getTimestamp(8);

					if (start_date_timestamp.after(order_start_time) && start_date_timestamp.before(order_end_time)) {
						System.err.println("start time overlaps existing order");
						return true;
					}
					if (end_date_timestamp.after(order_start_time) && start_date_timestamp.before(order_end_time)) {
						System.err.println("end time overlaps existing order");
						return true;
					}
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * check for Available Order of given car in current time
	 *
	 * @param parkId
	 * @param carId
	 * @return true if there is order
	 */
	public boolean checkAvailableOrder(int parkId, int carId) {
		java.sql.PreparedStatement stmt = null;
		int numOfOrders = 0;
		try {
			stmt = sql.conn.prepareStatement("SELECT count(*) FROM orders WHERE order_parking_id = ? AND order_car_id	= ? AND start_date <= NOW() AND end_date > NOW()");
			stmt.setInt(1, parkId);
			stmt.setInt(2, carId);


			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				numOfOrders = rs.getInt(1);
			}

			if (numOfOrders > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean startParking(String carID) {
		boolean flag = false;
		int res = 0;
		PreparedStatement stmt;
		try {
			stmt = sql.conn.prepareStatement("SELECT order_id FROM orders WHERE order_car_id = ?");
			stmt.setString(1, carID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String orderID = rs.getString(1);

				stmt = sql.conn.prepareStatement("UPDATE  `Group_1`.`orders` SET  `order_status` =  ? WHERE  `orders`.`order_id` =?;");
				stmt.setString(1, OrderStatus.ONGOING.toString());
				stmt.setInt(2, Integer.valueOf(orderID));
				res = stmt.executeUpdate();
			}
			if (res == 1) {
				System.out.println("parking started succsfully");
				flag = true;
			} else {
				System.err.println("cannot start parking");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean orderOngoingExist(String carID) {
		boolean flag = false;
		PreparedStatement stmt;
		try {
			stmt = sql.conn.prepareStatement("SELECT * FROM `orders` WHERE `order_car_id`=\"" + carID + "\" AND `order_status`=\"ONGOING\"");
			//			stmt.setString(1, carID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				flag = true;
				System.out.println("order ongoing exists");
			} else {
				System.out.println("order ongoing doesnt exist");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean orderPendingExist(String carID) {
		boolean flag = false;
		PreparedStatement stmt;
		try {
			stmt = sql.conn.prepareStatement("SELECT * FROM `orders` WHERE `order_car_id`=\"" + carID + "\" AND `order_status`=\"PENDING\"");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				flag = true;
				System.out.println("order pending exists");
			} else {
				System.out.println("order pending doesnt exist");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	private static double getTimeDiffInHours(Timestamp startTime, Timestamp endTime) {
		long diff = endTime.getTime() - startTime.getTime();
		return diff / 3600000.0;
	}

	public static double getPricePerHour(int parking_id, String type) {
		double price_per_hour = Double.MAX_VALUE;
		PreparedStatement stmt;
		try {
			stmt = sql.conn.prepareStatement("SELECT order_price_per_hour FROM order_prices WHERE parking_id = ? AND order_type = ?");
			stmt.setInt(1, parking_id);
			stmt.setString(2, type);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				price_per_hour = rs.getDouble(1);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return price_per_hour;
	}


	public static double calcPriceOnEndOrder(String carID) {
		double res = Double.MAX_VALUE, price_per_hour = 0, hours = 0;

		PreparedStatement stmt;
		try {
			stmt = sql.conn.prepareStatement("SELECT * FROM orders WHERE order_car_id = ?");
			stmt.setString(1, carID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Timestamp startTime = rs.getTimestamp("start_date");
				Timestamp endTime = rs.getTimestamp("end_date");
				Timestamp nowTime = new Timestamp(System.currentTimeMillis());
				hours = getTimeDiffInHours(startTime, nowTime) - getTimeDiffInHours(startTime, endTime);

				String type = rs.getString(5);
				int parking_id = rs.getInt(6);

				price_per_hour = getPricePerHour(parking_id, type);
				if (price_per_hour != Double.MAX_VALUE) {

					res = hours * price_per_hour;
					System.out.println("price calculated succsfully");
				}
			} else {
				System.out.println("price calculation failed");
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public static void calcAndUpdatePrice(int order_id) {
		double res = Double.MAX_VALUE, price_per_hour = 0, hours = 0;
		PreparedStatement stmt;
		try {
			stmt = sql.conn.prepareStatement("SELECT * FROM orders WHERE order_id = ?");
			stmt.setInt(1, order_id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Timestamp startTime = rs.getTimestamp("start_date");
				Timestamp endTime = rs.getTimestamp("end_date");
				hours = getTimeDiffInHours(startTime, endTime);

				String type = rs.getString(5);
				int parking_id = rs.getInt(6);
				price_per_hour = getPricePerHour(parking_id, type);

				if (price_per_hour != Double.MAX_VALUE) {
					res = hours * price_per_hour;
					stmt = sql.conn.prepareStatement("UPDATE orders SET order_price = ? WHERE order_id = ?");
					stmt.setDouble(1, res);
					stmt.setInt(2, order_id);
					stmt.executeUpdate();

					System.out.println("price " + res + "calculated succsfully");
				}
			} else {
				System.out.println("price calculation failed");
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static boolean removeOrderById(int order_id) {
		java.sql.PreparedStatement stmt;
		boolean return_res = false;
		try {
			stmt = sql.conn.prepareStatement("DELETE FROM orders WHERE order_id = ?");
			stmt.setInt(1, order_id);

			if (stmt.executeUpdate() == 1) {
				System.out.println("order deleted successfully");
				return_res = true;
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return return_res;
	}

	// type ONGOING for end parking, PENDING for cancel
	public static boolean removeOrder(String carID, String type) {
		boolean res = false;
		java.sql.PreparedStatement stmt;
		String clientId = Car.getClientId(carID);
		if (Car.getClientCarsById(clientId).size() == 1) {
			if (CustomerController.removeCustomer(clientId)) {
				res = true;
			}
		} else {
			try {
				stmt = sql.conn.prepareStatement("DELETE FROM orders WHERE order_car_id = ? AND order_status = ?");
				stmt.setString(1, carID);
				stmt.setString(2, type);
				if (stmt.executeUpdate() == 1) {
					res = true;
				}
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (res)
			System.out.println("order removed successfully");
		else
			System.out.println("remove order failed");

		return res;
	}

	public static double cancelOrder(int orderId) {
		PreparedStatement stmt;
		double diff = 0, price = Double.MAX_VALUE;
		try {
			stmt = sql.conn.prepareStatement("SELECT * FROM orders WHERE order_id = ?");
			stmt.setInt(1, orderId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Timestamp startTime = rs.getTimestamp("start_date");
				Timestamp nowTime = new Timestamp(System.currentTimeMillis());
				diff = getTimeDiffInHours(startTime, nowTime);
				price = rs.getDouble(9);

				if (diff <= 1)
					;
				else if (diff < 3 && diff > 1)
					price *= 0.5;
				else
					price *= 0.9;

				removeOrderById(orderId);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return price;
	}
}
