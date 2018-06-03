package CPS;

import java.sql.Date;

public class Complaint {
	int complaintNumber;
	Date date;
	String description;
	Customer customer;
	Worker attendant;
	String response;
	double compensation;
	
	public Complaint(int complaintNumber, Date date, String description, Customer customer, Worker attendant,
			String response, double compensation) {
		this.complaintNumber = complaintNumber;
		this.date = date;
		this.description = description;
		this.customer = customer;
		this.attendant = attendant;
		this.response = response;
		this.compensation = compensation;
	}
	
	
	public int getComplaintNumber() {
		return complaintNumber;
	}
	public void setComplaintNumber(int complaintNumber) {
		this.complaintNumber = complaintNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Worker getAttendant() {
		return attendant;
	}
	public void setAttendant(Worker attendant) {
		this.attendant = attendant;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public double getCompensation() {
		return compensation;
	}
	public void setCompensation(double compensation) {
		this.compensation = compensation;
	}
	public Date getDate() {
		return date;
	}
	
	
	

}
