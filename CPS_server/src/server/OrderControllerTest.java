package server;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class OrderControllerTest {


	@Test
	public void addOccasionalOrdertest() {
		int res = OrderController.addOccasionalOrder("123", "2018-07-07 08:05:31", "TelAviv", "CREDIT");
		assertFalse("check add Occasional Order should succeed",(res==-1));
	}
	@Test
	public void getOrderParkingIdtest() {
		int id = OrderController.getOrderParkingId("Haifa");
		assertEquals("check getOrderParkingId should return 2",id,2);
	}
	@Test
	public void addInAdvanceOrdertest() {
		int id = OrderController.addInAdvanceOrder("123","2018-07-07 08:05:31","2018-07-07 10:05:31","TelAviv","CREDIT");
		assertFalse("check addInAdvanceOrdertest should return !=-1",(id==-1));
	}
	@Test
	public void OrderOverlapstest() {
		boolean res = OrderController.OrderOverlaps("gdf","2018-06-24 11:11:00","2018-06-25 23:00:00","Haifa");
		assertEquals("check OrderOverlapstest should be overlap",res,true);
	}
}
