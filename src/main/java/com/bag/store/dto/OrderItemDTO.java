package com.bag.store.dto;

public class OrderItemDTO {
private int id;
private int order_id;
private int bag_id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getOrder_id() {
	return order_id;
}
public void setOrder_id(int order_id) {
	this.order_id = order_id;
}
public int getBag_id() {
	return bag_id;
}
public void setBag_id(int bag_id) {
	this.bag_id = bag_id;
}


}
