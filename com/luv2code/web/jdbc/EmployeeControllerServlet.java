package com.luv2code.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/EmployeeControllerServlet")
public class EmployeeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EmployeeDbUtil employeeDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			employeeDbUtil = new EmployeeDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listEmployees(request, response);
				break;
				
			case "ADD":
				addEmployee(request, response);
				break;
				
			case "LOAD":
				loadEmployee(request, response);
				break;
				
			case "UPDATE":
				updateEmployee(request, response);
				break;
				
			case "DELETE":
				deleteEmployee(request, response);
				break;
				
			default:
				listEmployees(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student id from form data
			String theEmployeeID = request.getParameter("employeeID");
			
			// delete student from database
			employeeDbUtil.deleteEmployee(theEmployeeID);
			
			// send them back to "list students" page
			listEmployees(request, response);
		}
	
	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
			// read student info from form data
			int employeeID = Integer.parseInt(request.getParameter("employeeID"));
			String empFirstName = request.getParameter("empFirstName");
			String empLastName = request.getParameter("empLastName");
			String empStreetAddress = request.getParameter("empStreetAddress");
			String empCity = request.getParameter("empCity");
			String empState = request.getParameter("empState");
			String empZipCode = request.getParameter("empZipCode");
			String empAreaCode = request.getParameter("empAreaCode");
			String empPhoneNumber = request.getParameter("empPhoneNumber");
			String empBirthDate = request.getParameter("empBirthDate");
			
			// create a new student object
			Employee theEmployee = new Employee(employeeID, empFirstName, empLastName, empStreetAddress, empCity,empState,empZipCode,empAreaCode,empPhoneNumber,empBirthDate);
			
			// perform update on database
			employeeDbUtil.updateEmployee(theEmployee);
			
			// send them back to the "list students" page
			listEmployees(request, response);
			
		}

		private void loadEmployee(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read student id from form data
			String theEmployeeID = request.getParameter("employeeID");
			
			// get student from database (db util)
			Employee theEmployee = employeeDbUtil.getEmployee(theEmployeeID);
			
			// place student in the request attribute
			request.setAttribute("THE_EMPLOYEE", theEmployee);
			
			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/update-employee-form.jsp");
			dispatcher.forward(request, response);		
		}


	private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
		
	
		String empFirstName = request.getParameter("empFirstName");
		  String empLastName = request.getParameter("empLastName");
			String empStreetAddress = request.getParameter("empStreetAddress");
			  String empCity = request.getParameter("empCity");
			  String empState = request.getParameter("empState");
				String empZipCode = request.getParameter("empZipCode");
				  String empAreaCode = request.getParameter("empAreaCode");
					String empPhoneNumber = request.getParameter("empPhoneNumber");
					String empBirthDate = request.getParameter("empBirthDate");
		
		// create a new student object
		Employee theEmployee = new Employee(empFirstName,empLastName,empStreetAddress,empCity,empState,empZipCode,empAreaCode,empPhoneNumber,empBirthDate);
		
		// add the student to the database
		employeeDbUtil.addEmployee(theEmployee);
				
		// send back to main page (the student list)
		listEmployees(request, response);
	}

	private void listEmployees(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get students from db util
		List<Employee> employees = employeeDbUtil.getEmployees();
		
		// add students to the request
		request.setAttribute("EMPLOYEE_LIST", employees);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employee.jsp");
		dispatcher.forward(request, response);
	}

}
















