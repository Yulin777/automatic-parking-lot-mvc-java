package CPS;

public class Worker extends Person {

	public Worker(String id, String firstName, String lastName, String password, String type) {
		super(id, firstName, lastName, password, type, "", "");
	}


	public boolean setup() {
		return false;
	}

	public boolean reserveParkingSlot() {
		return false;
	}

	// TODO: return type is Unknown
	public int checkParkingAvailability() {
		return 0;
	}

	public boolean placeOrder() {
		return false;
	}

}
