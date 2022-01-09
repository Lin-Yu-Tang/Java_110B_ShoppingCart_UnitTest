package com.example.dao;

import com.example.model.ShoppingCart;

/**
 * @author Tom Lin
 * 定義買家結帳使用方法
 */
public interface CheckoutService {
	public boolean checkout(String userId, String shippingAddress, ShoppingCart cart);
	
	
}
