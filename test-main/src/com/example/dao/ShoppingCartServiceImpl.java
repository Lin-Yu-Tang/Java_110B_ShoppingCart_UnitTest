package com.example.dao;

import java.util.ArrayList;

import com.example.model.Product;
import com.example.model.ShoppingCart;

/**
 * 
 * @author Tom Lin
 * @apiNote when customer add products to his shopping cart, invoke addProductToCart()
 */
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Override
	public ShoppingCart addProductToCart(ShoppingCart cart, String productId, int quantity) {
		ProductServiceImpl service = new ProductServiceImpl();
		int amount = 0;
		if (cart.getProducts() == null) {
			// init cart & new items
			ArrayList<Product> products = new ArrayList<Product>();
			Product product = service.selectOneProduct(productId);
			product.setQuantity(quantity);
			products.add(product);
			
			// calculate total amount
			for(Product p:products) {
				amount += p.getPrice() * p.getQuantity();
			}
			cart.setProducts(products);
			cart.setTotalAmount(amount);
		}else {
			// update items
			ArrayList<Product> products = cart.getProducts();
			int index = -1;
			A: for (int i=0; i<products.size(); i++) {
				// check if have a duplicate item
				if (products.get(i).getId().equals(productId)) {
					index = i;
					break A;
				}
			}
			
			if (index != -1) {
				// update duplicate item
				products.get(index)
				.setQuantity(products.get(index).getQuantity() + quantity);
			}else {
				Product product = service.selectOneProduct(productId);
				product.setQuantity(quantity);
				products.add(product);
			}
			
			// calculate total amount
			for(Product p:products) {
				amount += p.getPrice() * p.getQuantity();
			}
			cart.setTotalAmount(amount);
			
		}
		return cart;
	}

	@Override
	public ShoppingCart removeProductFromCart(ShoppingCart cart, String productId) {
		int amount = 0;
		// prevent null point exception
		if (cart.getProducts() != null) {
		ArrayList<Product> products = cart.getProducts();
		int index = -1;
		A: for (int i=0; i<products.size(); i++) {
			// find the product user want to remove
			if (products.get(i).getId().equals(productId)) {
				index = i;
				break A;
			}
		}
		// prevent indxOOBException
		if (!products.isEmpty() && index != -1) {
			products.remove(index);
			
			// calculate total amount
			for(Product p:products) {
				amount += p.getPrice() * p.getQuantity();
			}
			cart.setProducts(products);
			cart.setTotalAmount(amount);
		}
		}
		return cart;
	}

}
