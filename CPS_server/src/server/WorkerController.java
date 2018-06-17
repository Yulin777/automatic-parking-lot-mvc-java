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

	public Worker login(String email, String password) {
		java.sql.PreparedStatement stmt;
		Worker return_res = null;
		try {
			stmt =  sql.conn.prepareStatement("SELECT * FROM workers WHERE worker_email = ? AND worker_password = ?");
			stmt.setString(1, email);    
			stmt.setString(2, password);    

			
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
	 		return_res = new Worker(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), "");

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
	
	 
}