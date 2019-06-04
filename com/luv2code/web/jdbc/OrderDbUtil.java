package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class OrderDbUtil {

	private DataSource dataSource;

	public OrderDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Order> getOrders() throws Exception {
		
		List<Order> orders = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from orders order by employee_ID";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int orderId = myRs.getInt("orderId");
				String orderNumber = myRs.getString("order_Number");
				String orderDate = myRs.getString("order_Date");
				String shipDate = myRs.getString("ship_Date");
				String customerID = myRs.getString("customer_ID");
				String employeeID = myRs.getString("employee_ID");
				
				// create new student object
				Order tempOrder = new Order(orderId,orderNumber,orderDate, shipDate, customerID, employeeID);
				
				// add it to the list of students
				orders.add(tempOrder);				
			}
			
			return orders;		
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
	public void addOrder(Order theOrder) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into orders "
					   + "(orderId,order_Number,order_Date,ship_Date,customer_ID,employee_ID) "
					   + "values (?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setInt(1, theOrder.getOrderId());
			myStmt.setString(2, theOrder.getOrderNumber());
			myStmt.setString(3, theOrder.getOrderDate());
			myStmt.setString(4, theOrder.getShipDate());
			myStmt.setString(5, theOrder.getCustomerID());
			myStmt.setString(6, theOrder.getEmployeeID());
		
		

			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public Order getOrder(String theOrderId) throws Exception {

		Order theOrder = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int orderId;
		
		try {
			// convert student id to int
             orderId = Integer.parseInt(theOrderId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from orders where orderId=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, orderId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			
			// retrieve data from result set row
			if (myRs.next()) {
				String orderNumber = myRs.getString("order_Number");
				String orderDate = myRs.getString("order_Date");
				String shipDate = myRs.getString("ship_Date");
				String customerID = myRs.getString("customer_ID");
				String employeeID = myRs.getString("employee_ID");
				// use the studentId during construction
				theOrder = new Order(orderId,orderNumber, orderDate, shipDate, customerID,employeeID);
			}
			else {
				throw new Exception("Could not find order id: " + orderId);
			}				
			
			return theOrder;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateOrder(Order theOrder) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update orders "
						+ "set order_Number=?, order_Date=?, ship_Date=?, customer_ID=?, employee_ID=?"
						+ "where orderId=?";
						
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
						//myStmt.setString(1, "josephine2");
						//myStmt.setString(2, "smith2");
						//myStmt.setString(3, "ringo2@gmail.com");
						//myStmt.setInt(4,3);
			
			// set params
			
			myStmt.setString(1, theOrder.getOrderNumber());
			myStmt.setString(2, theOrder.getOrderDate());
			myStmt.setString(3, theOrder.getShipDate());
			myStmt.setString(4, theOrder.getCustomerID());
			myStmt.setString(5, theOrder.getEmployeeID());
			myStmt.setInt(6, theOrder.getOrderId());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public void deleteOrder(String theOrderId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int orderId = Integer.parseInt(theOrderId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from orders where orderId=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, orderId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}

}

