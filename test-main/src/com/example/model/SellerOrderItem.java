package com.example.model;

public class SellerOrderItem {
	private String id; // auto_increment
	private String sellerId;
	private String productId;
	private int price;
	private int quantity;
	private String orderId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "SellerOrderItem [id=" + id + ", sellerId=" + sellerId + ", productId=" + productId + ", price=" + price
				+ ", quantity=" + quantity + ", orderId=" + orderId + "]";
	}
	
	
	
}
