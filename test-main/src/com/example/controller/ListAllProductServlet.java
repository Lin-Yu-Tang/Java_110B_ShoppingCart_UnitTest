package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ProductServiceImpl;
import com.example.model.Product;

/**
 * Servlet implementation class ListAllProductServlet
 */
@WebServlet("/ListAllProductServlet")
public class ListAllProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductServiceImpl productService = new ProductServiceImpl();
		
		ArrayList<Product> listAllProduct = productService.ListAllProduct();
		
		for (Product p:listAllProduct) {
			System.out.println(p);
		}
		
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
