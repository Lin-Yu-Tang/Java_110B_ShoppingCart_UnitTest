package com.example.dao;

import java.util.ArrayList;

import com.example.model.Product;

public interface ProductService {
	
	public void saveProduct(Product product);
	
	public ArrayList<Product> listAllProduct();
	
	public Product selectOneProduct(String id);
	
	public void updateProduct(Product product);
	
	public void deleteProdct(String id);
	
	public ArrayList<Product> searchProduct(String[] strs);
}
