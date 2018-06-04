package CPS;

public class WorkerInterfaceController {
	Worker loginWorker;

	public WorkerInterfaceController() {
		new WorkerInterface(this);
	}

	//TODO Or
	public boolean login(String email, String password){
		Worker loginUser = WorkerController.getWorker(email,password);
		
		if(loginUser==null) //user not exist
			return false;
		
		loginWorker = loginUser;
		return true;	
	}
	
	//TODO Or
	public void Logout(){
		loginWorker = null;
	}

}
