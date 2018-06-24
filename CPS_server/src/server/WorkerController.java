package server;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WorkerController {

	private static sqlConnection sql = sqlConnection.getInstant();

	/**
	 *
	 * @param id
	 * @param password
	 * @param type
	 * @return the worker on login success - if worker exists with given id, pass and type. null otherwise
	 */
	public Worker login(String id, String password, Worker.WorkerType type) {
		java.sql.PreparedStatement stmt;
		Worker return_res = null;
		try {
			stmt = sql.conn.prepareStatement("SELECT * FROM workers WHERE worker_id = ? AND worker_password = ? AND worker_type = ?");
			stmt.setString(1, id);
			stmt.setString(2, password);
			stmt.setString(3, type.name());

			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			return_res = new Worker(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(7), rs.getString(4), rs.getString(6));

			rs.close();
			stmt.close();


		} catch (Exception e) {
			e.printStackTrace();
		}

		return return_res;
	}

	/**
	 *
	 * @param worker_id
	 * @return parking station of current worker. null if worker does not exists
	 */
	public static String checkWorkerStaion(String worker_id){
		java.sql.PreparedStatement stmt;
		String return_res = null;
		try {
			stmt = sql.conn.prepareStatement("SELECT parking_address FROM workers,ParkingStation WHERE workers.parking_id = ParkingStation.parking_id AND worker_id = ?");
			stmt.setString(1, worker_id);
			
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return null;
			}

			return_res =  rs.getString(1);
			
			rs.close();
			stmt.close();


		} catch (Exception e) {
			e.printStackTrace();
		}

		return return_res;
	}

}
