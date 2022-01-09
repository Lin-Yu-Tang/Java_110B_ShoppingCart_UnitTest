package com.example.dao;

import com.example.model.ShoppingCart;

/**
 * @author Tom Lin
 * @apiNote 定義購物車相關方法
 */
public interface ShoppingCartService {
	
	public ShoppingCart addProductToCart(ShoppingCart cart, String productId, int quantity);
	
	public ShoppingCart removeProductFromCart(ShoppingCart cart, String productId);

}