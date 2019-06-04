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
@WebServlet("/ProductControllerServlet")
public class ProductControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDbUtil productDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			productDbUtil = new ProductDbUtil(dataSource);
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
				listProduct(request, response);
				break;
				
			case "ADD":
				addProduct(request, response);
				break;
				
			case "LOAD":
				loadProduct(request, response);
				break;
				
			case "UPDATE":
				updateProduct(request, response);
				break;
				
			case "DELETE":
				deleteProduct(request, response);
				break;
				
			default:
				listProduct(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student id from form data
			String theProductId = request.getParameter("productId");
			
		
			// delete student from database
			productDbUtil.deleteProduct(theProductId);
			
			// send them back to "list students" page
			listProduct(request, response);
		}
	
	private void updateProduct(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

	
			// read student info from form data
			int productId = Integer.parseInt(request.getParameter("productId"));
			String productNumber = request.getParameter("productNumber");
			String productName = request.getParameter("productName");
			String productDesc = request.getParameter("productDesc");
			String retailPrice = request.getParameter("retailPrice");
			String quantityOnHand = request.getParameter("quantityOnHand");
			String categoryID = request.getParameter("categoryID");
			
			
			// create a new student object
			Product theProduct = new Product(productId, productNumber, productName, productDesc, retailPrice,quantityOnHand,categoryID);
			
			// perform update on database
			productDbUtil.updateProduct(theProduct);
			
			// send them back to the "list students" page
			listProduct(request, response);
			
		}

		private void loadProduct(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read student id from form data
			String theProductId = request.getParameter("productId");
			
			// get student from database (db util)
			Product theProduct = productDbUtil.getProduct(theProductId);
			
			// place student in the request attribute
			request.setAttribute("THE_PRODUCT", theProduct);
			
			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/update-product-form.jsp");
			dispatcher.forward(request, response);		
		}


	private void addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
	
	    String productNumber = request.getParameter("productNumber");
		String productName = request.getParameter("productName");
		  String productDesc = request.getParameter("productDesc");
			String retailPrice = request.getParameter("retailPrice");
			 String quantityOnHand = request.getParameter("quantityOnHand");
				String categoryID = request.getParameter("categoryID");
			
		// create a new student object
		Product theProduct = new Product(productNumber,productName,productDesc,retailPrice,quantityOnHand,categoryID);
		
		// add the student to the database
		productDbUtil.addProduct(theProduct);
				
		// send back to main page (the student list)
		listProduct(request, response);
	}

	private void listProduct(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get students from db util
		List<Product> product = productDbUtil.getProduct();
		
		// add students to the request
		request.setAttribute("PRODUCT_LIST", product);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-product.jsp");
		dispatcher.forward(request, response);
	}

}














