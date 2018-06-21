package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class ComplaintController 
{
	private static server.sqlConnection sql = server.sqlConnection.getInstant();
	

	/**
	 * Adding a new complaint to the db
	 * @param client_id - id of client who submitted the complaint
	 * @param description - the complaint itself
	 * @return true for successfull addition
	 */
	public static boolean addNewComplaint(String client_id, String description) {
		boolean flag = false;
//		PreparedStatement stmt;
		try {
				Statement statement = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet uprs = statement.executeQuery("SELECT * FROM complaints");
				uprs.moveToInsertRow();
				uprs.updateString("client_ID",client_id );
				uprs.updateString("description",description );
				uprs.insertRow();

				System.out.println("New complaint was added successfully");
				flag = true;

				uprs.close();
				statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
