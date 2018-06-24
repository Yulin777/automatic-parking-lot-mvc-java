package server;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.OrderController.OrderType;

public class WorkerController {

	private static sqlConnection sql = sqlConnection.getInstant();

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
	/* public static String getWorker(Connection con,String email, String password) throws GeneralSecurityException
	 {
		 String sha_pass=sha1(password);//generating sha1 from user provided pass string
		 Statement stmt;
		
			try 
			{
				stmt = con.createStatement();//sql
			
				ResultSet rs = stmt.executeQuery("SELECT * FROM workers WHERE worker_email=" + email + ";");
				
				if(rs.next()) //we have at least one result
		 		{
		 			if(rs.getString(5).equals(sha_pass))//check if this is the correct password
		 			{
		 				System.out.println("correct password");

		 				//CPS.Worker.WorkerType wtype= CPS.Worker.WorkerType.values()[rs.getInt(7)]; //convert to enum value
		 				//String workerName=rs.getString(2)+" "+rs.getString(3);//merge two name fields into one
		 				
		 				//return (new Worker(rs.getInt(1),workerName,rs.getString(4),rs.getString(5),rs.getString(6),wtype));//returning new worker
		 				return (new String(rs.getString("worker_id")+" "+rs.getString("worker_Fname")+rs.getString("worker_Lname")+rs.getString("worker_email")+rs.getString("worker_password")+rs.getString("worker_phone")+rs.getString("worker_type")));//returning new worker
		 				
		 			}
		 			else //incorrect password;
		 			{
		 				System.out.println("incorrect pssword");
		 				return null;
		 			}

				} 
		 		
		 	
		 	    if (stmt != null) {
		 	        try {
		 	        	
		 	            stmt.close();
		 	        } 
		 	        catch (SQLException e) { }
		 	    }
		 	   
			} catch (SQLException e) {e.printStackTrace();}
			
		 return null;
	 }
	 
	  static String sha1(String input) throws NoSuchAlgorithmException {
	        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
	        byte[] result = mDigest.digest(input.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < result.length; i++) {
	            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
	        }
	         
	        return sb.toString();
	    }
	 
	  public static void main(String[] args) throws GeneralSecurityException 
	  {
		  System.out.println(sha1("password"));
		  System.out.println(sha1("password1"));
	  }*/

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

	public static boolean updatePrices(String worker_id, double occasional, double advanced, double subscribed){
		java.sql.PreparedStatement stmt;
		boolean return_res = false;
		try {
			stmt = sql.conn.prepareStatement("SELECT parking_id FROM workers WHERE worker_id = ?");
			stmt.setString(1, worker_id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int parking_id = rs.getInt(1);
				stmt = sql.conn.prepareStatement("UPDATE order_prices SET order_price_per_hour = " + occasional +
						" WHERE parking_id = " + parking_id + " AND order_type = \"" + OrderType.OCCASIONAL.toString()+"\"");
				stmt.executeUpdate();
				
				stmt = sql.conn.prepareStatement("UPDATE order_prices SET order_price_per_hour = " + advanced +
						" WHERE parking_id = " + parking_id + " AND order_type = \"" + OrderType.IN_ADVANCE.toString()+"\"");
				stmt.executeUpdate();
				
				stmt = sql.conn.prepareStatement("UPDATE order_prices SET order_price_per_hour = " + subscribed +
						" WHERE parking_id = " + parking_id + " AND order_type = \"" + OrderType.SUBSCRIBED.toString()+"\"");
				stmt.executeUpdate();
				return_res = true;
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return return_res;
	}
	
}
