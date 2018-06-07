package CPS;

public class DirectorOfParkingStation implements Person {

	public DirectorOfParkingStation(int pid, String pname) {
		this.pid = pid;
		this.pname = pname;
	}

	int pid;
	String pname;
	
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
		// 

	}

	@Override
	public boolean login() {
		// 
		return false;
	}

}
