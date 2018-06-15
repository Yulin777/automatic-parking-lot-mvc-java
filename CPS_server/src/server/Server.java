package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		ServerSocket socket = null;
		try{
			socket = new ServerSocket(8080);
			System.out.println("CPS server on ip: "+InetAddress.getLocalHost().getHostAddress().toString()+
					" port: "+String.valueOf(socket.getLocalPort()));

			while(true){
				Socket currentSocket;
				currentSocket = socket.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(currentSocket.getInputStream()));
				String s = br.readLine();
				System.out.println("[request] "+s);
				handelRequest(s,currentSocket);
			}


		}
		catch(IOException ioe){

		}
		finally {
			try {
				if(socket!=null)
					socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	private static void handelRequest(String request,Socket currentSocket){
		String cmd[] = ((String) request).split(" ");// arr cmd holds the 3 params of the possible commands:
		if (cmd.length < 2)// we want at least 2 params
		{
			System.out.println("too few arguments,at least 3 are needed");
		}
		else if (cmd[0].equals("login") && cmd[1].equals("client")) {
			try{
				CustomerController cc = new CustomerController();
				Customer c = cc.login(cmd[2],cmd[3]);
				ObjectOutputStream osw  = new ObjectOutputStream(currentSocket.getOutputStream());
				//PrintWriter pw = new PrintWriter(osw);
				osw.writeObject(c);
				osw.flush();
				currentSocket.close();
			}
			catch(IOException ioe){

			}	
		}
		else if (cmd[0].equals("login") && cmd[1].equals("worker")) {
			try{
				WorkerController wc = new WorkerController();
				Worker w = wc.login(cmd[2],cmd[3]);
				ObjectOutputStream osw  = new ObjectOutputStream(currentSocket.getOutputStream());
				//PrintWriter pw = new PrintWriter(osw);
				osw.writeObject(w);
				osw.flush();
				currentSocket.close();
			}
			catch(IOException ioe){

			}	
		}


	}
}
