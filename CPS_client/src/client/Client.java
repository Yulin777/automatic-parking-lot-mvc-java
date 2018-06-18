package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import server.Customer;
import server.Worker;

public class Client {
	public Client(){
	}
	public Customer customerLogin(String email,String password){
		Customer c = null; 

		try{
			Socket socket = new Socket("localhost",8080);
			OutputStreamWriter osw  = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("login client "+email+" "+password);
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			c = (Customer)ois.readObject();
			if(c!=null)
				System.out.println("[response] customer "+c.getFirstName()+" "+c.getLastName()+" login Succeed");
			else
				System.out.println("[response] customer not found");
			
			socket.close();
		}

		catch(IOException ioe){
			System.out.println("error 1");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	public Worker workerLogin(String email,String password,Worker.WorkerType type){
		Worker w = null; 

		try{
			Socket socket = new Socket("localhost",8080);
			OutputStreamWriter osw  = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			pw.println("login worker "+email+" "+password+" "+type.name());
			pw.flush();

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			w = (Worker)ois.readObject();
			if(w!=null)
				System.out.println("[response] worker "+w.getFirstName()+" "+w.getLastName()+" login Succeed");
			else
				System.out.println("[response] worker not found");

			socket.close();
		}

		catch(IOException ioe){
			System.out.println("IOException exption");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return w;


	}
}

