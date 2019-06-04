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
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
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
			String sql = "select * from customer";
			myStmt = myConn.createStatement();
			
			// Step 4:  Execute SQL query
			myRs = myStmt.executeQuery(sql);
			
			// Step 5:  Process the result set
			while (myRs.next()) {
			
				String id = myRs.getString("id");
				out.println(id);
				String customerID = myRs.getString("customer_ID");
				out.println(customerID);
				String firstName = myRs.getString("first_Name");
				out.println(firstName);
				String lastName = myRs.getString("last_Name");
				out.println(lastName);
				String streetAddress = myRs.getString("street_Address");
				out.println(streetAddress);
				String city = myRs.getString("city");
				out.println(city);
				String zipCode = myRs.getString("zip_Code");
				out.println(zipCode);
				String areaCode = myRs.getString("area_Code");
				out.println(areaCode);
				String phoneNumber = myRs.getString("phone_Number");
				out.println(phoneNumber);
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}








