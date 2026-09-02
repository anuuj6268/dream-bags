package com.bag.store.dto;

public class PincodeDTO {
private int pincode_id;
private String city;
private String state;
private int pincode;
public int getPincode() {
	return pincode;
}
public void setPincode(int pincode) {
	this.pincode = pincode;
}
public int getPincode_id() {
	return pincode_id;
}
public void setPincode_id(int pincode_id) {
	this.pincode_id = pincode_id;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
}
