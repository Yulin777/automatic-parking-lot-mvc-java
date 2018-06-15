package CPS.View;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import server.Worker;

public class WorkerInterfaceController {
	Worker loginWorker;
	public static String response;

	public WorkerInterfaceController() {
		//new WorkerInterface(this);
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
    	//ChatClient.getInstance().handleMessageFromServer(Object loginUser);

    	return true;
	}
	
	//TODO Or
	public void Logout(){
		loginWorker = null;
	}

}
