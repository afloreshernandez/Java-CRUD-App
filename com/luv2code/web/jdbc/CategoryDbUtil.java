package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class CategoryDbUtil {

	private DataSource dataSource;

	public CategoryDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Category> getCategory() throws Exception {
		
		List<Category> category = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from category order by category_Desc";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				
				int categoryID = myRs.getInt("category_ID");
				String categoryDesc = myRs.getString("category_Desc");
				
				// create new student object
				Category tempCategory = new Category(categoryID,categoryDesc);
				
				// add it to the list of students
				category.add(tempCategory);				
			}
			
			return category;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void addCategory(Category theCategory) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into category "
					   + "(category_ID, category_Desc) "
					   + "values (?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setInt(1, theCategory.getCategoryID());
			myStmt.setString(2, theCategory.getCategoryDesc());
			
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public Category getCategory(String theCategoryID) throws Exception {

		Category theCategory = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int categoryID;
		
		try {
			// convert student id to int
			categoryID = Integer.parseInt(theCategoryID);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from category where category_ID=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, categoryID);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			
			// retrieve data from result set row
			if (myRs.next()) {
				String categoryDesc = myRs.getString("category_Desc");
				
			
				
				// use the studentId during construction
				theCategory = new Category(categoryID,categoryDesc);
			}
			else {
				throw new Exception("Could not find category id: " + categoryID);
			}				
			
			return theCategory;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateCategory(Category theCategory) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update category "
						+ "set category_Desc=? "
						+ "where category_ID=?";
						
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
						//myStmt.setString(1, "josephine2");
						//myStmt.setString(2, "smith2");
						//myStmt.setString(3, "ringo2@gmail.com");
						//myStmt.setInt(4,3);
			
			// set params
			
			
			myStmt.setString(1, theCategory.getCategoryDesc());
			
			myStmt.setInt(2, theCategory.getCategoryID());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public void deleteCategory(String theCategoryID) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int categoryID = Integer.parseInt(theCategoryID);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from category where category_ID=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			
			// set params
			myStmt.setInt(1, categoryID);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
}

