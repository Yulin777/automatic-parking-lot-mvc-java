package server;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	String pid;
	String id; 
	String firstName;
	String lastName;
	String password; 
	String type;
	String email;
	String telephone;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	
	//private static sqlConnection sql; = sqlConnection.getInstant();

	/**
	 * @param pid
	 *            the id of the person
	 * @param panme
	 *            the name of the person
	 */
	public Person(String id, String firstName, String lastName, String password, String type, String email,
			String telephone) {
	
		this.pid = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.type = type;
		this.email = email;
		this.telephone = telephone;

	/*	if (this.getClass().getName().equals("CPS.Customer"))
			CustomerController.addNewClient(id, firstName, lastName, password, type, email, telephone);
		else
			Person.addNewWorker(id, firstName, lastName, password, type, email, telephone);*/

	}

	/*public static String addNewWorker(String id, String firstName, String lastName, String password, String type, String email,
			String telephone) {
		Statement stmt;
		try {
			stmt = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

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
						 ignored }
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						 ignored }
				}
			} else {
				System.out.println("Worker already exists");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ("New worker was added succsfully");
	}*/

	public String getPid() {
		return this.pid;
	}

	public boolean login() {
		return false;
	}

}
