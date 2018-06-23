package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimerTask;
/**
 * NonRespondedComplaintsAutoCheck
 * checks for one day old messgess
 *
 */
public class NonRespondedComplaintsAutoCheck implements Runnable {
	
	@Override
	public void run() {
		System.out.println("[auto] checking for unanswered complaints");

		server.sqlConnection sql = server.sqlConnection.getInstant();
		java.sql.PreparedStatement stmt =null,stmt2=null;

		try {
			//locating unanswered complaints:
			stmt =  sql.conn.prepareStatement("SELECT * FROM complaints WHERE response IS NULL AND DATEDIFF(NOW(),date) > 0");
			ResultSet rs = stmt.executeQuery();
			//selecting custumer service rep
			stmt2 =  sql.conn.prepareStatement("SELECT * FROM workers WHERE worker_type = 'CustomerService';");
			ResultSet rs2 = stmt2.executeQuery();
			rs2.next();//moving to result
		
			
			while(rs.next())//while there are unanswered complaints:: 
			{
				Statement statement = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet uprs = statement.executeQuery("SELECT * FROM workersMessages");
					uprs.moveToInsertRow();
					uprs.updateString("messages_text", "Conplaint ID:"+rs.getString("complaint_number")+" is one day old and still has no response!!");
					uprs.updateInt("messages_confirmation", 0);
					uprs.updateString("worker_ID", rs2.getString("worker_id"));//assigning the unresponded compliants bot id
					uprs.insertRow();
					System.out.println("[auto] there are unresponded complainets");
			}
			stmt.close();
			rs.close();
			rs2.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
