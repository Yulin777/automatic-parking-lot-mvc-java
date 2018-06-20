package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class ParkingStationController {
	int currentId = 0;
	private static server.sqlConnection sql = server.sqlConnection.getInstant();


	public void addWorkerToParking(){

	}
	public void setParkingDirector(){

	}
	public int getSoltStatus(){
		return 0;
	}

	/**
	 * insert Car to available Slot
	 * @param parkId
	 * @param endTime
	 * @param carId
	 * @return true if there is available Slot
	 */
	public boolean insertCar(int parkId,LocalDate endTime,int carId){
		java.sql.PreparedStatement stmt =null,updatestmt = null;
		int availableSlot = 0;
			try {
				stmt =  sql.conn.prepareStatement("SELECT count(*) FROM ParkingStationSlots WHERE parking_id = ? AND ParkingStationSlot_status = 0");
			stmt.setInt(1,parkId);
			
			ResultSet rs = stmt.executeQuery();
		      if (rs.next()) {
		    	  availableSlot = rs.getInt(1);
		      }
		      
		    //TODO: check for orders
			if(availableSlot>0){
				updatestmt =  sql.conn.prepareStatement("UPDATE ParkingStationSlots SET ParkingStationSlot_status = 1, car_ID = ? WHERE parking_id = ? AND ParkingStationSlot_status = 0 LIMIT 1");
				updatestmt.setInt(1, carId);
				updatestmt.setInt(2, parkId);
				updatestmt.executeUpdate();
				return true;
			}
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
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
	 * set parking staion slot as out of order
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
				// TODO Auto-generated catch block
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
		ResultSet staion = stmt.executeQuery("SELECT * FROM ParkingStation WHERE parking_id=" + currentId + ";");
		if (!staion.next()){
			ResultSet uprs = stmt.executeQuery("SELECT * FROM ParkingStation");
			uprs.moveToInsertRow();
			uprs.updateString("parking_id", String.valueOf(currentId));
			uprs.updateString("parking_address", address);
			uprs.updateString("parking_size", String.valueOf(size));
			uprs.insertRow();

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
			return (false);
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
			for(int col=1;col<4;col++){
				for(int row=1;row<size+1;row++){
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
}