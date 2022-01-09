package com.example.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.model.Product;

/**
 * @author Tom Lin
 * @apiNote 定義商品管理/查詢相關方法
 */
public interface ProductService {
	
	public void saveProduct(Product product);
	
	public ArrayList<Product> listAllProduct();
	
	public Product selectOneProduct(String id);
	
	public void updateProduct(Product product);
	
	public void deleteProdct(String id);
	
	public ArrayList<Product> searchProduct(String[] strs);
	
	public HashMap<Integer, Integer> getCurrentStorage(ArrayList<Product> products);
	
	public ArrayList<Product> getAllProductsBySellerId(String sellerId);
}
