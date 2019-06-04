package com.luv2code.web.jdbc;

public class ProductVendor {
	
	private int vendorProductId;
	private String vendorProductNumber;
	private String vendorID;
	private String wholeSalePrice;
	private String daysToDeliver;
	
	public ProductVendor(String vendorProductNumber, String vendorID, String wholeSalePrice, String daysToDeliver) {
		
		this.vendorProductNumber = vendorProductNumber;
		this.vendorID = vendorID;
		this.wholeSalePrice = wholeSalePrice;
		this.daysToDeliver = daysToDeliver;
	}
	
public ProductVendor(int vendorProductId, String vendorProductNumber, String vendorID, String wholeSalePrice, String daysToDeliver) {
		
	    this.vendorProductId = vendorProductId;
		this.vendorProductNumber = vendorProductNumber;
		this.vendorID = vendorID;
		this.wholeSalePrice = wholeSalePrice;
		this.daysToDeliver = daysToDeliver;
	}


public int getVendorProductId() {
	return vendorProductId;
}

public void setVendorProductId(int vendorProductId) {
	this.vendorProductId = vendorProductId;
}

public String getVendorProductNumber() {
	return vendorProductNumber;
}

public void setVendorProductNumber(String vendorProductNumber) {
	this.vendorProductNumber = vendorProductNumber;
}

public String getVendorID() {
	return vendorID;
}

public void setVendorID(String vendorID) {
	this.vendorID = vendorID;
}

public String getWholeSalePrice() {
	return wholeSalePrice;
}

public void setWholeSalePrice(String wholeSalePrice) {
	this.wholeSalePrice = wholeSalePrice;
}

public String getDaysToDeliver() {
	return daysToDeliver;
}

public void setDaysToDeliver(String daysToDeliver) {
	this.daysToDeliver = daysToDeliver;
}


@Override
public String toString() {
	return "ProductVendor [vendorProductId=" + vendorProductId + ", vendorProductNumber=" + vendorProductNumber + ", vendorID=" + vendorID + ", wholeSalePrice=" + wholeSalePrice + ", daysToDeliver=" + daysToDeliver + "]";
}	

}