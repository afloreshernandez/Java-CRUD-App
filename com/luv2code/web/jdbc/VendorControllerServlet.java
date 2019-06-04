
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
@WebServlet("/VendorControllerServlet")
public class VendorControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private VendorDbUtil vendorDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			vendorDbUtil = new VendorDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

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
				listVendor(request, response);
				break;
				
			case "ADD":
				addVendor(request, response);
				break;
				
			case "LOAD":
				loadVendor(request, response);
				break;
				
			case "UPDATE":
				updateVendor(request, response);
				break;
				
			case "DELETE":
				deleteVendor(request, response);
				break;
				
			default:
				listVendor(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void deleteVendor(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student id from form data
			String theVendorID = request.getParameter("vendorID");
			
			// delete student from database
			vendorDbUtil.deleteVendor(theVendorID);
			
			// send them back to "list students" page
			listVendor(request, response);
		}
	
	private void updateVendor(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
		
			// read student info from form data
			int vendorID = Integer.parseInt(request.getParameter("vendorID"));
			String vendName = request.getParameter("vendName");
			String vendStreetAddress = request.getParameter("vendStreetAddress");
			String vendCity = request.getParameter("vendCity");
			String vendState = request.getParameter("vendState");
			String vendZipCode = request.getParameter("vendZipCode");
			String vendPhoneNumber = request.getParameter("vendPhoneNumber");
			String vendFaxNumber = request.getParameter("vendFaxNumber");
			String vendWebPage = request.getParameter("vendWebPage");
			String vendEmailAddress = request.getParameter("vendEmailAddress");
			
			// create a new student object
			Vendor theVendor = new Vendor(vendorID, vendName, vendStreetAddress, vendCity, vendState,vendZipCode,vendPhoneNumber,vendFaxNumber,vendWebPage,vendEmailAddress);
			
			// perform update on database
			vendorDbUtil.updateVendor(theVendor);
			
			// send them back to the "list students" page
			listVendor(request, response);
			
		}

		private void loadVendor(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read student id from form data
			String theVendorID = request.getParameter("vendorID");
			
			// get student from database (db util)
			Vendor theVendor = vendorDbUtil.getVendor(theVendorID);
			
			// place student in the request attribute
			request.setAttribute("THE_VENDOR", theVendor);
			
			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/update-vendor-form.jsp");
			dispatcher.forward(request, response);		
		}



	private void addVendor(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
	
	    
		String vendName = request.getParameter("vendName");
			String vendStreetAddress = request.getParameter("vendStreetAddress");
			  String vendCity = request.getParameter("vendCity");
			  String vendState = request.getParameter("vendState");
				String vendZipCode = request.getParameter("vendZipCode");
					String vendPhoneNumber = request.getParameter("vendPhoneNumber");
					String vendFaxNumber = request.getParameter("vendFaxNumber");
					String vendWebPage = request.getParameter("vendWebPage");
					String vendEmailAddress = request.getParameter("vendEmailAddress");
		
		// create a new student object
					Vendor theVendor = new Vendor(vendName,vendStreetAddress,vendCity,vendState,vendZipCode,vendPhoneNumber,vendFaxNumber,vendWebPage,vendEmailAddress);
		
		// add the student to the database
					vendorDbUtil.addVendor(theVendor);
				
		// send back to main page (the student list)
		listVendor(request, response);
	}

	private void listVendor(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get students from db util
		List<Vendor> vendor = vendorDbUtil.getVendor();
		
		// add students to the request
		request.setAttribute("VENDOR_LIST", vendor);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-vendor.jsp");
		dispatcher.forward(request, response);
	}

}
















