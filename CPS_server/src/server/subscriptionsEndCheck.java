package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimerTask;

public class subscriptionsEndCheck implements Runnable {

	@Override
	public void run() {
		System.out.println("[auto] start check for finished subscriptions");

		server.sqlConnection sql = server.sqlConnection.getInstant();
		java.sql.PreparedStatement stmt =null;

		try {
			stmt =  sql.conn.prepareStatement("SELECT * FROM subscriptions WHERE DATEDIFF(end_date, NOW())=7");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Statement statement = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet uprs = statement.executeQuery("SELECT * FROM Messages");
					uprs.moveToInsertRow();
					uprs.updateString("messages_text", "your subscription ends in a week");
					uprs.updateInt("messages_confirmation", 0);
					uprs.updateString("client_ID", rs.getString("client_ID"));

					uprs.insertRow();
					System.out.println("[auto] send subscriptions end message to client "+rs.getString("client_ID"));
			}
			stmt.close();
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
