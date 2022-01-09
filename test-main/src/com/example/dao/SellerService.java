package com.example.dao;

import java.util.ArrayList;

import com.example.model.SellerOrder;

/**
 * @author Tom Lin
 * @apiNote 定義賣家相關方法
 */
public interface SellerService {
	
	public String getLoginPass(String sellerAccount);
	
	public String getSellerId(String sellerAccount);
	
	public ArrayList<SellerOrder> getAllSellerOrders(String sellerId);
	
}
