package com.example.test;

import java.util.ArrayList;

import com.example.model.Product;
import com.example.model.ShoppingCart;

public class TestShoppingCart2 {
	public static void main(String[] args) {
		
		
		ShoppingCart cart = new ShoppingCart();
		ArrayList<Product> list = new ArrayList<Product>();
		Product p1 = new Product();
		
		p1.setId("1");
		p1.setQuantity(20);
		
		
		list.add(p1);
		cart.setProducts(list);
		cart.cartInfo();
		
		// continue
		Product p2 = new Product();
		p2.setId("2");
		p2.setQuantity(22);
		list.add(p2);
		
		
		
		for (Product o:list) {
			if (o.getId().equals("2")) {
				System.out.println("duplicate: " + o.getId());
			}
		}
		
		
		
		
		
		
		cart.setProducts(list);
		cart.cartInfo();
		
		
	}
}
