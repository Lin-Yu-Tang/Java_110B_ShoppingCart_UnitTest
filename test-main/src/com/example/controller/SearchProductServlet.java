package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ProductServiceImpl;
import com.example.model.Product;

/**
 * @author Tom Lin
 * @apiNote deal with the keywords user submit from the navbar search component in pages. 
 * 		reutrn the result contains products.
 */
@WebServlet("/search")
public class SearchProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String keyword = request.getParameter("keywords");
		ProductServiceImpl service = new ProductServiceImpl();
		
		String[] keywords = keyword.split(" ");
//		System.out.println("keywords len: " + keywords.length);
		String keywordResult = "";
		
		for (String s:keywords) {
			System.out.println("keywords: " + s);
			keywordResult += s + " ";
		}
		
		// data access
		ArrayList<Product> products = service.searchProduct(keywords);
		
		// result print in console
		products.forEach((product) -> System.out.println(product));
		
		
		// set attribute
		request.setAttribute("allProducts", products);
		request.setAttribute("keywordResultList", keywordResult);
		request.setAttribute("resultNum", products.size());
		RequestDispatcher dispatcher = request.getRequestDispatcher("searchPage");
		
		dispatcher.forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
