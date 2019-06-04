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
@WebServlet("/OrderDetailControllerServlet")
public class OrderDetailControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderDetailDbUtil orderDetailDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			orderDetailDbUtil = new OrderDetailDbUtil(dataSource);
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
				listOrderDetail(request, response);
				break;
				
			case "ADD":
				addOrderDetail(request, response);
				break;
				
			case "LOAD":
				loadOrderDetail(request, response);
				break;
				
			case "UPDATE":
				updateOrderDetail(request, response);
				break;
			
			case "DELETE":
				deleteOrderDetail(request, response);
				break;
				
			default:
				listOrderDetail(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}
	
	private void deleteOrderDetail(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student id from form data
			String theOrderDetailId = request.getParameter("orderDetailId");
			
			// delete student from database
			orderDetailDbUtil.deleteOrderDetail(theOrderDetailId);
			
			// send them back to "list students" page
			listOrderDetail(request, response);
		}

	private void updateOrderDetail(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student info from form data
			int orderDetailId = Integer.parseInt(request.getParameter("orderDetailId"));
			String orderDetailNumber = request.getParameter("orderDetailNumber");
			String productDetailNumber = request.getParameter("productDetailNumber");
			String quotedPrice = request.getParameter("quotedPrice");
			String quantityOrdered = request.getParameter("quantityOrdered");
			
			// create a new student object
			OrderDetail theOrderDetail = new OrderDetail(orderDetailId, orderDetailNumber, productDetailNumber, quotedPrice, quantityOrdered);
			
			// perform update on database
			orderDetailDbUtil.updateOrderDetail(theOrderDetail);
			
			// send them back to the "list students" page
			listOrderDetail(request, response);
			
		}

		private void loadOrderDetail(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read student id from form data
			String theOrderDetailId = request.getParameter("orderDetailId");
			
			// get student from database (db util)
			OrderDetail theOrderDetail = orderDetailDbUtil.getOrderDetail(theOrderDetailId);
			
			// place student in the request attribute
			request.setAttribute("THE_ORDERDETAIL", theOrderDetail);
			
			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/update-orderDetail-form.jsp");
			dispatcher.forward(request, response);		
		}


	private void addOrderDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
		 
	    String orderDetailNumber = request.getParameter("orderDetailNumber");
		String productDetailNumber = request.getParameter("productDetailNumber");
		  String quotedPrice = request.getParameter("quotedPrice");
			String quantityOrdered = request.getParameter("quantityOrdered");
			
		// create a new student object
		OrderDetail theOrderDetail = new OrderDetail(orderDetailNumber, productDetailNumber,quotedPrice,quantityOrdered);
		
		// add the student to the database
		orderDetailDbUtil.addOrderDetail(theOrderDetail);
				
		// send back to main page (the student list)
		listOrderDetail(request, response);
	}
	private void listOrderDetail(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get students from db util
		List<OrderDetail> orderDetail = orderDetailDbUtil.getOrderDetail();
		
		// add students to the request
		request.setAttribute("ORDERDETAIL_LIST", orderDetail);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-orderDetail.jsp");
		dispatcher.forward(request, response);
	}

}














