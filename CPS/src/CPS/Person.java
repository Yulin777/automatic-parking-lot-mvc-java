package CPS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			CustomerController.addNewClient(id, firstName, lastName, password, type, email, telephone);
		else
			Person.addNewWorker(id, firstName, lastName, password, type, email, telephone);

	}

	public static String addNewWorker(String id, String firstName, String lastName, String password, String type, String email,
			String telephone) {
		Statement stmt;
		try {
			stmt = EchoServer.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet worker = stmt.executeQuery("SELECT * FROM workers WHERE worker_ID=" + id + ";");
			if (!worker.next()) {
				ResultSet uprs = stmt.executeQuery("SELECT * FROM workers");
				uprs.moveToInsertRow();
				uprs.updateString("worker_ID", id);
				uprs.updateString("worker_Fname", firstName);
				uprs.updateString("worker_Lname", lastName);
				uprs.updateString("worker_type", type);
				uprs.updateString("worker_email", email);
				uprs.updateString("worker_phone", telephone);
				uprs.updateString("worker_password", password);

				uprs.insertRow();

				System.out.println("New worker was added succsfully");

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
				System.out.println("Worker already exists");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ("New worker was added succsfully");
	}

	public String getPid() {
		return this.pid;
	}

	public boolean login() {
		return false;
	}

}
