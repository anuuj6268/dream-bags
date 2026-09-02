package com.bag.store.dto;

public class AddressDTO {
private int id;
private int userId;
private String mobile;
private String houseNumber;
private String landmark;

public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}

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
