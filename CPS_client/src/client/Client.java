package client;

import java.io.*;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import server.Car;
import server.Customer;
import server.Worker;

public class Client {
	public Client() {
	}

	public Customer customerLogin(String email, String password) {
		Customer c = null;

		try {
			Socket socket = new Socket("localhost", 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("login client " + email + " " + password);
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			c = (Customer) ois.readObject();
			if (c != null)
				System.out.println("[response] customer " + c.getFirstName() + " " + c.getLastName() + " login Succeed");
			else
				System.out.println("[response] customer not found");

			socket.close();
		} catch (IOException ioe) {
			System.out.println("error 1");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return w;
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

	public boolean addNewCar(String customerId, String carId) {
		boolean flag = false;
		try {
			Socket socket = new Socket("localhost", 8080);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("add car " + customerId + " " + carId);
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

}
