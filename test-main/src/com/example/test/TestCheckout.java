package com.example.test;

import java.util.ArrayList;
import java.util.HashSet;

import com.example.dao.ProductServiceImpl;
import com.example.dao.ShoppingCartServiceImpl;
import com.example.model.Order;
import com.example.model.OrderItem;
import com.example.model.Product;
import com.example.model.ShoppingCart;

public class TestCheckout {

	public static void main(String[] args) {
		
		ShoppingCart cart = new ShoppingCart();
		ShoppingCartServiceImpl cartService = new ShoppingCartServiceImpl();
		
		cartService.addProductToCart(cart, "1", 20000);
		cartService.addProductToCart(cart, "2", 30);
		
		System.out.println(cart.toString());
		
		Order order = new Order();
		
		ArrayList<OrderItem> orderItemSet = new ArrayList<OrderItem>();
		
		
		
		
		// get all product from cart
		
		ArrayList<Product> products = cart.getProducts();
		
		// save to orderitems
		
		for (int i=0; i<products.size();i++) {
			OrderItem items = new OrderItem();
			items.setId(i+1+"");
			items.setProductId(products.get(i).getId());
			items.setQuantity(products.get(i).getQuantity());
			orderItemSet.add(items);
		}
		System.out.println(orderItemSet.size());
		orderItemSet.forEach((i) -> System.out.println(i));
		
		// 新增訂單時候不創建orderid 而是留給MySQL自動生成order id，而OrderItem使用FK方式參照 
		
		order.setItems(orderItemSet);
		System.out.println(order);
		
		
		
		
	}
}
