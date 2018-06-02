package CPS;

public class Customer implements Person {

	
	int pid;
	String pname;
	String email;
	Car[] cars;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
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
	
	public int orderParking(){
		return 0;
	}
	
	public boolean subscribe(){
		return false;
	}
	
	//TODO: return type is Unknown
	public void trackOrder(){
		
	}
	
	public Car[] getCars(){
		return null;
	}
	
	public void setCars(Car[] cars){
		
	}
	
	public boolean placeOrder(){
		return false;
	}

}
