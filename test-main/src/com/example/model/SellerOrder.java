package com.example.model;

import java.util.ArrayList;
import java.util.Date;

public class SellerOrder {
	private String id; // auto_increment
	private String sellerId;
	private String userId;
	private String status;
	private String shippingAddress;
	private Date createTime;
	private ArrayList<SellerOrderItem> items;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public ArrayList<SellerOrderItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<SellerOrderItem> items) {
		this.items = items;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "SellerOrder [id=" + id + ", sellerId=" + sellerId + ", userId=" + userId + ", status=" + status
				+ ", shippingAddress=" + shippingAddress + ", createTime=" + createTime + ", items=" + items + "]";
	}
	

	
	
}
