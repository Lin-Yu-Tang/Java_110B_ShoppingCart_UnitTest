package com.example.model;

import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;

public class Product {
	
	private String id;
	private String name;
	private Blob picture;
	private int price;
	private int quantity;
	private String seller_id;
	private String description;
	// by SQL default
	private String created_time;
	private String update_time;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreated_time() {
		return created_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", picture=" + picture + ", price=" + price + ", quantity="
				+ quantity + ", seller_id=" + seller_id + ", description=" + description + ", created_time="
				+ created_time + ", update_time=" + update_time + "]";
	}
	
	
}
