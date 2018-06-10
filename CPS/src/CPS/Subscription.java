package CPS;

import java.sql.Timestamp;

import client.EchoServer;

public class Subscription {
	EchoServer echoServer;

	public Subscription(String cliendID, String carID, Timestamp startDate, Timestamp endDate) {
		if (echoServer == null)
			echoServer = new EchoServer(EchoServer.DEFAULT_PORT);
		
		echoServer.addNewSubscription(cliendID, carID, startDate, endDate);
	}
}
