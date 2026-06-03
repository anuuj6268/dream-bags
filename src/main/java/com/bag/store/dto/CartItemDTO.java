package com.bag.store.dto;

public class CartItemDTO {
private int id;
private int cart_id;
private int bag_id;
private int quantity;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCart_id() {
	return cart_id;
}
public void setCart_id(int cart_id) {
	this.cart_id = cart_id;
}
public int getBag_id() {
	return bag_id;
}
public void setBag_id(int bag_id) {
	this.bag_id = bag_id;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}

}
