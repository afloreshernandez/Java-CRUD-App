package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class OrderDetailDbUtil {

	private DataSource dataSource;

	public OrderDetailDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<OrderDetail> getOrderDetail() throws Exception {
		
		List<OrderDetail> orderDetail = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from orderdetail order by quantity_Ordered";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int orderDetailId = myRs.getInt("orderDetailId");
				String orderDetailNumber = myRs.getString("orderDetail_Number");
				String productDetailNumber = myRs.getString("productDetail_Number");
				String quotedPrice = myRs.getString("quoted_Price");
				String quantityOrdered = myRs.getString("quantity_Ordered");
				
				// create new student object
				OrderDetail tempOrderDetail = new OrderDetail(orderDetailId,orderDetailNumber,productDetailNumber, quotedPrice, quantityOrdered);
				
				// add it to the list of students
				orderDetail.add(tempOrderDetail);				
			}
			
			return orderDetail;		
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
	public void addOrderDetail(OrderDetail theOrderDetail) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into orderdetail "
					   + "(orderDetailId,orderDetail_Number,productDetail_Number,quoted_Price,quantity_Ordered) "
					   + "values (?,?,?,?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setInt(1, theOrderDetail.getOrderDetailId());
			myStmt.setString(2, theOrderDetail.getOrderDetailNumber());
			myStmt.setString(3, theOrderDetail.getProductDetailNumber());
			myStmt.setString(4, theOrderDetail.getQuotedPrice());
			myStmt.setString(5, theOrderDetail.getQuantityOrdered());
			
			
			
			
		

			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	
	public OrderDetail getOrderDetail(String theOrderDetailId) throws Exception {

		OrderDetail theOrderDetail = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int orderDetailId;
		
		try {
			// convert student id to int
             orderDetailId = Integer.parseInt(theOrderDetailId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from orderdetail where orderDetailId=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, orderDetailId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String orderDetailNumber = myRs.getString("orderDetail_Number");
				String productDetailNumber = myRs.getString("productDetail_Number");
				String quotedPrice = myRs.getString("quoted_Price");
				String quantityOrdered = myRs.getString("quantity_Ordered");
				
				// use the studentId during construction
				theOrderDetail = new OrderDetail(orderDetailId,orderDetailNumber, productDetailNumber, quotedPrice, quantityOrdered);
			}
			else {
				throw new Exception("Could not find order Detail id: " + orderDetailId);
			}				
			
			return theOrderDetail;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateOrderDetail(OrderDetail theOrderDetail) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update orderdetail "
						+ "set orderDetail_Number=?, productDetail_Number=?, quoted_Price=?, quantity_Ordered=?"
						+ "where orderDetailId=?";
						
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
						//myStmt.setString(1, "josephine2");
						//myStmt.setString(2, "smith2");
						//myStmt.setString(3, "ringo2@gmail.com");
						//myStmt.setInt(4,3);
			
			// set params
			myStmt.setString(1, theOrderDetail.getOrderDetailNumber());
			myStmt.setString(2, theOrderDetail.getProductDetailNumber());
			myStmt.setString(3, theOrderDetail.getQuotedPrice());
			myStmt.setString(4, theOrderDetail.getQuantityOrdered());
			myStmt.setInt(5, theOrderDetail.getOrderDetailId());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public void deleteOrderDetail(String theOrderDetailId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int orderDetailId = Integer.parseInt(theOrderDetailId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from orderdetail where orderDetailId=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, orderDetailId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
}

