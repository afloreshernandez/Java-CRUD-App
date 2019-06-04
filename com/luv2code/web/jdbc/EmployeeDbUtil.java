package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class EmployeeDbUtil {

	private DataSource dataSource;

	public EmployeeDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Employee> getEmployees() throws Exception {
		
		List<Employee> employee = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from employee order by empBirth_Date";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				
				int employeeID = myRs.getInt("employee_ID");
				String empFirstName = myRs.getString("empFirst_Name");
				String empLastName = myRs.getString("empLast_Name");
				String empStreetAddress= myRs.getString("empStreet_Address");
				String empCity = myRs.getString("emp_City");
				String empState = myRs.getString("emp_State");
				String empZipCode = myRs.getString("empZip_Code");
				String empAreaCode = myRs.getString("empArea_Code");
				String empPhoneNumber = myRs.getString("empPhone_Number");
				String empBirthDate = myRs.getString("empBirth_Date");
				
				// create new student object
				Employee tempEmployee = new Employee(employeeID,empFirstName,empLastName,empStreetAddress,empCity,empState,empZipCode,empAreaCode,empPhoneNumber,empBirthDate);
				
				// add it to the list of students
				employee.add(tempEmployee);				
			}
			
			return employee;		
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
	public void addEmployee(Employee theEmployee) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into employee "
					   + "(employee_ID, empFirst_Name, empLast_Name, empStreet_Address, emp_City, emp_State, empZip_Code, empArea_Code, empPhone_Number, empBirth_Date) "
					   + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setInt(1, theEmployee.getEmployeeID());
			myStmt.setString(2, theEmployee.getEmpFirstName());
			myStmt.setString(3, theEmployee.getEmpLastName());
			myStmt.setString(4, theEmployee.getEmpStreetAddress());
			myStmt.setString(5, theEmployee.getEmpCity());
			myStmt.setString(6, theEmployee.getEmpState());
			myStmt.setString(7, theEmployee.getEmpZipCode());
			myStmt.setString(8, theEmployee.getEmpAreaCode());
			myStmt.setString(9, theEmployee.getEmpPhoneNumber());
			myStmt.setString(10, theEmployee.getEmpBirthDate());
			
			
		

			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public Employee getEmployee(String theEmployeeID) throws Exception {

		Employee theEmployee = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int employeeID;
		
		try {
			// convert student id to int
			employeeID = Integer.parseInt(theEmployeeID);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from employee where employee_ID=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, employeeID);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			
			// retrieve data from result set row
			if (myRs.next()) {
				String empFirstName = myRs.getString("empFirst_Name");
				String empLastName = myRs.getString("empLast_Name");
				String empStreetAddress = myRs.getString("empStreet_Address");
				String empCity  = myRs.getString("emp_City");
				String empState = myRs.getString("emp_State");
				String empZipCode = myRs.getString("empZip_Code");
				String empAreaCode  = myRs.getString("empArea_Code");
				String empPhoneNumber = myRs.getString("empPhone_Number");
				String empBirthDate = myRs.getString("empBirth_Date");
				// use the studentId during construction
				theEmployee = new Employee(employeeID, empFirstName, empLastName, empStreetAddress, empCity,empState,empZipCode,empAreaCode,empPhoneNumber,empBirthDate);
			}
			else {
				throw new Exception("Could not find employee id: " + employeeID);
			}				
			
			return theEmployee;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateEmployee(Employee theEmployee) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update employee "
						+ "set empFirst_Name=?, empLast_Name=?, empStreet_Address=?, emp_City=?, emp_State=?, empZip_Code=?, empArea_Code=?, empPhone_Number=?,empBirth_Date=?"
						+ "where employee_ID=?";
						
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
						//myStmt.setString(1, "josephine2");
						//myStmt.setString(2, "smith2");
						//myStmt.setString(3, "ringo2@gmail.com");
						//myStmt.setInt(4,3);
			
			// set params
			
			
			myStmt.setString(1, theEmployee.getEmpFirstName());
			myStmt.setString(2, theEmployee.getEmpLastName());
			myStmt.setString(3, theEmployee.getEmpStreetAddress());
			myStmt.setString(4, theEmployee.getEmpCity());
			myStmt.setString(5, theEmployee.getEmpState());
			myStmt.setString(6, theEmployee.getEmpZipCode());
			myStmt.setString(7, theEmployee.getEmpAreaCode());
			myStmt.setString(8, theEmployee.getEmpPhoneNumber());
			myStmt.setString(9, theEmployee.getEmpBirthDate());
			myStmt.setInt(10, theEmployee.getEmployeeID());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public void deleteEmployee(String theEmployeeID) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int employeeID = Integer.parseInt(theEmployeeID);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from employee where employee_ID=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, employeeID);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}


}

