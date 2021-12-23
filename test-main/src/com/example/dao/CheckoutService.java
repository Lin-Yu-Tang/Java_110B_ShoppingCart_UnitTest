package com.example.dao;

import com.example.model.ShoppingCart;

public interface CheckoutService {
	public boolean checkout(String userId, String shippingAddress, ShoppingCart cart);
	
	
}
