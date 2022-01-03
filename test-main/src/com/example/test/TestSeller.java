package com.example.test;

import java.util.ArrayList;

import com.example.dao.SellerServiceImpl;
import com.example.model.SellerOrder;
import com.example.service.SellerLoginVerifyService;

public class TestSeller {
	public static void main(String[] args) {
		// 賣家登入驗證service測試
		SellerLoginVerifyService service = new SellerLoginVerifyService();
		boolean pass = service.loginCheck("root", "12534");
		System.out.println(pass);
		
		// 賣家DAO 取得Id
		SellerServiceImpl sellerService = new SellerServiceImpl();
		String sellerId = sellerService.getSellerId("root");
		System.out.println(sellerId);
		
		
		ArrayList<SellerOrder> allSellerOrders = sellerService.getAllSellerOrders(sellerId);
		
		
		allSellerOrders.forEach(i -> System.out.println(i));
		
		
		
		
		
		
		
		
		
		
		
	}
}
