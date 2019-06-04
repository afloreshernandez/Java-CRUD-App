package com.luv2code.web.jdbc;

public class Vendor {
     
	 
	 private int vendorID;
	 private String	vendName;
	 private String	vendStreetAddress;
	 private String	vendCity;
	 private String	vendState;
	 private String	vendZipCode;
	 private String	vendPhoneNumber;
	 private String	vendFaxNumber;
	 private String	vendWebPage;
	 private String vendEmailAddress;
	 
	 public Vendor( String vendName, String vendStreetAddress, String vendCity, String vendState,
				String vendZipCode, String vendPhoneNumber, String vendFaxNumber, String vendWebPage,
				String vendEmailAddress) {
			
			
		
			this.vendName = vendName;
			this.vendStreetAddress = vendStreetAddress;
			this.vendCity = vendCity;
			this.vendState = vendState;
			this.vendZipCode = vendZipCode;
			this.vendPhoneNumber = vendPhoneNumber;
			this.vendFaxNumber = vendFaxNumber;
			this.vendWebPage = vendWebPage;
			this.vendEmailAddress = vendEmailAddress;
		}

	 public Vendor(int vendorID, String vendName, String vendStreetAddress, String vendCity, String vendState,
			String vendZipCode, String vendPhoneNumber, String vendFaxNumber, String vendWebPage,
			String vendEmailAddress) {
		
		
		this.vendorID = vendorID;
		this.vendName = vendName;
		this.vendStreetAddress = vendStreetAddress;
		this.vendCity = vendCity;
		this.vendState = vendState;
		this.vendZipCode = vendZipCode;
		this.vendPhoneNumber = vendPhoneNumber;
		this.vendFaxNumber = vendFaxNumber;
		this.vendWebPage = vendWebPage;
		this.vendEmailAddress = vendEmailAddress;
	}


	public int getVendorID() {
		return vendorID;
	}


	public void setVendorID(int vendorID) {
		this.vendorID = vendorID;
	}


	public String getVendName() {
		return vendName;
	}


	public void setVendName(String vendName) {
		this.vendName = vendName;
	}


	public String getVendStreetAddress() {
		return vendStreetAddress;
	}


	public void setVendStreetAddress(String vendStreetAddress) {
		this.vendStreetAddress = vendStreetAddress;
	}


	public String getVendCity() {
		return vendCity;
	}


	public void setVendCity(String vendCity) {
		this.vendCity = vendCity;
	}


	public String getVendState() {
		return vendState;
	}


	public void setVendState(String vendState) {
		this.vendState = vendState;
	}


	public String getVendZipCode() {
		return vendZipCode;
	}


	public void setVendZipCode(String vendZipCode) {
		this.vendZipCode = vendZipCode;
	}


	public String getVendPhoneNumber() {
		return vendPhoneNumber;
	}


	public void setVendPhoneNumber(String vendPhoneNumber) {
		this.vendPhoneNumber = vendPhoneNumber;
	}


	public String getVendFaxNumber() {
		return vendFaxNumber;
	}


	public void setVendFaxNumber(String vendFaxNumber) {
		this.vendFaxNumber = vendFaxNumber;
	}


	public String getVendWebPage() {
		return vendWebPage;
	}


	public void setVendWebPage(String vendWebPage) {
		this.vendWebPage = vendWebPage;
	}


	public String getVendEmailAddress() {
		return vendEmailAddress;
	}


	public void setVendEmailAddress(String vendEmailAddress) {
		this.vendEmailAddress = vendEmailAddress;
	}


	 
	@Override
	public String toString() {
		return "Vendor [vendorID=" + vendorID + ", vendName=" + vendName + ", vendStreetAddress=" + vendStreetAddress + ", vendCity=" + vendCity + ", vendState=" + vendState + ", vendZipCode=" + vendZipCode + ", vendPhoneNumber=" + vendPhoneNumber + ", vendFaxNumber=" + vendFaxNumber + ", vendWebPage=" + vendWebPage + ", vendEmailAddress=" + vendEmailAddress + "]";
	}
	 
	
}
