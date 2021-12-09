package com.example;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/shoppingCart")
public class ShoppingCartServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		ShoppingCart cart = new ShoppingCart();
		cart.setAmount(1000);
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(7 * 24 * 60 * 60);
		
		// setAttribute 屬性(不論是從session或request設定)不能用 - 符號，否則jsp無法取得參數
		session.setAttribute("shoppingcart", cart);
		
		ShoppingCart o = (ShoppingCart) session.getAttribute("shoppingcart");
		
		System.out.println(o.getAmount());
		request.setAttribute("user-cart", o); // 無法取得參數
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("shoppingCart.jsp");
	
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
