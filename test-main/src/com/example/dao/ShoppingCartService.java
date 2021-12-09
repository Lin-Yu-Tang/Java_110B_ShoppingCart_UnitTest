package com.example.dao;

import com.example.model.ShoppingCart;

public interface ShoppingCartService {
	
	public ShoppingCart addProductToCart(ShoppingCart cart, String productId, int quantity);
	
	public ShoppingCart removeProductFromCart(ShoppingCart cart, String productId);

}