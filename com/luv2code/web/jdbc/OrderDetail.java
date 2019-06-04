package com.luv2code.web.jdbc;

public class OrderDetail {

private int orderDetailId;
private String orderDetailNumber;
private String productDetailNumber;
private String quotedPrice;
private String quantityOrdered;

public OrderDetail(String orderDetailNumber, String productDetailNumber, String quotedPrice, String quantityOrdered) {
	
	this.orderDetailNumber = orderDetailNumber;
	this.productDetailNumber = productDetailNumber;
	this.quotedPrice = quotedPrice;
	this.quantityOrdered = quantityOrdered;
}

public OrderDetail(int orderDetailId,String orderDetailNumber, String productDetailNumber, String quotedPrice, String quantityOrdered) {
	
	
	this.orderDetailId = orderDetailId;
	this.orderDetailNumber = orderDetailNumber;
	this.productDetailNumber = productDetailNumber;
	this.quotedPrice = quotedPrice;
	this.quantityOrdered = quantityOrdered;
}

public int getOrderDetailId() {
	return orderDetailId;
}

public void setOrderDetailId(int orderDetailId) {
	this.orderDetailId = orderDetailId;
}

public String getOrderDetailNumber() {
	return orderDetailNumber;
}

public void setOrderDetailNumber(String orderDetailNumber) {
	this.orderDetailNumber = orderDetailNumber;
}

public String getProductDetailNumber() {
	return productDetailNumber;
}

public void setProductDetailNumber(String productDetailNumber) {
	this.productDetailNumber = productDetailNumber;
}

public String getQuotedPrice() {
	return quotedPrice;
}

public void setQuotedPrice(String quotedPrice) {
	this.quotedPrice = quotedPrice;
}

public String getQuantityOrdered() {
	return quantityOrdered;
}

public void setQuantityOrdered(String quantityOrdered) {
	this.quantityOrdered = quantityOrdered;
}


@Override
public String toString() {
	return "OrderDetail [orderDetailId=" + orderDetailId + ", orderDetailNumber=" + orderDetailNumber + ", productDetailNumber=" + productDetailNumber + ", quotedPrice=" + quotedPrice + ", quantityOrdered=" + quantityOrdered + "]";
}	

}
