package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ParkingStationController {
	int currentId = 1;
	private static server.sqlConnection sql = server.sqlConnection.getInstant();

	public static List<String> getParkingIDs() {
		java.sql.PreparedStatement stmt;
		List<String> results = new ArrayList<String>();
		try {
			stmt = sql.conn.prepareStatement("SELECT parking_address FROM ParkingStation");

			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			rs.beforeFirst();
			while(rs.next()) {
				results.add(rs.getString(1));
				//			    System.out.println(rs.getString(1));
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
		return results;
	}

	public void addWorkerToParking(){

	}
	public void setParkingDirector(){

	}
	public int getSoltStatus(){
		return 0;
	}
	/**
	 * remove car from occupied slot
	 * @param parkId
	 * @param carId
	 */
	public static void removeCar(int parkId,int carId){
		java.sql.PreparedStatement updatestmt = null;

		try{

			updatestmt =  sql.conn.prepareStatement("UPDATE ParkingStationSlots SET ParkingStationSlot_status = 0,car_ID='' WHERE parking_id = ? AND car_ID = ? LIMIT 1");
			updatestmt.setInt(1, parkId);
			updatestmt.setInt(2, carId);
			updatestmt.executeUpdate();
		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}
	/**
	 * insert Car to available Slot
	 * @param parkId
	 * @param endTime
	 * @param carId
	 * @return true if there is available Slot
	 */
	public static boolean insertCar(int parkId,LocalDateTime endTime,int carId){
		java.sql.PreparedStatement stmt =null,updatestmt = null;
		int availableSlot = 0,orderSlot = 0;
		try {
			stmt =  sql.conn.prepareStatement("SELECT count(*) FROM ParkingStationSlots WHERE parking_id = ? AND ParkingStationSlot_status = 0");
			stmt.setInt(1,parkId);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				availableSlot = rs.getInt(1);
			}

			stmt =  sql.conn.prepareStatement("SELECT count(*) FROM orders WHERE order_parking_id = ? AND start_date >= NOW() AND start_date <= ?");
			stmt.setInt(1,parkId);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String endT = endTime.format(formatter);
			stmt.setString(2,endT);
			rs = stmt.executeQuery();

			if (rs.next()) {
				orderSlot = rs.getInt(1);
			}

			//TODO: check for orders
			if((availableSlot - orderSlot)>0){
				updatestmt =  sql.conn.prepareStatement("UPDATE ParkingStationSlots SET ParkingStationSlot_status = 1, car_ID = ? WHERE parking_id = ? AND ParkingStationSlot_status = 0 LIMIT 1");
				updatestmt.setInt(1, carId);
				updatestmt.setInt(2, parkId);
				updatestmt.executeUpdate();
				return true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Reseve Parking staion slot 
	 * @param parkId
	 * @param level
	 * @param row
	 * @param col
	 */
	public void setResevedSlot(int parkId,int level,int row,int col){
		java.sql.PreparedStatement stmt = null;


		try {
			stmt =  sql.conn.prepareStatement("UPDATE ParkingStationSlots SET ParkingStationSlot_status = 3 WHERE parking_id = ? AND level = ? AND row= ? AND col= ?");
			stmt.setInt(1, parkId);
			stmt.setInt(2, level);    
			stmt.setInt(3, row);    
			stmt.setInt(4, col);   
			int rs = stmt.executeUpdate();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}    

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			/* ignored */}
		}
	}

	/**
	 * set parking station slot as out of order
	 * @param parkId
	 * @param level
	 * @param row
	 * @param col
	 */
	public void setOutOfOrderSlot(int parkId,int level,int row,int col){
		java.sql.PreparedStatement stmt = null;


		try {
			stmt =  sql.conn.prepareStatement("UPDATE ParkingStationSlots SET ParkingStationSlot_status = 2 WHERE parking_id = ? AND level = ? AND row= ? AND col= ?");
			stmt.setInt(1, parkId);
			stmt.setInt(2, level);    
			stmt.setInt(3, row);    
			stmt.setInt(4, col);   
			int rs = stmt.executeUpdate();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}    

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			/* ignored */}
		}
	}
	/**
	 * add new parking staion to DB
	 * @param address
	 * @param director
	 * @param size
	 * @return true if DB add success
	 */
	public boolean addParkingStaion(String address,Worker director,int size){
		Statement stmt;

		//add ParkingStation to db
		try {
			stmt = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs = stmt.executeQuery("SELECT * FROM ParkingStation");
			uprs.moveToInsertRow();
			//uprs.updateString("parking_id", String.valueOf(currentId));
			uprs.updateString("parking_address", address);
			uprs.updateString("parking_size", String.valueOf(size));
			uprs.insertRow();

			uprs.last();
			int id = uprs.getInt("parking_id");
			int [] prices = {5,4,(72*4)};
			String [] type = {"OCCASIONAL","IN_ADVANCE", "SUBSCRIBED"}; 

			uprs = stmt.executeQuery("SELECT * FROM order_prices"); //create new park prices
			for(int i=0;i<3;i++){
				uprs.moveToInsertRow();
				uprs.updateInt("parking_id", id);
				uprs.updateString("order_type", type[i]);
				uprs.updateDouble("order_price_per_hour", prices[i]);
				uprs.insertRow();
			}


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
		} catch (SQLException e) {
			e.printStackTrace();
		}

		setupSlots(currentId, size);
		currentId++;
		return(true);
	}

	/**
	 * add all parking slot of new parking staion to DB
	 * @param parkId
	 * @param size
	 */
	public void setupSlots(int parkId,int size){
		Statement stmt;

		try {
			stmt = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs = null;
			for(int level=1;level<4;level++){
				for(int row=1;row<4;row++){
					for(int col=1;col<size+1;col++){
						uprs = stmt.executeQuery("SELECT * FROM ParkingStationSlots");
						uprs.moveToInsertRow();
						uprs.updateInt("parking_id", parkId);
						uprs.updateInt("level", level);
						uprs.updateInt("row", row);
						uprs.updateInt("col", col);
						uprs.updateInt("car_ID", 0);
						uprs.updateInt("ParkingStationSlot_status", 0);
						uprs.insertRow();
						uprs.moveToCurrentRow();
					}
				}
			}
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

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * return all slot status of given park station in form of [level][row][column]
	 * @param parkId
	 * @return 3d array options are 0=AVAILABLE 1=OCCUPIED 2=OUT_OF_ORDER 3=RESERVED
	 */
	public static int [][][] getSlotStatus(int parkId){
		java.sql.PreparedStatement stmt =null;
		int parkSize = 0;
		int[][][] result = null; 
		try {
			stmt =  sql.conn.prepareStatement("SELECT parking_size FROM ParkingStation WHERE parking_id = ?");
			stmt.setInt(1,parkId);


			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				parkSize = rs.getInt(1);
			}

			if(parkSize>0){
				result = new int [3][3][parkSize];
			}
			stmt =  sql.conn.prepareStatement("SELECT * FROM ParkingStationSlots WHERE parking_id = ?");
			stmt.setInt(1, parkId);
			ResultSet rs_slots = stmt.executeQuery();

			if (!rs_slots.next()) {
				return null;
			}
			rs_slots.beforeFirst();
			while(rs_slots.next()) {
				result[rs_slots.getInt("level")-1][rs_slots.getInt("row")-1][rs_slots.getInt("col")-1] = rs_slots.getInt("ParkingStationSlot_status");
			}
			return result;

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	public static boolean checkAvilablePlace(int parkId,String startTime,String endTime){
		java.sql.PreparedStatement stmt =null,updatestmt = null;
		int availableSlot = 0,orderSlot = 0;
		try {
			stmt =  sql.conn.prepareStatement("SELECT count(*) FROM ParkingStationSlots WHERE parking_id = ? AND (ParkingStationSlot_status = 0 OR ParkingStationSlot_status = 1)");
			stmt.setInt(1,parkId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				availableSlot = rs.getInt(1);
			}

			stmt =  sql.conn.prepareStatement("SELECT count(*) FROM orders WHERE order_parking_id = ? AND start_date <= ? AND ? <= end_date");
			stmt.setInt(1,parkId);
			stmt.setString(2, endTime);
			stmt.setString(3,startTime);

			rs = stmt.executeQuery();

			if (rs.next()) {
				orderSlot = rs.getInt(1);
			}

			//TODO: check for orders
			if((availableSlot - orderSlot)>0){
				System.out.println((availableSlot - orderSlot));
				return true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	/**
	 * return parking id of a given order
	 * @param orderId
	 * @return integer
	 */
	public static int getParkId(int orderId) {
		int id = -1;
		PreparedStatement stmt;
		try {
			stmt = sql.conn.prepareStatement("SELECT order_parking_id FROM orders WHERE order_id = ?");
			stmt.setInt(1, orderId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			} else {
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return id;

	}
	
	
	/**
	 * Change Parking prices 
	 * @param parking_id
	 * @param order_type
	 * @param order_price_per_hour - double
	 *
	 */
	public String setParkingPrices(String parking_id,String order_type, Double order_price_per_hour){
		java.sql.PreparedStatement stmt = null;


		try {
			stmt =  sql.conn.prepareStatement("UPDATE order_prices SET order_price_per_hour = ? WHERE parking_id = "+ parking_id +" order_type = " + order_type);
			stmt.setDouble(1, order_price_per_hour);
			 
			int rs = stmt.executeUpdate();
			if (rs==0)
			{
				return ("error updating prices");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}    

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			/* ignored */}
		}
		return ("Price was updated succussfully");
	}


}