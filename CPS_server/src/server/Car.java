package server;

import com.mysql.jdbc.PreparedStatement;

import javax.xml.transform.sax.SAXSource;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Car implements Serializable{
    private static final long serialVersionUID = 1L;
    public String carID;
    private static sqlConnection sql = sqlConnection.getInstant();

    public Car(String carID, String ownerID) {
        this.carID = carID;
        addNewCarToClient(ownerID, carID);
    }

    private boolean addNewCarToClient(String clientID, String carID) {
        java.sql.PreparedStatement stmt;
        try {
            stmt = sql.conn.prepareStatement("SELECT * FROM cars WHERE car_ID=?");  // createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, clientID);


            ResultSet car = stmt.executeQuery();
            if (car.next()) {
                System.err.println("car already exists");
                return false;
            }
            Statement statement = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet newCar = statement.executeQuery("SELECT * FROM cars;");
            newCar.moveToInsertRow();
            newCar.updateString("client_ID", clientID);
            newCar.updateString("car_ID", carID);
            newCar.insertRow();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("New car was added succefully");
        return true;
    }

    public static String getClientCarsById(String id) {
        Statement stmt;
        String return_res = "";
        try {
            stmt = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = stmt.executeQuery("SELECT * FROM cars WHERE client_ID=" + id + ";");
            while (rs.next()) {
                // Print out the values
                return_res += (rs.getString(1)) + " "; // client id
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
}
