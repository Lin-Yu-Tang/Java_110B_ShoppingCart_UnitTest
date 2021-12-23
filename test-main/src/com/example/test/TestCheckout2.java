package com.example.test;

import java.util.HashMap;

import com.example.dao.CheckoutServiceImpl;
import com.example.dao.ProductServiceImpl;
import com.example.dao.ShoppingCartServiceImpl;
import com.example.model.ShoppingCart;

public class TestCheckout2 {

	public static void main(String[] args) {
		
		// 購物車
		ShoppingCartServiceImpl cartService = new ShoppingCartServiceImpl();
		CheckoutServiceImpl checkoutService = new CheckoutServiceImpl();
		ProductServiceImpl productService = new ProductServiceImpl();
		ShoppingCart cart = new ShoppingCart();
		cart = cartService.addProductToCart(cart, "1", 100);
		cart = cartService.addProductToCart(cart, "3", 2);
		
		// 庫存資訊
//		HashMap<Integer, Integer> map = productService.getCurrentStorage(cart.getProducts());
//		cart.getProducts().forEach(
//				i -> 
//		System.out.println(i.getId() + " : " + map.get(Integer.parseInt(i.getId()))));
		
		
		
		// checkout procedure
		// argument: String userId, String shippingAddress, ShoppingCart cart
		boolean checkoutRes = checkoutService.checkout("1", "大馬路", cart);
		
		if (checkoutRes == true) {
			System.out.println("結帳成功");
		}else {
			System.out.println("結帳失敗");
		}
		
		
		
		
	}

}
