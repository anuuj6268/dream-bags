package com.bag.store.dto;

public class ViewCartDTO {
private int bagId;
private int cartItemId;
private String bagName;
private int bagPrice;
private String bagURL;
private int quantity;
public int getBagId() {
	return bagId;
}
public void setBagId(int bagId) {
	this.bagId = bagId;
}
public int getCartItemId() {
	return cartItemId;
}
public void setCartItemId(int cartItemId) {
	this.cartItemId = cartItemId;
}
public String getBagName() {
	return bagName;
}
public void setBagName(String bagName) {
	this.bagName = bagName;
}
public int getBagPrice() {
	return bagPrice;
}
public void setBagPrice(int bagPrice) {
	this.bagPrice = bagPrice;
}
public String getBagURL() {
	return bagURL;
}
public void setBagURL(String bagURL) {
	this.bagURL = bagURL;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}


}
