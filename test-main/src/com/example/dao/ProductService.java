package com.example.dao;

import java.util.ArrayList;

import com.example.model.Product;

public interface ProductService {
	
	public void saveProduct(Product product);
	
	public ArrayList<Product> ListAllProduct();
}
