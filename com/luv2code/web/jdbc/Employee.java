package com.luv2code.web.jdbc;

public class Employee {

	
	private int employeeID;
	
	private String empFirstName;
	
	private String empLastName;
	
	private String empStreetAddress;
	
	private String empCity;
								
	private String empState;
	
	private String empZipCode;
	
	private String empAreaCode;
	
	private String empPhoneNumber;
	
	private String empBirthDate;
	
	public Employee(String empFirstName, String empLastName, String empStreetAddress,
			String empCity, String empState, String empZipCode, String empAreaCode, String empPhoneNumber,
			String empBirthDate) {
		
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empStreetAddress = empStreetAddress;
		this.empCity = empCity;
		this.empState = empState;
		this.empZipCode = empZipCode;
		this.empAreaCode = empAreaCode;
		this.empPhoneNumber = empPhoneNumber;
		this.empBirthDate = empBirthDate;
	}

	public Employee(int employeeID, String empFirstName, String empLastName, String empStreetAddress,
			String empCity, String empState, String empZipCode, String empAreaCode, String empPhoneNumber,
			String empBirthDate) {
		
		
		this.employeeID = employeeID;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empStreetAddress = empStreetAddress;
		this.empCity = empCity;
		this.empState = empState;
		this.empZipCode = empZipCode;
		this.empAreaCode = empAreaCode;
		this.empPhoneNumber = empPhoneNumber;
		this.empBirthDate = empBirthDate;
	}


	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmpFirstName() {
		return empFirstName;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public String getEmpStreetAddress() {
		return empStreetAddress;
	}

	public void setEmpStreetAddress(String empStreetAddress) {
		this.empStreetAddress = empStreetAddress;
	}

	public String getEmpCity() {
		return empCity;
	}

	public void setEmpCity(String empCity) {
		this.empCity = empCity;
	}

	public String getEmpState() {
		return empState;
	}

	public void setEmpState(String empState) {
		this.empState = empState;
	}

	public String getEmpZipCode() {
		return empZipCode;
	}

	public void setEmpZipCode(String empZipCode) {
		this.empZipCode = empZipCode;
	}

	public String getEmpAreaCode() {
		return empAreaCode;
	}

	public void setEmpAreaCode(String empAreaCode) {
		this.empAreaCode = empAreaCode;
	}

	public String getEmpPhoneNumber() {
		return empPhoneNumber;
	}

	public void setEmpPhoneNumber(String empPhoneNumber) {
		this.empPhoneNumber = empPhoneNumber;
	}

	public String getEmpBirthDate() {
		return empBirthDate;
	}

	public void setEmpBirthDate(String empBirthDate) {
		this.empBirthDate = empBirthDate;
	}


	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", empFirstName=" + empFirstName + ", empLastName=" + empLastName + ", empStreetAddress=" + empStreetAddress + ", empCity=" + empCity + ", empState=" + empState + ", empZipCode=" + empZipCode + ", empAreaCode=" + empAreaCode + ", empPhoneNumber=" + empPhoneNumber + ", empBirthDate=" + empBirthDate +"]";
	}	

}
