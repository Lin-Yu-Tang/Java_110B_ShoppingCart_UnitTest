package com.example.test;

import java.util.ArrayList;

import com.example.dao.ShoppingCartServiceImpl;
import com.example.model.Product;
import com.example.model.ShoppingCart;

public class TestShoppingCart {
	public static void main(String[] args) {
		
		ShoppingCartServiceImpl service = new ShoppingCartServiceImpl();
		
		ShoppingCart cart = new ShoppingCart();
		
		cart = service.addProductToCart(cart, "1", 20);
		cart = service.addProductToCart(cart, "1", 10); // update case
		cart = service.addProductToCart(cart, "1", 12);
		cart = service.addProductToCart(cart, "3", 1);
		cart = service.addProductToCart(cart, "3", 2);
		cart = service.addProductToCart(cart, "3", 10);
		cart.cartInfo();
		
		// test remove
		System.out.println("remove test!!");
		cart = service.removeProductFromCart(cart, "1");
		cart = service.removeProductFromCart(cart, "2");
		cart = service.removeProductFromCart(cart, "1");
		cart = service.removeProductFromCart(cart, "3");
		cart = service.removeProductFromCart(cart, "1");
		cart.cartInfo();
		
		
		
		
		
	}
	
	
}
