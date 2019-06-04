package com.luv2code.web.jdbc;

public class Customer {

private int customerID;
private String firstName;
private String lastName;
private String streetAddress;
private String city;
private String zipCode;
private String areaCode;
private String phoneNumber;

public Customer(String firstName, String lastName, String streetAddress, String city,
		String zipCode, String areaCode, String phoneNumber) {
	
	
	this.firstName = firstName;
	this.lastName = lastName;
	this.streetAddress = streetAddress;
	this.city = city;
	this.zipCode = zipCode;
	this.areaCode = areaCode;
	this.phoneNumber = phoneNumber;
}


public Customer(int customerID, String firstName, String lastName, String streetAddress, String city,
		String zipCode, String areaCode, String phoneNumber) {
	
	this.customerID = customerID;
	this.firstName = firstName;
	this.lastName = lastName;
	this.streetAddress = streetAddress;
	this.city = city;
	this.zipCode = zipCode;
	this.areaCode = areaCode;
	this.phoneNumber = phoneNumber;
}


public int getCustomerID() {
	return customerID;
}

public void setCustomerID(int customerID) {
	this.customerID = customerID;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getStreetAddress() {
	return streetAddress;
}

public void setStreetAddress(String streetAddress) {
	this.streetAddress = streetAddress;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getZipCode() {
	return zipCode;
}

public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}

public String getAreaCode() {
	return areaCode;
}

public void setAreaCode(String areaCode) {
	this.areaCode = areaCode;
}

public String getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}


@Override
public String toString() {
	return "Customer [customerID=" + customerID + ", firstName=" + firstName + ", lastName=" + lastName + ", streetAddress=" + streetAddress + ", city=" + city + ",zipCode=" + zipCode + ",areaCode=" + areaCode + ",phoneNumber=" + phoneNumber + "]";
}	


}
