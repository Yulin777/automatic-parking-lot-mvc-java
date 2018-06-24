package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import server.Customer;
import server.CustomerController;
import server.OrderController;
import server.ParkingStation;
import server.ParkingStationController;
import server.Worker;
import server.WorkerController;

public class Client {
	public Client() {
	}
	static String ip = "localhost";
	/**
	 * call CustomerController login function
	 * @param id
	 * @param password
	 * @return customer if succeed
	 */
	public Customer customerLogin(String id, String password) {
		Customer c = null;

		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("login client " + id + " " + password);
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			c = (Customer) ois.readObject();
			if (c != null)
				System.out.println("[response] customer " + c.getFirstName() + " " + c.getLastName() + " login Succeed");
			else
				System.out.println("[response] customer not found");

			socket.close();
		} catch (IOException ioe) {
			System.out.println("login error");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * call server WorkerController login function
	 * @param email
	 * @param password
	 * @param type
	 * @return Worker if succeed
	 */
	public Worker workerLogin(String email, String password, Worker.WorkerType type) {
		Worker w = null;

		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("login worker " + email + " " + password + " " + type.name());
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			w = (Worker) ois.readObject();
			if (w != null)
				System.out.println("[response] worker " + w.getFirstName() + " " + w.getLastName() + " login Succeed");
			else
				System.out.println("[response] worker not found");

			socket.close();
		} catch (IOException ioe) {
			System.out.println("IOException exption");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return w;
	}

	/**
	 * call server ParkingStationController getParkingIDs function 
	 * @return list of all ParkingStation name
	 */
	public static List<String> getStations() {
		List<String> stations = new ArrayList<String>();

		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("get stations");
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			stations = (List<String>) ois.readObject();
			if (stations != null)
				System.out.println("[response] get stations succeed");
			else
				System.out.println("[response] get stations failed");

			socket.close();
		} catch (IOException ioe) {
			System.out.println("IOException exption");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return stations;
	}
	/**
	 * call server OrderController startParking function
	 * @param carID
	 * @param clientID
	 * @return true if update db succeed
	 */
	public boolean startParking(String carID, String clientID) {
		boolean res = false;

		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("start parking " + carID + " " + clientID);
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			res = (boolean) ois.readObject();
			if (res)
				System.out.println("[response] start parking succeed");
			else
				System.out.println("[response] start parking failed");

			socket.close();
		} catch (IOException ioe) {
			System.out.println("IOException exption");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * 
	 * @param id
	 * @param car_number
	 * @param car_park
	 * @param email
	 * @param start_date
	 * @param start_time
	 * @param end_date
	 * @param end_time
	 * @return
	 */
	public int AdvanceOneTimeOrder(String id, String car_number, String car_park, String email, LocalDate start_date,
			String start_time, LocalDate end_date, String end_time) {
		int result;

		// TODO: input validation
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // parse start and end time
		String start = start_date.toString() + " " + start_time;
		String end = end_date.toString() + " " + end_time;
		try {
			Date s = df.parse(start);
			Date e = df.parse(end);

			Timestamp startTime = new Timestamp(s.getTime());
			Timestamp endTime = new Timestamp(e.getTime());

			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("order temporery " + id + " " + car_number + " " + car_park + " " + email + " " + startTime + " "
					+ endTime);
			pw.flush();
			socket.close();

		} catch (Exception e) {
			System.out.println("time convert fail");
		}

		return 0;
	}
	/**
	 * call CustomerController addNewClient function
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param type
	 * @param email
	 * @param phone
	 * @return true if add to db succeed
	 */
	public boolean addNewCustomer(String id, String firstName, String lastName, String password, Customer.type type,
			String email, String phone) {
		boolean flag = false;

		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("add client " + id + " " + firstName + " " + lastName + " " + password + " " + type.toString() + " " + email
					+ " " + phone);
			pw.flush();
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			flag = (boolean) ois.readObject();
			if (flag) {
				System.out.println("[response] customer " + firstName + " " + lastName + " creation Succeed");
			} else
				System.out.println("[response] could not add customer");

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * call Car addNewCarToClient function
	 * @param carId
	 * @param customerId
	 * @return true if add to db succeed
	 */
	public boolean addNewCar(String carId, String customerId) {
		boolean flag = false;
		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("add car " + carId + " " + customerId);
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			flag = (boolean) ois.readObject();

			if (flag) {
				System.out.println("[response] car " + carId + " was added successfully");
			} else
				System.out.println("[response] car was not found");

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * call OrderController addOccasionalOrder function
	 * @param car_id
	 * @param end_time
	 * @param car_park
	 * @param payMethod
	 * @return true if add to db succeed
	 */
	public int addOccasionalOrder(String car_id, Timestamp end_time, String car_park, String payMethod) {
		int res = -1;
		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("add occasional " + car_id + " " + end_time.toString() + " " + car_park+ " " + payMethod);
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			res = (int) ois.readObject();

			if (res>0) {
				System.out.println("[response] order was added successfully");
			} else
				System.out.println("[response] adding order was failed");

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * call OrderController addInAdvanceOrder function
	 * @param car_id
	 * @param start_time
	 * @param end_time
	 * @param parkingName
	 * @param paymentMethod
	 * @return true if add to db succeed
	 */
	public int addInAdvanceOrder(String car_id, Timestamp start_time, Timestamp end_time, String parkingName, String paymentMethod) {
		int res = -1;
		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("add advanced " + car_id + " " + start_time.toString() + " " + end_time.toString() + " " + parkingName + " " + paymentMethod);
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			res = (int) ois.readObject();

			if (res>0) {
				System.out.println("[response] in advance order was added successfully");
			} else
				System.out.println("[response] adding in advance order was failed");

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	//================complaint handle============================================

	/**
	 * client function - adding new complaint
	 *
	 * @param client_id
	 * @param description - the complaint itself
	 * @return true for success
	 */
	public boolean addNewComplaint(String client_id, String description) {
		boolean c = false;
		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("add complaint " + client_id + " " + description);
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			c = (boolean) ois.readObject();
			if (c == true)
				System.out.println("[response] new complaint was added");
			else
				System.out.println("[response] error adding new complaint");

			socket.close();
		} catch (IOException ioe) {
			System.out.println("adding new complaint error");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * client function - assigning attendant(worker) to complaint
	 *
	 * @param ComplaintID
	 * @param attendantID
	 * @return true for success
	 */

	public boolean assignAttendantToCOmplaint(String ComplaintID, String attendantID) {
		boolean c = false;
		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("assign attendant " + ComplaintID + " " + attendantID);
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			c = (boolean) ois.readObject();
			if (c == true)
				System.out.println("[response] Attendant assigned to complaint ");
			else
				System.out.println("[response] error assigning attendant to complaint");

			socket.close();
		} 
		catch (IOException ioe) 
		{
			System.out.println("assigning to complaint error");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return c;
	}


	/**
	 * client function - respond to complaint
	 *
	 * @param ComplaintID
	 * @param response
	 * @return true for success
	 */
	public boolean respondToCompalint(String ComplaintID, String response) {
		boolean c = false;
		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("respond compalint " + ComplaintID + " " + response);
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			c = (boolean) ois.readObject();
			if (c == true)
				System.out.println("[response] complaint responded ");
			else
				System.out.println("[response] error respondeing to complaint");

			socket.close();
		} catch (IOException ioe) {
			System.out.println("responding to complaint error");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * client function - add compensation to complaint
	 *
	 * @param ComplaintID
	 * @param compnsationAmount
	 * @return true for success
	 */
	public boolean addCompensationToCompalint(String ComplaintID, float compnsationAmount) {
		boolean c = false;
		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("add compensation " + ComplaintID + " " + Float.toString(compnsationAmount));
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			c = (boolean) ois.readObject();
			if (c == true)
				System.out.println("[response] compensation added ");
			else
				System.out.println("[response] error adding compensation");

			socket.close();
		} catch (IOException ioe) {
			System.out.println("adding compensation error");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}

	//==============================end of compliant handling=====================================

	/**
	 * call OrderController orderOngoingExist function
	 * @param car_id
	 * @param client_id
	 * @return  true if update db succeed
	 */
	public boolean endParking(String car_id, String client_id) {
		boolean res = false;
		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("end parking " + car_id + " "+ client_id);
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			res = (boolean) ois.readObject();
			if (res)
				System.out.println("[response] end parking succeeded");
			else
				System.out.println("[response] end parking failed");

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public double getEndPrice(String car_id) {
		double res = Double.MAX_VALUE;
		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("get endPrice " + car_id);
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			res = (double) ois.readObject();
			if (res != Double.MAX_VALUE)
				System.out.println("[response] get price succeeded");
			else
				System.out.println("[response] get price failed");

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * call OrderController calcPriceOnEndOrder function
	 * @param car_id
	 * @return price
	 */
	public double getPrice(int order_id) {
		double res = Double.MAX_VALUE;
		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("get Price " + String.valueOf(order_id));
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			res = (double) ois.readObject();
			if (res != Double.MAX_VALUE)
				System.out.println("[response] get price succeeded");
			else
				System.out.println("[response] get price failed");

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * call ParkingStaionController getParkSoltStatus function
	 * @param parkId
	 * @return 3d array options are 0=AVAILABLE 1=OCCUPIED 2=OUT_OF_ORDER 3=RESERVED
	 */
	public int[][][] getParkSoltStatus(int parkId) {
		int[][][] res = null;
		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("get slots " + String.valueOf(parkId));
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			res = (int[][][]) ois.readObject();
			if (res != null)
				System.out.println("[response] get park solt status succeeded");
			else
				System.out.println("[response] get park solt status failed");

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * call OrderController cancelOrder function
	 * @param orderId
	 * @return price of cancel order
	 */
	public double cancelOrder(int orderId) {
		double res = Double.MAX_VALUE;
		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("cancel order " + String.valueOf(orderId));
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			res = (double) ois.readObject();
			if (res != Double.MAX_VALUE)
				System.out.println("[response] order canceled succeessfully");
			else
				System.out.println("[response] order cancel failed");

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}

	/**
	 * call OrderController getOrderStatus function
	 * @param orderId
	 * @return status of orderId
	 */
	public String getOrderStatus(int orderId) {
		String res = "";
		try {
			Socket socket = new Socket(ip, 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("get OrderStatus " + String.valueOf(orderId));
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			res = (String) ois.readObject();
			if (res != null)
				System.out.println("[response] order Status "+res);
			else
				System.out.println("[response] order not found");

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}

	/**
	 * call CustomerController logout function
	 * @param id
	 */
	public void logoutClient(String id){
		Socket socket;
		try {
			socket = new Socket(ip, 8080);

			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("logout client " + id);
			pw.flush();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * call ParkingStaionController setResevedSlot function
	 * @param parkId
	 * @param level
	 * @param row
	 * @param col
	 */
	public void setResevedSlot(int parkId,int level,int row,int col){
		Socket socket;
		try {
			socket = new Socket(ip, 8080);

			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("reseved slot " +String.valueOf(parkId)+" "+ String.valueOf(level)+" "+ String.valueOf(row)+" "+  String.valueOf(col));
			pw.flush();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * call ParkingStaionController setOutOfOrderSlot function
	 * @param parkId
	 * @param level
	 * @param row
	 * @param col
	 */
	public void setOutOfOrderSlot(int parkId,int level,int row,int col){
		Socket socket;
		try {
			socket = new Socket(ip, 8080);

			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("outOfOrder slot " +String.valueOf(parkId)+" "+ String.valueOf(level)+" "+ String.valueOf(row)+" "+  String.valueOf(col));
			pw.flush();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * call ParkingStaionController addParkingStaion function
	 * @param address
	 * @param size
	 * @return true if update db succeed
	 */
	public boolean addParkingStaion(String address,int size){
		Socket socket;
		boolean res = false;
		try {
			socket = new Socket(ip, 8080);

			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("add ParkingStaion " +address+" "+ String.valueOf(size));
			pw.flush();
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			res = (boolean) ois.readObject();
			if (res != true)
				System.out.println("[response] addParkingStaion succeed");
			else
				System.out.println("[response] addParkingStaion fail");

			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;

	}

	/**
	 * call OrderController responseToMessage function
	 * @param response
	 * @param messages_id
	 */
	public void responseToMessage(int response,int messages_id){
		Socket socket;
		try {
			socket = new Socket(ip, 8080);

			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("response Message " +String.valueOf(response)+" "+ String.valueOf(messages_id));
			pw.flush();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//	public boolean updateParkingStaionPrices(String parking_id,String order_type, Double order_price_per_hour){
	//		Socket socket;
	//		boolean res = false;
	//		try {
	//			socket = new Socket(ip, 8080);
	//
	//			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
	//			PrintWriter pw = new PrintWriter(osw);
	//			pw.println("ParkingStaion updatePrices " + parking_id +" "+ order_type +" "+ String.valueOf(order_price_per_hour));
	//			pw.flush();
	//			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
	//			res = (boolean) ois.readObject();
	//			if (res != true)
	//				System.out.println("[response] parking staion price update succes");
	//			else
	//				System.out.println("[response] parking staion price update failure");
	//
	//			socket.close();
	//		} catch (UnknownHostException e) 
	//		{
	//			e.printStackTrace();
	//		}
	//		catch (IOException e)
	//		{
	//			e.printStackTrace();
	//		}
	//		catch (ClassNotFoundException e) {
	//			e.printStackTrace();
	//		}
	//		return res;
	//
	//	}
	
	/**
	 * call WorkerController updatePrices function
	 * @param worker_id
	 * @param occasional
	 * @param advanced
	 * @param subscribed
	 * @return true if update db succeed
	 */
	public boolean updateParkingStaionPrices(String worker_id,String occasional, String advanced, String subscribed){
		Socket socket;
		boolean res = false;
		try {
			socket = new Socket(ip, 8080);

			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("ParkingStaion updatePrices " + worker_id +" "+ occasional +" "+ advanced +" "+ subscribed);
			pw.flush();
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			res = (boolean) ois.readObject();
			if (res)
				System.out.println("[response] parking staion price update success");
			else
				System.out.println("[response] parking staion price update failure");

			socket.close();
		} catch (UnknownHostException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return res;

	}

	/**
	 * if the ceo wants to approve the praking price change
	 * this function is used..
	 * @param parking_id
	 * @param order_type
	 * @param order_price_per_hour
	 * @return
	 */
	public boolean updateParkingStaionPricesCeoApprove(String parking_id,String order_type,String yesORno){
		Socket socket;
		boolean res = false;
		try {
			socket = new Socket(ip, 8080);

			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("ParkingStaion updatePricesCeoApprov " + parking_id +" "+ order_type +" "+ yesORno);
			pw.flush();
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			res = (boolean) ois.readObject();
			if (res != true)
				System.out.println("[response] parking staion price update approve succes");
			else
				System.out.println("[response] parking staion price update approve failure");

			socket.close();
		} catch (UnknownHostException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return res;

	}
	
	/**
	 * call WorkerController checkWorkerStaion function
	 * @param worker_id
	 * @return name of WorkerStaion
	 */
	public  String checkWorkerStaion(String worker_id){
		Socket socket;
		String res = null;
		try {
			socket = new Socket(ip, 8080);

			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("Worker Staion " +worker_id);
			pw.flush();
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			res = (String) ois.readObject();
			if (res != null)
				System.out.println("[response] checkWorkerStaion "+res);
			else
				System.out.println("[response] checkWorkerStaion fail");

			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}



