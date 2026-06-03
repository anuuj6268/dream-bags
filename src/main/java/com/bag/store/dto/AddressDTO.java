package com.bag.store.dto;

public class AddressDTO {
private int id;
private int userId;
private String houseNumber;
private String landmark;
private int pincodeId;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getHouseNumber() {
	return houseNumber;
}
public void setHouseNumber(String houseNumber) {
	this.houseNumber = houseNumber;
}
public String getLandmark() {
	return landmark;
}
public void setLandmark(String landmark) {
	this.landmark = landmark;
}
public int getPincodeId() {
	return pincodeId;
}
public void setPincodeId(int pincodeId) {
	this.pincodeId = pincodeId;
}
}
