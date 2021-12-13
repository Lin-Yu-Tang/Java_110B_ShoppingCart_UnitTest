package com.example.test;

import java.util.ArrayList;

import com.example.dao.ProductServiceImpl;
import com.example.model.Product;

public class TestProducts {

	public static void main(String[] args) {
		
		ProductServiceImpl service = new ProductServiceImpl();
		ArrayList<Product> listAllProduct = service.ListAllProduct();
		
		for (Product p:listAllProduct) {
			System.out.println(p);
		}
	}

}
