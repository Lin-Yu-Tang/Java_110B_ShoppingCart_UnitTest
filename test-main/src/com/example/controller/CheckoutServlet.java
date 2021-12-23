package com.example.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.CheckoutServiceImpl;
import com.example.dao.UserServiceImpl;
import com.example.model.ShoppingCart;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/checkoutServlet")
public class CheckoutServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		UserServiceImpl uServ = new UserServiceImpl();
		CheckoutServiceImpl checkoutService = new CheckoutServiceImpl();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingcart");
		
		// prepare parameters requirement
		System.out.println("USER NAME: " + username);
		String userId = uServ.getUserId(username);
		System.out.println("UserID: " + userId);
		System.out.println(cart);
		
		// 結帳 傳入 String userId, String shippingAddress, ShoppingCart cart
		// 寫入買家table(orders, order_item)、賣家table(product,)
		boolean checkoutResult = checkoutService.checkout(userId, "楊新路一段1231230", cart);

		// 結帳成功 ? 清空session:返回結帳頁面
		
		if (checkoutResult == true) {
			session.setAttribute("shoppingcart", null);
			response.sendRedirect("/test-main");
		}else {
			System.out.println("結帳發生錯誤!!");
			response.sendRedirect("shoppingCartServlet");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
