package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqlConnection {
	public Connection conn;
	private static sqlConnection instant;

	public static sqlConnection getInstant(){
		if(instant==null)
			instant = new sqlConnection();
		return instant;
	}
	private sqlConnection(){
        try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String url = "jdbc:mysql://cs.telhai.ac.il/Group_1";

		String username = "cs311873400";
		String password = "Password1";

		try {
			conn = DriverManager.getConnection(url, username, password);

			System.out.println("SQL connection succeed");
			// conn.close();
		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

}
