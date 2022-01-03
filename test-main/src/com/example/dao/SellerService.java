package com.example.dao;

import java.util.ArrayList;

import com.example.model.SellerOrder;

public interface SellerService {
	
	public String getLoginPass(String sellerAccount);
	
	public String getSellerId(String sellerAccount);
	
	public ArrayList<SellerOrder> getAllSellerOrders(String sellerId);
	
}
