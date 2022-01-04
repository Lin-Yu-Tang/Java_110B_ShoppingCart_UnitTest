package com.example.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ProductServiceImpl;
import com.example.model.Product;

/**
 * @author Tom Lin
 * @apiNote 導向商品更新頁面
 */
@WebServlet("/seller/update")
public class SellerProductUpdateServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String pid = request.getParameter("PID");
		
		System.out.println("product update: " + pid);
		
		// TODO: 將商品資料導向編輯商品頁面
		ProductServiceImpl service = new ProductServiceImpl();
		Product product = service.selectOneProduct(pid);
		
		request.setAttribute("theProduct", product);
		
		
		request.getRequestDispatcher("updatePage").forward(request, response);;
		
		
	}
}
