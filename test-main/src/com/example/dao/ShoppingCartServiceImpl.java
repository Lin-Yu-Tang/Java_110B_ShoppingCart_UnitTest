package com.example.dao;

import java.util.ArrayList;

import com.example.model.Product;
import com.example.model.ShoppingCart;

public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Override
	public ShoppingCart addProductToCart(String ProductId) {
		ProductServiceImpl productService = new ProductServiceImpl();
		ShoppingCart cart = new ShoppingCart();
		ArrayList<Product> products = new ArrayList<Product>();
		int amount = 0;
		products.add(productService.selectOneProduct(ProductId));
		for(Product p:products) {
			amount += p.getPrice();
		}
		cart.setProducts(products);
		cart.setTotalAmount(amount);
		return cart;
	}

	@Override
	public ShoppingCart removeProductFromCart(ShoppingCart cart, String ProductId) {
		ProductServiceImpl productService = new ProductServiceImpl();
		int amount = 0;
		ArrayList<Product> products = cart.getProducts();
		int targetIndex = -1;
		for (int i = 0; i<products.size(); i++) {
			if (products.get(i).getId().equals(ProductId)) {
				targetIndex = i;
				break;
			}
		}
		
		if (targetIndex != -1)
			products.remove(targetIndex);
		
		for(Product p:products) {
			amount += p.getPrice();
		}
		cart.setProducts(products);
		cart.setTotalAmount(amount);
		return cart;
	}

	@Override
	public ShoppingCart appendProductToCart(ShoppingCart cart, String ProductId) {
		ProductServiceImpl productService = new ProductServiceImpl();
		int amount = 0;
		ArrayList<Product> products = cart.getProducts();
		products.add(productService.selectOneProduct(ProductId));
		for(Product p:products) {
			amount += p.getPrice();
		}
		cart.setTotalAmount(amount);
		return cart;
	}

}
