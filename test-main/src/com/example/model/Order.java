package com.example.model;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	
	private String id; // auto_increment
	private String date;
	private int totalPrice;
	private String status;
	private String userId;
	private String shippingAddress;
	private ArrayList<OrderItem> items;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ArrayList<OrderItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<OrderItem> items) {
		this.items = items;
	}
	
	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", totalPrice=" + totalPrice + ", status=" + status + ", userId="
				+ userId + ", shippingAddress=" + shippingAddress + ", items=" + items + "]";
	}


	
}
