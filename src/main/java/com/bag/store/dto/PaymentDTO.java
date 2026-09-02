package com.bag.store.dto;

import java.sql.Timestamp;
public class PaymentDTO {
private int id;
private int paymentTypeId;
private Timestamp dateAndTime; 
private double amount;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getPaymentTypeId() {
	return paymentTypeId;
}
public void setPaymentTypeId(int paymentTypeId) {
	this.paymentTypeId = paymentTypeId;
}
public Timestamp getDateAndTime() {
	return dateAndTime;
}
public void setDateAndTime(Timestamp dateAndTime) {
	this.dateAndTime = dateAndTime;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}


}
