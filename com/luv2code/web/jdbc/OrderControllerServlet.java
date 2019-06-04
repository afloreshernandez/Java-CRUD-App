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
@WebServlet("/OrderControllerServlet")
public class OrderControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderDbUtil orderDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			orderDbUtil = new OrderDbUtil(dataSource);
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
				listOrders(request, response);
				break;
				
			case "ADD":
				addOrders(request, response);
				break;
			
			case "LOAD":
				loadOrder(request, response);
				break;
				
			case "UPDATE":
				updateOrder(request, response);
				break;
				
			case "DELETE":
				deleteOrder(request, response);
				break;
				
			default:
				listOrders(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student id from form data
			String theOrderId = request.getParameter("orderId");
			
			// delete student from database
			orderDbUtil.deleteOrder(theOrderId);
			
			// send them back to "list students" page
			listOrders(request, response);
		}
	
	private void updateOrder(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student info from form data
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			String orderNumber = request.getParameter("orderNumber");
			String orderDate = request.getParameter("orderDate");
			String shipDate = request.getParameter("shipDate");
			String customerID = request.getParameter("customerID");
			String employeeID = request.getParameter("employeeID");
			
			// create a new student object
			Order theOrder = new Order(orderId, orderNumber, orderDate, shipDate, customerID,employeeID);
			
			// perform update on database
			orderDbUtil.updateOrder(theOrder);
			
			// send them back to the "list students" page
			listOrders(request, response);
			
		}

		private void loadOrder(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read student id from form data
			String theOrderId = request.getParameter("orderId");
			
			// get student from database (db util)
			Order theOrder = orderDbUtil.getOrder(theOrderId);
			
			// place student in the request attribute
			request.setAttribute("THE_ORDER", theOrder);
			
			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/update-order-form.jsp");
			dispatcher.forward(request, response);		
		}


	private void addOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data

	    String orderNumber = request.getParameter("orderNumber");
		String orderDate = request.getParameter("orderDate");
		  String shipDate = request.getParameter("shipDate");
			String  customerID = request.getParameter("customerID");
			  String employeeID = request.getParameter("employeeID");
			
		// create a new student object
		Order theOrder = new Order(orderNumber,orderDate,shipDate,customerID,employeeID);
		
		// add the student to the database
		orderDbUtil.addOrder(theOrder);
				
		// send back to main page (the student list)
		listOrders(request, response);
	}

	private void listOrders(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get students from db util
		List<Order> orders = orderDbUtil.getOrders();
		
		// add students to the request
		request.setAttribute("ORDER_LIST", orders);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-orders.jsp");
		dispatcher.forward(request, response);
	}

}













