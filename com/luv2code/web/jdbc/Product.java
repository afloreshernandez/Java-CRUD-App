package com.luv2code.web.jdbc;

public class Product {
     
	
	 private int productId;
	 private String productNumber;
	 private String productName;
	 private String productDesc;
	 private String retailPrice;
	 private String quantityOnHand;
	 private String categoryID;
	 
	 
	 public Product(String productNumber, String productName, String productDesc, String retailPrice,
			String quantityOnHand, String categoryID) {
		
		this.productNumber = productNumber;
		this.productName = productName;
		this.productDesc = productDesc;
		this.retailPrice = retailPrice;
		this.quantityOnHand = quantityOnHand;
		this.categoryID = categoryID;
	}

	 public Product(int productId,String productNumber, String productName, String productDesc, String retailPrice,
				String quantityOnHand, String categoryID) {
			
		    this.productId = productId;
			this.productNumber = productNumber;
			this.productName = productName;
			this.productDesc = productDesc;
			this.retailPrice = retailPrice;
			this.quantityOnHand = quantityOnHand;
			this.categoryID = categoryID;
		}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}

	public String getQuantityOnHand() {
		return quantityOnHand;
	}

	public void setQuantityOnHand(String quantityOnHand) {
		this.quantityOnHand = quantityOnHand;
	}

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	 
		
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productNumber=" + productNumber + ", productName=" + productName + ", productDesc=" + productDesc + ", retailPrice=" + retailPrice + ",quantityOnHand=" + quantityOnHand + ",categoryID=" + categoryID + "]";
	}		
	 
}

