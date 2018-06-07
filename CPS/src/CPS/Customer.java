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
		return 0;
	}

	@Override
	public void setPid(int pid) {

	}

	@Override
	public String getPname() {
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
