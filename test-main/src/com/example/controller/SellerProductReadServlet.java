package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.ProductServiceImpl;
import com.example.dao.SellerServiceImpl;
import com.example.model.Product;

/**
 * @author Tom Lin
 * @apiNote 導向我的商品頁面
 */
@WebServlet("/seller/read")
public class SellerProductReadServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		ProductServiceImpl productService = new ProductServiceImpl();
		SellerServiceImpl sellerService = new SellerServiceImpl();
		
		HttpSession session = request.getSession();
		
		String sellerId = sellerService.getSellerId((String) session.getAttribute("sellername"));
		
		ArrayList<Product> products = productService.getAllProductsBySellerId(sellerId);
		
		request.setAttribute("allProducts", products);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("readPage");
		
		dispatcher.forward(request, response);
	}

}
