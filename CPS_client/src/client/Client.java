package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import server.Customer;
import server.Worker;

public class Client {
	public Client() {
		
	}

	public Customer customerLogin(String id, String password) {
		Customer c = null;

		try {
			Socket socket = new Socket("localhost", 8080);
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

	public Worker workerLogin(String email, String password, Worker.WorkerType type) {
		Worker w = null;

		try {
			Socket socket = new Socket("localhost", 8080);
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

	public static List<String> getStations() {
		List<String> stations = new ArrayList<String>();

		try {
			Socket socket = new Socket("localhost", 8080);
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

	public boolean startParking(String carID) {
		boolean res = false;

		try {
			Socket socket = new Socket("localhost", 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("start parking " + carID);
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

			Socket socket = new Socket("localhost", 8080);
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

	public boolean addNewCustomer(String id, String firstName, String lastName, String password, Customer.type type,
								  String email, String phone) {
		boolean flag = false;

		try {
			Socket socket = new Socket("localhost", 8080);
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

	public boolean addNewCar(String carId, String customerId) {
		boolean flag = false;
		try {
			Socket socket = new Socket("localhost", 8080);
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

	public boolean addOccasionalOrder(String car_id, Timestamp end_time, String car_park, String payMethod) {
		boolean flag = false;
		try {
			Socket socket = new Socket("localhost", 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("add occasional " + car_id + " " + end_time.toString() + " " + car_park+ " " + payMethod);
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			flag = (boolean) ois.readObject();

			if (flag) {
				System.out.println("[response] order was added successfully");
			} else
				System.out.println("[response] adding order was failed");

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean addInAdvanceOrder(String car_id, Timestamp start_time, Timestamp end_time, String parkingName, String paymentMethod) {
		boolean flag = false;
		try {
			Socket socket = new Socket("localhost", 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("add advanced " + car_id + " " + start_time.toString() + " " + end_time.toString() + " " + parkingName + " " + paymentMethod);
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			flag = (boolean) ois.readObject();

			if (flag) {
				System.out.println("[response] in advance order was added successfully");
			} else
				System.out.println("[response] adding in advance order was failed");

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
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
			Socket socket = new Socket("localhost", 8080);
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
			Socket socket = new Socket("localhost", 8080);
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
			Socket socket = new Socket("localhost", 8080);
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
			Socket socket = new Socket("localhost", 8080);
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

	public boolean endParking(String car_id, String client_id) {
		boolean res = false;
		try {
			Socket socket = new Socket("localhost", 8080);
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

	public double getPrice(String car_id) {
		double res = Double.MAX_VALUE;
		try {
			Socket socket = new Socket("localhost", 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("get price " + car_id);
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

	public int[][][] getParkSoltStatus(int parkId) {
		int[][][] res = null;
		try {
			Socket socket = new Socket("localhost", 8080);
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

	public double cancelOrder(int orderId) {
		double res = Double.MAX_VALUE;
		try {
			Socket socket = new Socket("localhost", 8080);
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
}
