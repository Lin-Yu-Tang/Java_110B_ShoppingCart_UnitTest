package com.example.test;

import com.example.dao.ProductServiceImpl;
import com.example.model.Product;

public class TestProductServiceDelete {

	public static void main(String[] args) {
		
		ProductServiceImpl service = new ProductServiceImpl();
		
		
		
		Product product = new Product();
		product.setName("TestProduct");
		product.setPrice(66666);
		product.setDescription("this is a test!!");
//		System.out.println("insert product: " + product);
//		service.saveProduct(product);
		
		Product selectedProduct = service.selectOneProduct("4");
		System.out.println(selectedProduct);
		
		service.deleteProdct(selectedProduct.getId());
		
		
	}

}
