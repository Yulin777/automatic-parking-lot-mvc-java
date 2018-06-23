package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class lateOrderCheck  implements Runnable{

	@Override
	public void run() {
		System.out.println("[auto] start check for late orders");
		server.sqlConnection sql = server.sqlConnection.getInstant();
		java.sql.PreparedStatement stmt =null;

		try {
			stmt =  sql.conn.prepareStatement("SELECT * FROM orders,cars WHERE order_car_id = car_ID AND order_status = 'PENDING' AND  (TIMESTAMPDIFF(MINUTE,NOW(),start_date))<0");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Statement statement = sql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet uprs = statement.executeQuery("SELECT * FROM Messages WHERE messages_text = 'you are late to order "+rs.getString("order_id")+"'");
					if(!uprs.next()){
						uprs.moveToInsertRow();
						uprs.updateString("messages_text", "you are late to order "+rs.getString("order_id"));
						uprs.updateInt("messages_confirmation", 0);
						uprs.insertRow();
						System.out.println("[auto] send late message to client "+rs.getString("client_ID"));
					}
					else{
						stmt =  sql.conn.prepareStatement("SELECT * FROM orders,cars WHERE order_car_id = car_ID AND order_status = 'PENDING' AND  (TIMESTAMPDIFF(MINUTE,NOW(),start_date))<-29");
						
						 rs = stmt.executeQuery();
					}
			}
			stmt.close();
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	
		
	}
	

}
