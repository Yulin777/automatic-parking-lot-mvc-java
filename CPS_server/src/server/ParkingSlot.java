package server;

enum SlotStatus
{
	AVAILABLE, OCCUPIED, OUT_OF_ORDER, RESERVED;
}
public class ParkingSlot {
	Location location;
	SlotStatus status;
	
	public ParkingSlot(Location location, SlotStatus status) {
		this.location = location;
		this.status = status;
	}
	public SlotStatus getStatus() {
		return status;
	}
	public void setStatus(SlotStatus status) {
		this.status = status;
	}
	public Location getLocation() {
		return location;
	}
}
