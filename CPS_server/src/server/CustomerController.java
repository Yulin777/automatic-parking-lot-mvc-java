package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class CustomerController {

    private static server.sqlConnection sql = server.sqlConnection.getInstant();

    public CustomerController() {

    }

    public Customer addNewClient(String id, String firstName, String lastName, String password, String type,
                                 String email, String telephone) {
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

                if (uprs != null) {
                    try {
                        uprs.close();
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
            } else {
                System.out.println("Client already exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Client added successfully");
        return new Customer(id, firstName, lastName, password, type, email, telephone);
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

    public static String addNewSubscription(String cliendID, String carID, java.sql.Timestamp startDate,
                                            java.sql.Timestamp endDate) {
        Statement stmt;
        try {
            stmt = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

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
                uprs.updateTimestamp("end_date", endDate);

                uprs.insertRow();

                System.out.println("New subscription was added succsfully");

                if (uprs != null) {
                    try {
                        uprs.close();
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
            } else {
                return ("client already has subscription");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ("New subscription was added succsfully");
    }

    public Customer login(String email, String password) {
        java.sql.PreparedStatement stmt;
        Customer return_res = null;
        try {
            stmt = sql.conn.prepareStatement("SELECT * FROM clients WHERE client_email = ? AND client_password = ?");
            stmt.setString(1, email);
            stmt.setString(2, password);


            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return_res = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), "");

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

    public boolean addCarToCustomer(int sid, int carNumber, int customerPid) {
        return false;
    }

    public boolean removeCar(int customerPid, int CarNumber) {
        return true;
        // test
    }

}
