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
@WebServlet("/CategoryControllerServlet")
public class CategoryControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategoryDbUtil categoryDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			categoryDbUtil = new CategoryDbUtil(dataSource);
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
				listCategory(request, response);
				break;
				
			case "ADD":
				addCategory(request, response);
				break;
				
			case "LOAD":
				loadCategory(request, response);
				break;
				
			case "UPDATE":
				updateCategory(request, response);
				break;
				
			case "DELETE":
				deleteCategory(request, response);
				break;	
				
			default:
				listCategory(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student id from form data
			String theCategoryID = request.getParameter("categoryID");
			
			// delete student from database
			categoryDbUtil.deleteCategory(theCategoryID);
			
			// send them back to "list students" page
			listCategory(request, response);
		}
	
	private void updateCategory(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
			// read student info from form data
			int categoryID = Integer.parseInt(request.getParameter("categoryID"));
			String categoryDesc = request.getParameter("categoryDesc");
			
			
			// create a new student object
			Category theCategory = new Category(categoryID, categoryDesc);
			
			// perform update on database
			categoryDbUtil.updateCategory(theCategory);
			
			// send them back to the "list students" page
			listCategory(request, response);
			
		}

		private void loadCategory(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read student id from form data
			String theCategoryID = request.getParameter("categoryID");
			
			// get student from database (db util)
			Category theCategory = categoryDbUtil.getCategory(theCategoryID);
			
			// place student in the request attribute
			request.setAttribute("THE_CATEGORY", theCategory);
			
			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/update-category-form.jsp");
			dispatcher.forward(request, response);		
		}

	private void addCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
		
	    
		String categoryDesc = request.getParameter("categoryDesc");
		
		// create a new student object
		Category theCategory = new Category(categoryDesc);
		
		// add the student to the database
		categoryDbUtil.addCategory(theCategory);
				
		// send back to main page (the student list)
		listCategory(request, response);
	}


	private void listCategory(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get students from db util
		List<Category> category = categoryDbUtil.getCategory();
		
		// add students to the request
		request.setAttribute("CATEGORY_LIST", category);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-category.jsp");
		dispatcher.forward(request, response);
	}

}














