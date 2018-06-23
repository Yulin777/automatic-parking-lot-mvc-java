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
//		System.out.println("complaint controller-complaitn desc: "+ description);
		boolean flag = false;
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
	
	/**
	 *  assigning a worker to the compalint
	 * @param ComplaintID 
	 * @param attendantID - worker id
	 * @return
	 */
	public static String assignAttendantToComlaint(String ComplaintID, String attendantID)
	 {
		java.sql.PreparedStatement stmt = null;


		try {
			stmt =  sql.conn.prepareStatement("UPDATE complaints SET attendant_ID=? WHERE complaint_number=" + ComplaintID + ";");
			stmt.setString(1, attendantID);
			int rs = stmt.executeUpdate();
			if (rs==0)
			{
				return ("error assigning attendent to complaint");
			}
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}    

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			/* ignored */}
		}

		return ("Attendant " + attendantID + " was assigned successfully to complaint number "+ ComplaintID);
	}

	/**
	 * writing a response to the complaint
	 * @param ComplaintID
	 * @param response
	 * @return
	 */
	public static String respondToCompalint(String ComplaintID, String response)
	 {
		Statement stmt;
		java.sql.PreparedStatement prpdstmt = null;
		try {
			stmt = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet c = stmt.executeQuery("SELECT * FROM complaints WHERE complaint_number=" + ComplaintID + ";");
			if (!c.next())
			{
				System.err.println("There is no complaint with such id");
				return ("There is no complaint with such id");
			}
		}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
			


			try {
				prpdstmt =  sql.conn.prepareStatement("UPDATE complaints SET response=? WHERE complaint_number=" + ComplaintID + ";");
				prpdstmt.setString(1, response);
				int rs = prpdstmt.executeUpdate();
				if (rs==0)
				{
					return ("error responding to complaint");
				}
				
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}    

			if (prpdstmt != null) {
				try {
					prpdstmt.close();
				} catch (SQLException e) {
				/* ignored */}
			}

		return ("Response was added successfully to complaint number ");
	}
	
	/**
	 * adding a compensation of needed to a complaint
	 * @param ComplaintID
	 * @param compnsationAmount
	 * @return
	 */
	public static String addCompensationToCompalint(String ComplaintID, float compnsationAmount)
	 {
		Statement stmt;
		java.sql.PreparedStatement prpdstmt = null;
		try {
			stmt = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet c = stmt.executeQuery("SELECT * FROM complaints WHERE complaint_number=" + ComplaintID + ";");
			if (!c.next())
			{
				System.err.println("There is no complaint with such id");
				return ("There is no complaint with such id");
			}
			
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					/* ignored */
				}
			}
		}
		catch (SQLException e)
		{
						System.out.println(e.getMessage());
		}
				
		try {
			prpdstmt =  sql.conn.prepareStatement("UPDATE complaints SET compensation=? WHERE complaint_number=" + ComplaintID + ";");
			prpdstmt.setFloat(1, compnsationAmount);
			int rs = prpdstmt.executeUpdate();
			if (rs==0)
			{
				return ("error adding compensation to complaint");
			}
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}    

		if (prpdstmt != null) {
			try {
				prpdstmt.close();
			} catch (SQLException e) {
			/* ignored */}
		}
		return ("Compensation was added successfully to complaint");
	}
	
	
}
