// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import common.*;
import java.io.*;

import CPS.WorkerInterfaceController;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
	private String name,id;
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 
  final public static int DEFAULT_PORT = 5555;
  private static ChatClient instance;
  
  public static ChatClient getInstance( ) {
	  if(instance==null)
		try {
			instance = new ChatClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
      return instance;
   }
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public ChatClient(String host, int port, ChatIF clientUI,String Name,String ID) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    this.name=Name;
    this.id=ID;
    openConnection();
  }
  public ChatClient() 
			throws IOException 
	{
		super("", DEFAULT_PORT); //Call the superclass constructor	   
		openConnection();
	}
  
  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
	   //clientUI.display(msg.toString());
	  System.out.println("message recive");
	  WorkerInterfaceController.response = (String)msg;
	  
  }

  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(String message)
  {
    try
    {
    	if(message.equals("send")) {
    		String new_msg="Name: " + this.name + " Id: "+this.id +" My Address:"+getInetAddress().toString();
    		try{
    			sendToServer(new_msg);
    			return;
    		}
    		catch (IOException e){
    			clientUI.display
    	        ("Could not send message to server.  Terminating client.");
    	      quit();
    		}
    		
    		
    	}
    	/*TODO: add the functionality to ChatIF and all it's implementations and use it to handle message type "send"*/
    	
    	sendToServer(message);
    }
    catch(IOException e)
    {
      clientUI.display
        ("Could not send message to server.  Terminating client.");
      quit();
    }
  }
  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of ChatClient class
