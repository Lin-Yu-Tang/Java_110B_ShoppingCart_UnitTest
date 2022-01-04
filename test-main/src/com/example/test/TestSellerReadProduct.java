package com.example.test;

import java.util.ArrayList;

import com.example.dao.ProductServiceImpl;
import com.example.model.Product;

public class TestSellerReadProduct {

	public static void main(String[] args) {
		ProductServiceImpl service = new ProductServiceImpl();
		
		ArrayList<Product> products = service.getAllProductsBySellerId("1");
		
		System.out.println(products);
	}

}
