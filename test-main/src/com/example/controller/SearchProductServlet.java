package com.example.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tom Lin
 * @apiNote deal with the keywords user submit the navbar search component in pages. 
 * 		reutrn the result contains products.
 */
@WebServlet("/searchProducts")
public class SearchProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String keyword = request.getParameter("keywords");
		
		String[] keywords = keyword.split(" ");
		System.out.println("keywords len: " + keywords.length);
		
		for (String s:keywords) {
			System.out.println("keywords: " + s);
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
