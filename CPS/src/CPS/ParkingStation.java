package CPS;

public class ParkingStation {
	
	int number; 
	String address;
	DirectorOfParkingStation director;
	int[] workers; 
	ParkingStationSize size; 
	ParkingSlot[] parkingSlots;
	
	public ParkingStation(int number, String address, DirectorOfParkingStation director, int[] workers,
			int size, ParkingSlot[] parkingSlots) {
		this.number = number;
		this.address = address;
		this.director = director;
		this.workers = workers;
		this.size = size;
		this.parkingSlots = parkingSlots;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public DirectorOfParkingStation getDirector() {
		return director;
	}
	public void setDirector(DirectorOfParkingStation director) {
		this.director = director;
	}
	public int[] getWorkers() {
		return workers;
	}
	public void setWorkers(int[] workers) {
		this.workers = workers;
	}
	public ParkingStationSize getSize() {
		return size;
	}
	public void setSize(ParkingStationSize size) {
		this.size = size;
	}
	public ParkingSlot[] getParkingSlots() {
		return parkingSlots;
	}
	public void setParkingSlots(ParkingSlot[] parkingSlots) {
		this.parkingSlots = parkingSlots;
	}
	
	public void cancel(){
		
	}
	
	
}
