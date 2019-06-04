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
@WebServlet("/CustomerControllerServlet")
public class CustomerControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerDbUtil customerDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			customerDbUtil = new CustomerDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
				listCustomer(request, response);
				break;
				
			case "ADD":
				addCustomer(request, response);
				break;
				
			case "LOAD":
				loadCustomer(request, response);
				break;
				
			case "UPDATE":
				updateCustomer(request, response);
				break;
				
			case "DELETE":
				deleteCustomer(request, response);
				break;
				
			default:
				listCustomer(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}


	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student id from form data
			String theCustomerID = request.getParameter("customerID");
			
			// delete student from database
			customerDbUtil.deleteCustomer(theCustomerID);
			
			// send them back to "list students" page
			listCustomer(request, response);
		}
	
	private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
			// read student info from form data
			int customerID = Integer.parseInt(request.getParameter("customerID"));
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String streetAddress = request.getParameter("streetAddress");
			String city = request.getParameter("city");
			String zipCode = request.getParameter("zipCode");
			String areaCode = request.getParameter("areaCode");
			String phoneNumber = request.getParameter("phoneNumber");
			
			// create a new student object
			Customer theCustomer = new Customer(customerID, firstName, lastName, streetAddress, city,zipCode,areaCode,phoneNumber);
			
			// perform update on database
			customerDbUtil.updateCustomer(theCustomer);
			
			// send them back to the "list students" page
			listCustomer(request, response);
			
		}

		private void loadCustomer(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read student id from form data
			String theCustomerID = request.getParameter("customerID");
			
			// get student from database (db util)
			Customer theCustomer = customerDbUtil.getCustomer(theCustomerID);
			
			// place student in the request attribute
			request.setAttribute("THE_CUSTOMER", theCustomer);
			
			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/update-customer-form.jsp");
			dispatcher.forward(request, response);		
		}


	private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
		
		String firstName = request.getParameter("firstName");
		  String lastName = request.getParameter("lastName");
			String streetAddress = request.getParameter("streetAddress");
			  String city = request.getParameter("city");
				String zipCode = request.getParameter("zipCode");
				  String areaCode = request.getParameter("areaCode");
					String phoneNumber = request.getParameter("phoneNumber");
		
		// create a new student object
		Customer theCustomer = new Customer(firstName,lastName,streetAddress,city,zipCode,areaCode,phoneNumber);
		
		// add the student to the database
		customerDbUtil.addCustomer(theCustomer);
				
		// send back to main page (the student list)
		listCustomer(request, response);
	}

	private void listCustomer(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get students from db util
		List<Customer> customer = customerDbUtil.getCustomer();
		
		// add students to the request
		request.setAttribute("CUSTOMER_LIST", customer);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-customer.jsp");
		dispatcher.forward(request, response);
	}

}
















