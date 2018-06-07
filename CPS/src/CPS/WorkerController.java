package CPS;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WorkerController {
	
	private String worker_Fname;
	private String worker_Lname;
	private String worker_email;
	private String worker_password;
	private String worker_phone;
	private String worker_access_level;

	
	//TODO Evgeny
	 public static Worker getWorker(Connection con,String email, String password) throws GeneralSecurityException
	 {
		 String sha_pass=sha1(password);
		 Statement stmt;
			String return_res = null;
			try 
			{
				stmt = con.createStatement();
			
				ResultSet rs = stmt.executeQuery("SELECT * FROM clients WHERE client_ID=" + id + ";");
		 		while(rs.next())
		 		{
		 			return_res=(rs.getString(1)+"  " +rs.getString(2)+" " +rs.getString(3)+" , " +rs.getString(4)
			 			+" , " +rs.getString(5)+" , " +rs.getString(6));
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
	  }
	 
}
