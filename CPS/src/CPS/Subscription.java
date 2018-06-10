package CPS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import client.EchoServer;

public class Subscription {
	EchoServer echoServer;

	public Subscription(String cliendID, String carID, Timestamp startDate, Timestamp endDate) {
		if (echoServer == null)
			echoServer = new EchoServer(EchoServer.DEFAULT_PORT);
		
		Subscription.addNewSubscription(cliendID, carID, startDate, endDate);
	}
	

	public static String addNewSubscription(String cliendID, String carID, java.sql.Timestamp startDate, java.sql.Timestamp endDate) {
		Statement stmt;
		try {
			stmt = EchoServer.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet c = stmt.executeQuery("SELECT * FROM clients WHERE client_ID=" + cliendID + ";");
			if (!c.next()) {
				System.err.println("no client with such id");
				return ("no client with such id");
			}
			ResultSet client = stmt.executeQuery("SELECT * FROM subscriptions WHERE client_ID=" + cliendID + ";");
			if (!client.next()) {
				ResultSet uprs = stmt.executeQuery("SELECT * FROM subscriptions");
				uprs.moveToInsertRow();
				uprs.updateString("client_ID", cliendID);
				uprs.updateString("car_ID", carID);
				uprs.updateTimestamp("start_date", startDate);
				uprs.updateTimestamp("end_date",  endDate);

				uprs.insertRow();

				System.out.println("New subscription was added succsfully");

				if (uprs != null) {
					try {
						uprs.close();
					} catch (SQLException e) {
						/* ignored */}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						/* ignored */}
				}
			} else {
				return ("client already has subscription");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ("New subscription was added succsfully");
	}

}
