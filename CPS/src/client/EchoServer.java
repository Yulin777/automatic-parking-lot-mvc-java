package client;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.mysql.jdbc.UpdatableResultSet;
import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;
  public Connection conn;
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer(int port) 
  {
    super(port);
    try 
	{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
    } catch (Exception ex) {/* handle the error*/}
    
	
	String url = "jdbc:mysql://cs.telhai.ac.il/Group_1";
	
	String username = "cs311873400";
	String password = "Password1";

	
	
    try 
    {
    	conn = DriverManager.getConnection(url,username,password);

        System.out.println("SQL connection succeed");
       // conn.close();
 	} catch (SQLException ex) 
 	    {/* handle any errors*/
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
        }
    
  }

  
  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient
    (Object msg, ConnectionToClient client)
  {
	  
	  String cmd[] = ((String)msg).split(" ");//arr cmd holds the 3 params of the possible commands:
	  										//get client x  OR add client
	  if(cmd.length<2)//we want at least 2 params
	  {
		  System.out.println("too few arguments,at least 3 are needed");
	  }
	  
	  if(cmd[0].equals("get") && cmd[1].equals("client"))
	  {
		  
		  this.sendToAllClients(getClientById(conn, cmd[2]));
	  }
	  if(cmd[0].equals("add") && cmd[1].equals("client"))
	  {
		  this.sendToAllClients(addNewClient(conn, cmd[2], cmd[3], cmd[4], cmd[5], cmd[6]));
	  }
	  


	  }


    
  
  
  
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
    try {
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of 
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on.  Defaults to 5555 
   *          if no argument is entered.
   */
  public static void main(String[] args) 
  {
    int port = 0; //Port to listen on


	
    try
    {
      port = Integer.parseInt(args[0]); //Get port from command line
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }
	
    EchoServer sv = new EchoServer(port);
    
    try 
    {
      sv.listen(); //Start listening for connections
    } 
    catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");
      System.out.println("ex:" + ex.toString());
    }
  }
  
  private static String getClientById(Connection con,String id)
	{
		Statement stmt;
		String return_res = null;
		try 
		{
			stmt = con.createStatement();
		
			ResultSet rs = stmt.executeQuery("SELECT * FROM clients WHERE client_ID=" + id + ";");
	 		while(rs.next())
	 		{
				 // Print out the values
	 			return_res=(rs.getString(1)+"  " +rs.getString(2)+" " +rs.getString(3)+" , " +rs.getString(4)
		 			+" , " +rs.getString(5)+" , " +rs.getString(6));
			} 
	 		
	 		
	 		
	 		
	 		
	 	    if (rs != null) {
	 	        try {
	 	            rs.close();
	 	        } catch (SQLException e) { /* ignored */}
	 	    }
	 	    if (stmt != null) {
	 	        try {
	 	            stmt.close();
	 	        } catch (SQLException e) { /* ignored */}
	 	    }
	 	   
		} catch (SQLException e) {e.printStackTrace();}
		
		return return_res;
	}
  
  
 private String addNewClient(Connection conn,String firstName, String lastName, String type, String email, String telephone) {
		Statement stmt;
		try 
		{
			stmt =conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
										 ResultSet.CONCUR_UPDATABLE);
					
			ResultSet uprs = stmt.executeQuery("SELECT * FROM clients");

			uprs.moveToInsertRow();
			uprs.updateString("client_first_name",firstName );
			uprs.updateString("client_last_name", lastName);
			uprs.updateString("client_type",type );
			uprs.updateString("client_email",email );
			uprs.updateString("client_telephone",telephone );

			uprs.insertRow();

			System.out.println("New client was added succsfully");
			
			
	 	    if (uprs != null) {
	 	        try {
	 	        	uprs.close();
	 	        } catch (SQLException e) { /* ignored */}
	 	    }
	 	    if (stmt != null) {
	 	        try {
	 	            stmt.close();
	 	        } catch (SQLException e) { /* ignored */}
	 	    }
		
		} catch (SQLException e) {e.printStackTrace();}
		
		return ("New client was added succsfully");
	}

  
  
}
//End of EchoServer class
