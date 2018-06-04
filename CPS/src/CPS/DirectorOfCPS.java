package CPS;

public class DirectorOfCPS implements Person {

	public DirectorOfCPS(int pid, String pname) {
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
	
	//TODO: return type is Unknown
	public void getReport() {
	}

}
