package server;

import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class CustomerController {

	private static server.sqlConnection sql = server.sqlConnection.getInstant();
	static ArrayList<String> loggedCustomers = new ArrayList<String>();
	public CustomerController() {
	}

	public boolean addNewClient(String id, String firstName, String lastName, String password, String type,
			String email, String telephone) {
		boolean flag = false;
		PreparedStatement stmt;
		try {
			stmt = sql.conn.prepareStatement("SELECT * FROM clients WHERE client_ID=?");  // createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, id);
			ResultSet client = stmt.executeQuery();
			if (!client.next()) {
				Statement statement = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet uprs = statement.executeQuery("SELECT * FROM clients");
				uprs.moveToInsertRow();
				uprs.updateString("client_ID", id);
				uprs.updateString("client_first_name", firstName);
				uprs.updateString("client_last_name", lastName);
				uprs.updateString("client_type", type);
				uprs.updateString("client_email", email);
				uprs.updateString("client_telephone", telephone);
				uprs.updateString("client_password", password);
				uprs.insertRow();

				System.out.println("New client was added succsfully");
				flag = true;

				uprs.close();
				stmt.close();
			} else {
				System.out.println("Client already exists");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag; //Customer(id, firstName, lastName, password, type, email, telephone);
	}

	public static Customer getClientById(String id) {
		java.sql.PreparedStatement stmt;
		Customer return_res = null;
		try {
			stmt = sql.conn.prepareStatement("SELECT * FROM clients WHERE client_ID = ?");
			stmt.setString(1, id);

			System.out.println("SELECT * FROM clients WHERE client_ID=" + id + ";");

			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				//return null;
			}
			//rs.next();
			//while (rs.next()) {
			// Print out the values
			return_res = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), "");

			//}

			if (rs != null) {
				try {
					rs.close();
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

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return return_res;
	}

	public static boolean removeCustomer(String id) {
		int res = 0; 
		java.sql.PreparedStatement stmt;
		boolean return_res = false;
		try {
			stmt = sql.conn.prepareStatement("DELETE FROM `Group_1`.`clients` WHERE `clients`.`client_ID` = ?");
			stmt.setString(1, id);
			res = stmt.executeUpdate(); 

			if(res==1) {
				System.out.println("client deleted successfully");
				return_res = true;
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return return_res;
	}

	public Customer login(String id, String password) {
		java.sql.PreparedStatement stmt;
		Customer return_res = null;
		if(!checkIfLogin(id)){
			try {
				stmt = sql.conn.prepareStatement("SELECT * FROM clients WHERE client_ID = ? AND client_password = ?");
				stmt.setString(1, id);
				stmt.setString(2, password);

				ResultSet rs = stmt.executeQuery();
				if (!rs.next()) {
					return null;
				}
				return_res = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				loggedCustomers.add(id);
				if (rs != null) {
					try {
						rs.close();
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

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return return_res;
	}
	public void logout(String id) {
		loggedCustomers.remove(id);
	}

	private boolean checkIfLogin(String id){
		for(String c:loggedCustomers){
			if(c.equals(id))
				return true;
		}
		return false;
	}
}
