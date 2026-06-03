package com.bag.store.dto;

import java.time.LocalDateTime;

public class PaymentDTO {
private int id;
private int paymentTypeId;
private LocalDateTime dateAndTime;
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
public LocalDateTime getDateAndTime() {
	return dateAndTime;
}
public void setDateAndTime(LocalDateTime dateAndTime) {
	this.dateAndTime = dateAndTime;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}


}
