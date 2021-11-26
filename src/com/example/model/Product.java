package com.example.model;

import java.sql.Blob;
import java.sql.Clob;

public class Product {
	
	private String id;
	private String name;
	private Blob picture;
	private int price;
	private String description;
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Blob getPicture() {
		return picture;
	}
	public void setPicture(Blob picture) {
		this.picture = picture;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", picture=" + picture + ", price=" + price + ", description="
				+ description + "]";
	}
	
	
}
