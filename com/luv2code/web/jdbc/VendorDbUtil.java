package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class VendorDbUtil {

	private DataSource dataSource;

	public VendorDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Vendor> getVendor() throws Exception {
		
		List<Vendor> vendor = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from vendor order by vendEmail_Address";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				 
				// retrieve data from result set row
				
				int vendorID = myRs.getInt("vendor_ID");
				String vendName = myRs.getString("vend_Name");
				String vendStreetAddress = myRs.getString("vendStreet_Address");
				String vendCity = myRs.getString("vend_City");
				String vendState = myRs.getString("vend_State");
				String vendZipCode = myRs.getString("vendZip_Code");
				String vendPhoneNumber = myRs.getString("vendPhone_Number");
				String vendFaxNumber = myRs.getString("vendFax_Number");
				String vendWebPage = myRs.getString("vendWeb_Page");
				String vendEmailAddress = myRs.getString("vendEmail_Address");
				
				// create new student object
				Vendor tempVendor = new Vendor(vendorID,vendName,vendStreetAddress,vendCity,vendState,vendZipCode,vendPhoneNumber,vendFaxNumber,vendWebPage,vendEmailAddress);
				
				// add it to the list of students
				vendor.add(tempVendor);				
			}
			
			return vendor;		
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
	public void addVendor(Vendor theVendor) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into vendor "
					   + "(vendor_ID, vend_Name, vendStreet_Address, vend_City, vend_State, vendZip_Code, vendPhone_Number, vendFax_Number,vendWeb_Page,vendEmail_Address) "
					   + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setInt(1, theVendor.getVendorID());
			myStmt.setString(2, theVendor.getVendName());
			myStmt.setString(3, theVendor.getVendStreetAddress());
			myStmt.setString(4, theVendor.getVendCity());
			myStmt.setString(5, theVendor.getVendState());
			myStmt.setString(6, theVendor.getVendZipCode());
			myStmt.setString(7, theVendor.getVendPhoneNumber());
			myStmt.setString(8, theVendor.getVendFaxNumber());
			myStmt.setString(9, theVendor.getVendWebPage());
			myStmt.setString(10, theVendor.getVendEmailAddress());
			

			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public Vendor getVendor(String theVendorID) throws Exception {

		Vendor theVendor = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int vendorID;
		
		try {
			// convert student id to int
			vendorID = Integer.parseInt(theVendorID);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from vendor where vendor_ID=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, vendorID);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			
			
			 
			
			// retrieve data from result set row
			if (myRs.next()) {
				String vendName = myRs.getString("vend_Name");
				String vendStreetAddress = myRs.getString("vendStreet_Address");
				String vendCity  = myRs.getString("vend_City");
				String vendState = myRs.getString("vend_State");
				String vendZipCode  = myRs.getString("vendZip_Code");
				String vendPhoneNumber = myRs.getString("vendPhone_Number");
				String vendFaxNumber = myRs.getString("vendFax_Number");
				String vendWebPage  = myRs.getString("vendWeb_Page");
				String vendEmailAddress = myRs.getString("vendEmail_Address");
				
				// use the studentId during construction
				theVendor = new Vendor(vendorID,vendName, vendStreetAddress, vendCity, vendState, vendZipCode, vendPhoneNumber, vendFaxNumber, vendWebPage, vendEmailAddress);
			}
			else {
				throw new Exception("Could not find vendor id: " + vendorID);
			}				
			
			return theVendor;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateVendor(Vendor theVendor) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update vendor "
						+ "set vend_Name=?, vendStreet_Address=?, vend_City=?, vend_State=?, vendZip_Code=?, vendPhone_Number=?, vendFax_Number=?, vendWeb_Page=?, vendEmail_Address=?"
						+ "where vendor_ID=?";
						
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
						//myStmt.setString(1, "josephine2");
						//myStmt.setString(2, "smith2");
						//myStmt.setString(3, "ringo2@gmail.com");
						//myStmt.setInt(4,3);
			
			// set params
			
			
			myStmt.setString(1, theVendor.getVendName());
			myStmt.setString(2, theVendor.getVendStreetAddress());
			myStmt.setString(3, theVendor.getVendCity());
			myStmt.setString(4, theVendor.getVendState());
			myStmt.setString(5, theVendor.getVendZipCode());
			myStmt.setString(6, theVendor.getVendPhoneNumber());
			myStmt.setString(7, theVendor.getVendFaxNumber());
			myStmt.setString(8, theVendor.getVendWebPage());
			myStmt.setString(9, theVendor.getVendEmailAddress());
			
			myStmt.setInt(10, theVendor.getVendorID());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public void deleteVendor(String theVendorID) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int vendorID = Integer.parseInt(theVendorID);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from vendor where vendor_ID=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, vendorID);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}


}

