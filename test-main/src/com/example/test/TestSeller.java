package com.example.test;

import com.example.dao.SellerServiceImpl;
import com.example.service.SellerLoginVerifyService;

public class TestSeller {
	public static void main(String[] args) {
		SellerLoginVerifyService service = new SellerLoginVerifyService();
		boolean pass = service.loginCheck("root", "12534");
		
		System.out.println(pass);
	}
}
