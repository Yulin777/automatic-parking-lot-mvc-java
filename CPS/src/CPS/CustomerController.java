package CPS;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import client.EchoServer;

public class CustomerController {
    static EchoServer echoServer;

    public CustomerController() {

    }

    public static String addNewClient(String id, String firstName, String lastName, String password, String type,
                                      String email, String telephone) {
        Statement stmt;
        try {
            stmt = EchoServer.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet client = stmt.executeQuery("SELECT * FROM clients WHERE client_ID=" + id + ";");
            if (!client.next()) {
                ResultSet uprs = stmt.executeQuery("SELECT * FROM clients");
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

        return ("New client was added succsfully");
    }

    public static String getClientById(String id) {
        Statement stmt;
        String return_res = null;
        if (echoServer == null)
            echoServer = new EchoServer(EchoServer.DEFAULT_PORT);
        try {
            stmt = EchoServer.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = stmt.executeQuery("SELECT * FROM clients WHERE client_ID=" + id + ";");
            if (!rs.next()) {
                return "no client with such id";
            }
            while (rs.next()) {
                // Print out the values
                return_res = (rs.getString(1) + "  " + rs.getString(2) + " " + rs.getString(3) + " , " + rs.getString(4)
                        + " , " + rs.getString(5) + " , " + rs.getString(6));
            }

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

    public List<String> login(String id, String password) {
        String client = getClientById(id);

        if (client.equals("no client with such id")) {
            return null;
        }

        List<String> items = Arrays.asList(client.split("\\s*,\\s*"));
        if (password.equals(items.get(4))) {
            return items;
        }
        return null;
    }

    public boolean addCarToCustomer(int sid, int carNumber, int customerPid) {
        return false;
    }

    public boolean removeCar(int customerPid, int CarNumber) {
        return true;
        // test
    }

}
