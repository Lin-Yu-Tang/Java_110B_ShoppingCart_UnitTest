package com.example.model;

import java.util.ArrayList;

public class ShoppingCart {
	ArrayList<Product> products;
	int totalAmount;
	
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Override
	public String toString() {
		return "ShoppingCart [products=" + products + ", totalAmount=" + totalAmount + "]";
	}
	
	
}
