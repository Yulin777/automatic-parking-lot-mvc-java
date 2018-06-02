package CPS;

public class Worker implements Person {

	public Worker(int pid, String pname) {
		this.pid = pid;
		this.pname = pname;
	}

	int pid;
	String pname;
	
	@Override
	public int getPid() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPid(int pid) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPname() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPname(String pname) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean login() {
		// TODO Auto-generated method stub
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
