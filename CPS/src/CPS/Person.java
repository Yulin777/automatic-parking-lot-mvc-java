package CPS;

import client.EchoServer;

public class Person {
	EchoServer echoServer;
	String pid;

	/**
	 * @param pid
	 *            the id of the person
	 * @param panme
	 *            the name of the person
	 */
	public Person(String id, String firstName, String lastName, String password, String type, String email,
			String telephone) {
		if (echoServer == null)
			echoServer = new EchoServer(EchoServer.DEFAULT_PORT);
		this.pid = id;

		if (this.getClass().getName().equals("CPS.Customer"))
			echoServer.addNewClient(id, firstName, lastName, password, type, email, telephone);
		else
			echoServer.addNewWorker(id, firstName, lastName, password, type, email, telephone);

	}

	public String getPid() {
		return this.pid;
	}

	public boolean login() {
		return false;
	}

}
