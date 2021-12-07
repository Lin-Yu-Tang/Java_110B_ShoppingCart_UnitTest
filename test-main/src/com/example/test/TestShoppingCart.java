package com.example.test;

import java.util.ArrayList;

import com.example.dao.ShoppingCartServiceImpl;
import com.example.model.Product;
import com.example.model.ShoppingCart;

public class TestShoppingCart {
	public static void main(String[] args) {
		
		ShoppingCartServiceImpl service = new ShoppingCartServiceImpl();
		
		ShoppingCart cart = service.addProductToCart("1");
		
		cart = service.appendProductToCart(cart, "2");
		cart = service.appendProductToCart(cart, "2");
		cart = service.appendProductToCart(cart, "2");
		
		cart = service.removeProductFromCart(cart, "2");
		cart = service.removeProductFromCart(cart, "1");
		cart = service.removeProductFromCart(cart, "1");
		
		cart = service.removeProductFromCart(cart, "2");
		cart = service.removeProductFromCart(cart, "2");
		ArrayList<Product> products = cart.getProducts();
		System.out.println("商品內容");
		System.out.println("Amount: " + cart.getTotalAmount());
		for(Product p:products) {
			System.out.println(p.getName());
		}
		
	}
	
	
}
