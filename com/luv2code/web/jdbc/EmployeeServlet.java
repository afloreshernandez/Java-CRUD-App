package com.luv2code.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Define datasource/connection pool for Resource Injection
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Step 1:  Set up the printwriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		// Step 2:  Get a connection to the database
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			
			// Step 3:  Create a SQL statements
			String sql = "select * from employee";
			myStmt = myConn.createStatement();
			
			// Step 4:  Execute SQL query
			myRs = myStmt.executeQuery(sql);
			
			// Step 5:  Process the result set
			while (myRs.next()) {
				int id = myRs.getInt("id");
				out.println(id);
				String employeeID = myRs.getString("employee_ID");
				out.println(employeeID);
				String empFirstName = myRs.getString("empFirst_Name");
				out.println(empFirstName);
				String empLastName = myRs.getString("empLast_Name");
				out.println(empLastName);
				String empStreetAddress = myRs.getString("empStreet_Address");
				out.println(empStreetAddress);
				String empCity = myRs.getString("emp_City");
				out.println(empCity);
				String empState = myRs.getString("emp_State");
				out.println(empState);
				String empZipCode = myRs.getString("empZip_Code");
				out.println(empZipCode);
				String empAreaCode = myRs.getString("empArea_Code");
				out.println(empAreaCode);
				String empPhoneNumber = myRs.getString("empPhone_Number");
				out.println(empPhoneNumber);
				String empBirthDate = myRs.getString("empBirth_Date");
				out.println(empBirthDate);
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}









