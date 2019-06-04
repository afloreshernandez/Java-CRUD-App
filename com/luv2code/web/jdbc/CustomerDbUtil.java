package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class CustomerDbUtil {

	private DataSource dataSource;

	public CustomerDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Customer> getCustomer() throws Exception {
		
		List<Customer> customer = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from customer order by phone_Number";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int customerID = myRs.getInt("customer_ID");
				String firstName = myRs.getString("first_Name");
				String lastName = myRs.getString("last_Name");
				String streetAddress = myRs.getString("street_Address");
				String city = myRs.getString("city");
				String zipCode = myRs.getString("zip_Code");
				String areaCode = myRs.getString("area_Code");
				String phoneNumber = myRs.getString("phone_Number");
				
				// create new student object
				Customer tempCustomer = new Customer(customerID,firstName,lastName,streetAddress,city,zipCode,areaCode,phoneNumber);
				
				// add it to the list of students
				customer.add(tempCustomer);				
			}
			
			return customer;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public void addCustomer(Customer theCustomer) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into customer "
					   + "(customer_ID, first_Name,last_Name,street_Address,city,zip_Code,area_Code,phone_Number) "
					   + "values (?, ?, ?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			
			myStmt.setInt(1, theCustomer.getCustomerID());
			myStmt.setString(2, theCustomer.getFirstName());
			myStmt.setString(3, theCustomer.getLastName());
			myStmt.setString(4, theCustomer.getStreetAddress());
			myStmt.setString(5, theCustomer.getCity());
			myStmt.setString(6, theCustomer.getZipCode());
			myStmt.setString(7, theCustomer.getAreaCode());
			myStmt.setString(8, theCustomer.getPhoneNumber());
			
			
		

			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public Customer getCustomer(String theCustomerID) throws Exception {

		Customer theCustomer = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int customerID;
		
		try {
			// convert student id to int
			customerID = Integer.parseInt(theCustomerID);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from customer where customer_ID=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, customerID);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			
			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("first_Name");
				String lastName = myRs.getString("last_Name");
				String streetAddress = myRs.getString("street_Address");
				String city  = myRs.getString("city");
				String zipCode = myRs.getString("zip_Code");
				String areaCode  = myRs.getString("area_Code");
				String phoneNumber = myRs.getString("phone_Number");
				// use the studentId during construction
				theCustomer = new Customer(customerID,firstName, lastName, streetAddress, city,zipCode,areaCode,phoneNumber);
			}
			else {
				throw new Exception("Could not find customer id: " + customerID);
			}				
			
			return theCustomer;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateCustomer(Customer theCustomer) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update customer "
						+ "set first_Name=?, last_Name=?, street_Address=?, city=?, zip_Code=?, area_Code=?, phone_Number=?"
						+ "where customer_ID=?";
						
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
						//myStmt.setString(1, "josephine2");
						//myStmt.setString(2, "smith2");
						//myStmt.setString(3, "ringo2@gmail.com");
						//myStmt.setInt(4,3);
			
			// set params
			
			
			myStmt.setString(1, theCustomer.getFirstName());
			myStmt.setString(2, theCustomer.getLastName());
			myStmt.setString(3, theCustomer.getStreetAddress());
			myStmt.setString(4, theCustomer.getCity());
			myStmt.setString(5, theCustomer.getZipCode());
			myStmt.setString(6, theCustomer.getAreaCode());
			myStmt.setString(7, theCustomer.getPhoneNumber());
			myStmt.setInt(8, theCustomer.getCustomerID());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public void deleteCustomer(String theCustomerID) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int customerID = Integer.parseInt(theCustomerID);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from customer where customer_ID=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, customerID);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}


}

