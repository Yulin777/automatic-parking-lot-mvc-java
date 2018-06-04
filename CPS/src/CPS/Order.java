package CPS;


enum OrderStatus
{
	PENDING, CONFIRMED, DINIED;
}

enum OrderType
{
	UP_FRONT_ONE_TIME, OCCASIONAL, MONTHLY_SUBSCRIPTION;
}

enum PaymentMethod
{
	CREDIT_CARD, CASH, CHECK;
}
public class Order {
	int oid;
	OrderStatus status;
	double pricePerHour;
	int carId;
	PaymentMethod paymentMethod;
	OrderType orderType;
	///???attribute????
	
	
	public Order(int oid, OrderStatus status, double pricePerHour, int carId, PaymentMethod paymentMethod,
			OrderType orderType) {
		this.oid = oid;
		this.status = status;
		this.pricePerHour = pricePerHour;
		this.carId = carId;
		this.paymentMethod = paymentMethod;
		this.orderType = orderType;
	}
	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public double getPricePerHour() {
		return pricePerHour;
	}
	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public OrderType getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	
	
	
	

}
