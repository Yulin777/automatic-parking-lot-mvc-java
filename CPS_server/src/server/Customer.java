package server;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Customer extends Person implements Serializable {

	public static enum type {
		OCCASIONAL, SUBSCRIBED, ADVANCED
	}


	private static final long serialVersionUID = 1L;
	//ArrayList<Car> cars;

	/**
	 * constructor
	 *
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param type
	 * @param email
	 * @param telephone
	 */
	public Customer(String id, String firstName, String lastName, String password, String type, String email,
					String telephone) {
		super(id, firstName, lastName, password, type, email, telephone);
	}

}
