package com.example.model;

public class OrderItem {
	private String id;
	private String productId;
	private int quantity;
	private String orderId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", productId=" + productId + ", quantity=" + quantity + ", orderId=" + orderId
				+ "]";
	}
	
	
	
}
