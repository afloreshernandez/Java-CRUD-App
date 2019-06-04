package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ProductVendorDbUtil {

	private DataSource dataSource;

	public ProductVendorDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<ProductVendor> getProductVendor() throws Exception {
		
		List<ProductVendor> productVendor = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from productvendor order by  days_To_Deliver";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int vendorProductId = myRs.getInt("vendorProduct_Id");
				String vendorProductNumber = myRs.getString("vendorProduct_Number");
				String vendorID = myRs.getString("vendor_ID");
				String wholeSalePrice = myRs.getString("wholeSale_Price");
				String daysToDeliver = myRs.getString("days_To_Deliver");
				
				// create new student object
				ProductVendor tempProductVendor = new ProductVendor(vendorProductId,vendorProductNumber,vendorID, wholeSalePrice, daysToDeliver);
				
				// add it to the list of students
				productVendor.add(tempProductVendor);				
			}
			
			return productVendor;		
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
	public void addProductVendor(ProductVendor theProductVendor) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into productvendor "
					   + "(vendorProduct_Number,vendor_ID,wholeSale_Price,days_To_Deliver) "
					   + "values (?,?,?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, theProductVendor.getVendorProductNumber());
			myStmt.setString(2, theProductVendor.getVendorID());
			myStmt.setString(3, theProductVendor.getWholeSalePrice());
			myStmt.setString(4, theProductVendor.getDaysToDeliver());
			

			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public ProductVendor getProductVendor(String theVendorProductId) throws Exception {

		ProductVendor theProductVendor = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int vendorProductId;
		
		try {
			// convert student id to int
			vendorProductId = Integer.parseInt(theVendorProductId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from productvendor where vendorProduct_Id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, vendorProductId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			
		
			// retrieve data from result set row
			if (myRs.next()) {
				String vendorProductNumber = myRs.getString("vendorProduct_Number");
				String vendorID = myRs.getString("vendor_ID");
				String wholeSalePrice = myRs.getString("wholeSale_Price");
				String daysToDeliver  = myRs.getString("days_To_Deliver");
				
				
				// use the studentId during construction
				theProductVendor = new ProductVendor(vendorProductId,vendorProductNumber, vendorID, wholeSalePrice, daysToDeliver);
			}
			else {
				throw new Exception("Could not find vendor product id: " + vendorProductId);
			}				
			
			return theProductVendor;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateProductVendor(ProductVendor theProductVendor) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update productvendor "
						+ "set vendorProduct_Number=?, vendor_ID=?, wholeSale_Price=?, days_To_Deliver=?"
						+ "where vendorProduct_Id=?";
						
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
						//myStmt.setString(1, "josephine2");
						//myStmt.setString(2, "smith2");
						//myStmt.setString(3, "ringo2@gmail.com");
						//myStmt.setInt(4,3);
			
			// set params
			
			
			myStmt.setString(1, theProductVendor.getVendorProductNumber());
			myStmt.setString(2, theProductVendor.getVendorID());
			myStmt.setString(3, theProductVendor.getWholeSalePrice());
			myStmt.setString(4, theProductVendor.getDaysToDeliver());
			
			myStmt.setInt(5, theProductVendor.getVendorProductId());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public void deleteProductVendor(String theVendorProductId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int vendorProductId = Integer.parseInt(theVendorProductId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from productvendor where vendorProduct_Id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, vendorProductId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}


}


