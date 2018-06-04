package CPS;

public class WorkerInterface {
	WorkerInterfaceController wIC;
	
	public WorkerInterface(WorkerInterfaceController wIC) {
		this.wIC = wIC;
	}
	public void setup(){
		
	}
	public boolean checkParkingAvliblity(){
		return false;
		
	}
	public void reserveParkingSlot(){
		
	}

	public void getReport(){
		
	}
	
	
	//TODO Snir
	public boolean login(String email, String password){
		return false;
	}
	
	//TODO Snir
	public void logout(){

	}


}
