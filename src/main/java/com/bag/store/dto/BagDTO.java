package com.bag.store.dto;

public class BagDTO {
private int id;
private String name;
private int price;
private int bagType;
private String material;
private String url;
public int getId() {
	return id;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getBagType() {
	return bagType;
}
public void setBagType(int bagType) {
	this.bagType = bagType;
}
public String getMaterial() {
	return material;
}
public void setMaterial(String material) {
	this.material = material;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}

}
