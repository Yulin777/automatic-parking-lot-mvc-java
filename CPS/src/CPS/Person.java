package CPS;

public interface Person {
	
	//no Constructor in Interface
	//public Person(int pid,String panme);
	public int getPid();
	public void setPid(int pid);
	public String getPname();
	public void setPname(String pname);
	public boolean login();
	
}
