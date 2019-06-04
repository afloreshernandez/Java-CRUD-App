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
@WebServlet("/ProductVendorControllerServlet")
public class ProductVendorControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductVendorDbUtil productVendorDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			productVendorDbUtil = new ProductVendorDbUtil(dataSource);
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
				listProductVendor(request, response);
				break;
				
			case "ADD":
				addProductVendor(request, response);
				break;
				
			case "LOAD":
				loadProductVendor(request, response);
				break;
				
			case "UPDATE":
				updateProductVendor(request, response);
				break;
				
			case "DELETE":
				deleteProductVendor(request, response);
				break;
				
			default:
				listProductVendor(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void deleteProductVendor(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student id from form data
			String theVendorProductId = request.getParameter("vendorProductId");
			
		
			// delete student from database
			productVendorDbUtil.deleteProductVendor(theVendorProductId);
			
			// send them back to "list students" page
			listProductVendor(request, response);
		}
	
	private void updateProductVendor(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

	
		 
			// read student info from form data
			int vendorProductId = Integer.parseInt(request.getParameter("vendorProductId"));
			String vendorProductNumber = request.getParameter("vendorProductNumber");
			String vendorID = request.getParameter("vendorID");
			String wholeSalePrice = request.getParameter("wholeSalePrice");
			String daysToDeliver = request.getParameter("daysToDeliver");
			
			
			// create a new student object
			ProductVendor theProductVendor = new ProductVendor(vendorProductId, vendorProductNumber, vendorID, wholeSalePrice, daysToDeliver);
			
			// perform update on database
			productVendorDbUtil.updateProductVendor(theProductVendor);
			
			// send them back to the "list students" page
			listProductVendor(request, response);
			
		}

		private void loadProductVendor(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read student id from form data
			String theVendorProductId = request.getParameter("vendorProductId");
			
			// get student from database (db util)
			ProductVendor theProductVendor = productVendorDbUtil.getProductVendor(theVendorProductId);
			
			// place student in the request attribute
			request.setAttribute("THE_PRODUCTVENDOR", theProductVendor);
			
			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/update-productVendor-form.jsp");
			dispatcher.forward(request, response);		
		}


	private void addProductVendor(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
		
		
	    String vendorProductNumber = request.getParameter("vendorProductNumber");
		String vendorID = request.getParameter("vendorID");
		  String wholeSalePrice = request.getParameter("wholeSalePrice");
			String daysToDeliver = request.getParameter("daysToDeliver");
			
		// create a new student object
		ProductVendor theProductVendor = new ProductVendor(vendorProductNumber,vendorID,wholeSalePrice,daysToDeliver);
		
		// add the student to the database
		productVendorDbUtil.addProductVendor(theProductVendor);
				
		// send back to main page (the student list)
		listProductVendor(request, response);
	}


	private void listProductVendor(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get students from db util
		List<ProductVendor> productVendor = productVendorDbUtil.getProductVendor();
		
		// add students to the request
		request.setAttribute("PRODUCTVENDOR_LIST", productVendor);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-productVendor.jsp");
		dispatcher.forward(request, response);
	}

}















