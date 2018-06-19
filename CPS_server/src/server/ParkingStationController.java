package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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