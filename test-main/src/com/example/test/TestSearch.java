package com.example.test;

import java.util.ArrayList;

import com.example.dao.ProductServiceImpl;
import com.example.model.Product;

public class TestSearch {
	public static void main(String[] args) {
		
		ProductServiceImpl service = new ProductServiceImpl();
		
		
		String[] strs = {"蘋果"};
		System.out.println("關鍵字: " + strs[0]);
		ArrayList<Product> products = service.searchProduct(strs);
		
		for (Product p:products) {
			System.out.println(p.toString());
		}
		
		
	}
}
