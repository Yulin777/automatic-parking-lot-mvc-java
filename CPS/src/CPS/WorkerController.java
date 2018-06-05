package CPS;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class WorkerController {
	
	private String worker_Fname;
	private String worker_Lname;
	private String worker_email;
	private String worker_password;
	private String worker_phone;
	private String worker_access_level;

	
	//TODO Evgeny
	 public static Worker getWorker(String email, String password)
	 {
		 
		 
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
	 
	 
	 
}
