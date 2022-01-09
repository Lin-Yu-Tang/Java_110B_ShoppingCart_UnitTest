package com.example.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ProductServiceImpl;

/**
 * @author Tom Lin
 * @apiNote 接收刪除商品請求
 */
@WebServlet("/seller/delete")
public class SellerProductDeleteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String PID = request.getParameter("pid");
		
//		System.out.println("delete product, id = " + PID);
		
		ProductServiceImpl service = new ProductServiceImpl();
		service.deleteProdct(PID);
		
		response.sendRedirect(getServletContext().getContextPath() + "/seller");
		
	}

}
