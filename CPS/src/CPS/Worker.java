package CPS;

public class Worker implements Person {

	public Worker(int pid, String pname, String email, String password, String phone, WorkerType type) {
		this.pid = pid;
		this.pname = pname;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.type = type;
	}


	enum WorkerType
	{
		ParkingWorker, MasterManger, CustomerService
;
	}
	
	int pid;
	String pname;
	String email;
	String password;
	String phone;
	WorkerType type;


	
	
	@Override
	public int getPid() {
		// 
		return 0;
	}

	@Override
	public void setPid(int pid) {
		// 

	}

	@Override
	public String getPname() {
		// 
		return null;
	}

	@Override
	public void setPname(String pname) {
		

	}

	@Override
	public boolean login() {

		return false;
	}
	
	public boolean setup() {
		return false;
	}
	
	public boolean reserveParkingSlot(){
		return false;
	}
	
	//TODO: return type is Unknown
	public int checkParkingAvailability(){
		return 0;
	}
	
	public boolean placeOrder(){
		return false;
	}
	

}
