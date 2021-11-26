package com.example.test;

import com.example.dao.ProductServiceImpl;
import com.example.model.Product;

/**
 * 
 * @author Tom Lin
 * @apiNote 測試ProductServiceImpl.selectOneProduct()與.updateProduct()
 *
 */
public class TestProductServiceSelectAndUpate {

	public static void main(String[] args) {
		
		ProductServiceImpl service = new ProductServiceImpl();
		
		String id = "1";
		Product tempProduct = service.selectOneProduct(id);
		
		System.out.println(tempProduct);
		
		
		// test update 
		tempProduct.setName("黃金蘋果");
		tempProduct.setPrice(888);
		tempProduct.setDescription("好吃的黃金蘋果");
		
		service.updateProduct(tempProduct);
		
		System.out.println("更新後:");
		System.out.println(service.selectOneProduct(id));
		
	}

}
