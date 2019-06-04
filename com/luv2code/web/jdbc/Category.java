package com.luv2code.web.jdbc;

public class Category 
{
	
	private int categoryID;
	private String categoryDesc;
	
	
	public Category(String categoryDesc) {
		
	
		this.categoryDesc = categoryDesc;
	}
	
	public Category(int categoryID, String categoryDesc) {
			
		this.categoryID = categoryID;
		this.categoryDesc = categoryDesc;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	@Override
	public String toString() {
		return "Category [categoryID=" + categoryID + ", categoryDesc=" + categoryDesc + "]";
	}	

	
}

	

	
	
