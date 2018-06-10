package CPS;

import client.EchoServer;

public class Car {
	String carID;
	EchoServer echoServer;

	public Car(String carID, String ownerID) {
		if (echoServer == null)
			echoServer = new EchoServer(EchoServer.DEFAULT_PORT);

		this.carID = carID;
		echoServer.addNewCarToClient(ownerID, carID);
	}

	public String getOwner() {
		return echoServer.getCarOwnerByCarId(carID);
	}
}
