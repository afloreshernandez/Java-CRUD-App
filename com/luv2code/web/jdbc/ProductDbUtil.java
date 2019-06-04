package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ProductDbUtil {

	private DataSource dataSource;

	public ProductDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Product> getProduct() throws Exception {
		
		List<Product> product = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from product order by category_ID";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int productId = myRs.getInt("product_Id");
				String productNumber = myRs.getString("product_Number");
				String productName = myRs.getString("product_Name");
				String productDesc = myRs.getString("product_Desc");
				String retailPrice = myRs.getString("retail_Price");
				String quantityOnHand = myRs.getString("quantity_On_Hand");
				String categoryID = myRs.getString("category_ID");
				
				// create new student object
				Product tempProduct = new Product(productId,productNumber,productName, productDesc, retailPrice, quantityOnHand,categoryID);
				
				// add it to the list of students
				product.add(tempProduct);				
			}
			
			return product;		
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
	public void addProduct(Product theProduct) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into product "
					   + "(product_Id,product_Number,product_Name,product_Desc,retail_Price,quantity_On_Hand,category_ID) "
					   + "values (?,?,?,?,?,?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setInt(1, theProduct.getProductId());
			myStmt.setString(2, theProduct.getProductNumber());
			myStmt.setString(3, theProduct.getProductName());
			myStmt.setString(4, theProduct.getProductDesc());
			myStmt.setString(5, theProduct.getRetailPrice());
			myStmt.setString(6, theProduct.getQuantityOnHand());
			myStmt.setString(7, theProduct.getCategoryID());
		

			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public Product getProduct(String theProductId) throws Exception {

		Product theProduct = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int productId;
		
		try {
			// convert student id to int
			productId = Integer.parseInt(theProductId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from product where product_Id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, productId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			
		
			// retrieve data from result set row
			if (myRs.next()) {
				String productNumber = myRs.getString("product_Number");
				String productName = myRs.getString("product_Name");
				String productDesc = myRs.getString("product_Desc");
				String retailPrice  = myRs.getString("retail_Price");
				String quantityOnHand = myRs.getString("quantity_On_Hand");
				String categoryID  = myRs.getString("category_ID");
				
				// use the studentId during construction
				theProduct = new Product(productId,productNumber, productName, productDesc, retailPrice,quantityOnHand,categoryID);
			}
			else {
				throw new Exception("Could not find product id: " + productId);
			}				
			
			return theProduct;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateProduct(Product theProduct) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update product "
						+ "set product_Number=?, product_Name=?, product_Desc=?, retail_Price=?, quantity_On_Hand=?, category_ID=?"
						+ "where product_Id=?";
						
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
						//myStmt.setString(1, "josephine2");
						//myStmt.setString(2, "smith2");
						//myStmt.setString(3, "ringo2@gmail.com");
						//myStmt.setInt(4,3);
			
			// set params
			
			
			myStmt.setString(1, theProduct.getProductNumber());
			myStmt.setString(2, theProduct.getProductName());
			myStmt.setString(3, theProduct.getProductDesc());
			myStmt.setString(4, theProduct.getRetailPrice());
			myStmt.setString(5, theProduct.getQuantityOnHand());
			myStmt.setString(6, theProduct.getCategoryID());
			myStmt.setInt(7, theProduct.getProductId());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public void deleteProduct(String theProductId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int productId = Integer.parseInt(theProductId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from product where product_Id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, productId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}



}

