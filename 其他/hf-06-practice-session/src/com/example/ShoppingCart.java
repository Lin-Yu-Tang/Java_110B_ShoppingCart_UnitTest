package com.example;

public class ShoppingCart {
	private int amount;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "ShoppingCart [amount=" + amount + "]";
	}
	
	
}
