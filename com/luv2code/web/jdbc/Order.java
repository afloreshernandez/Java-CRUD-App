package com.luv2code.web.jdbc;

public class Order {

	private int orderId;
	private String orderNumber;
	private String orderDate;
	private String shipDate;
	private String customerID;
	private String employeeID;
	
	
	public Order(String orderNumber, String orderDate, String shipDate, String customerID, String employeeID) {
		
		
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.customerID = customerID;
		this.employeeID = employeeID;
	}
	
public Order(int orderId,String orderNumber, String orderDate, String shipDate, String customerID, String employeeID) {
		
		this.orderId= orderId;
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.customerID = customerID;
		this.employeeID = employeeID;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}


	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderNumber=" + orderNumber + ", orderDate=" + orderDate + ", shipDate=" + shipDate + ", customerID=" + customerID + ",employeeID=" + employeeID + "]";
	}	
	
	
	
}

