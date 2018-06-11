package CPS;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import client.ChatClient;

public class WorkerInterfaceController {
	Worker loginWorker;
	public static String response;

	public WorkerInterfaceController() {
		new WorkerInterface(this);
	}

	//TODO Or
	public static boolean login(String email, String password)
	{
		/*Worker loginUser = WorkerController.getWorker(email,password);
		
		if(loginUser==null) //user not exist
			return false;
		
		loginWorker = loginUser;
		return true;	*/
		String loginUser = "";
		response = "";
    	try {
			ChatClient.getInstance().sendToServer("login worker"+ email+ password);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//ChatClient.getInstance().handleMessageFromServer(Object loginUser);
    	try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(!response.equals(""))
    		System.out.println("got from srver: "+loginUser);
    	System.out.println("no response");
    	//loginWorker = loginUser;
    	
    	return true;
	}
	
	//TODO Or
	public void Logout(){
		loginWorker = null;
	}

}
