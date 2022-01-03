package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.SellerServiceImpl;
import com.example.model.SellerOrder;

/**
 * Servlet implementation class SellerOrderManageServlet
 */
@WebServlet("/seller/order")
public class SellerOrderManageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		SellerServiceImpl service = new SellerServiceImpl();
		System.out.println("seller/order page");
		String SID = (String) session.getAttribute("sellername");
		
		System.out.println("SID: " + SID);
		
		if (SID != null) {
			String sellerId = service.getSellerId(SID);
			
			System.out.println("SellerId: " + sellerId);
			
			ArrayList<SellerOrder> allSellerOrders = service.getAllSellerOrders(sellerId);
			
			System.out.println(allSellerOrders);
			
			request.setAttribute("sellerOrders", allSellerOrders);
			
			request.getRequestDispatcher("orderPage").forward(request, response);
			
		}else {
			response.sendRedirect("/test-main/sellerLogin");
		}
		
	}
}
