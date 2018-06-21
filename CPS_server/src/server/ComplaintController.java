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
	 * @return true for successful addition
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
	
	public static String assignAttendant(String ComplaintID, String attendantID)
	 {
		Statement stmt;
		try {
			stmt = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet c = stmt.executeQuery("SELECT * FROM complaints WHERE complaint_number=" + ComplaintID + ";");
			if (!c.next()) {
				System.err.println("There is no complaint with such id");
				return ("There is no complaint with such id");
			}
//			ResultSet complaint = stmt.executeQuery("SELECT * FROM complaints WHERE complaint_number=" + ComplaintID + ";");
//			if (!complaint.next()) {
				ResultSet uprs = stmt.executeQuery("SELECT * FROM complaints");
				uprs.moveToInsertRow();
				uprs.updateString("attendant_ID", attendantID);
				uprs.insertRow();

				System.out.println("Attendant " + attendantID + " was assigned successfully to complaint number "+ ComplaintID);

				if (uprs != null) {
					try {
						uprs.close();
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
//			} else {
//				return ("client already has subscription");
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ("Attendant " + attendantID + " was assigned successfully to complaint number "+ ComplaintID);
	}

	
	
}
