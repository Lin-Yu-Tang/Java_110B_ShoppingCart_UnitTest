package com.example.dao;

import com.example.model.ShoppingCart;

public interface ShoppingCartService {
	
	public ShoppingCart addProductToCart(String ProductId);
	
	public ShoppingCart removeProductFromCart(ShoppingCart cart, String ProductId);
	
	
	public ShoppingCart appendProductToCart(ShoppingCart cart, String ProductId);
}