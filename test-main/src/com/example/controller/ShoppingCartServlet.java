package com.example.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.ShoppingCartService;
import com.example.dao.ShoppingCartServiceImpl;
import com.example.model.ShoppingCart;

/**
 * @author Tom Lin
 * @apiNote 接收來自productInfo.jsp 使用者的行為(add/remove/checkout)，新增/刪除商品至使用者的購物車
 */
@WebServlet("/shoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		ShoppingCartServiceImpl service = new ShoppingCartServiceImpl();
		HttpSession session = request.getSession(false);
		if (session == null) {
			
			System.out.println("new session~~");
			session = request.getSession();
			session.setMaxInactiveInterval(7 * 24 * 60 * 60);
		}
		// get parameter from user add product to shopping cart or checkout
		String q = request.getParameter("buy-amount");
		String pid = request.getParameter("pid");
		String action = request.getParameter("action"); // add or remove
		
		
		System.out.printf("ID: %s%nQ: %s%nACTION: %s%n",pid,q,action);
		
		
		ShoppingCart cart = null;
		
		// retrieve or new cart
		if (session.getAttribute("shoppingcart") == null) {
			System.out.println("new cart!!");
			cart = new ShoppingCart();
		} else {
			cart = (ShoppingCart) session.getAttribute("shoppingcart");
		}

		// shopping cart management
		if (action.equals("add")) {
			// deal with add action
			cart = service.addProductToCart(cart, pid, Integer.parseInt(q));
			cart.cartInfo();
		}else if (action.equals("remove")) {
			// deal with remove action
		}
		
		
		
		// finally, add cart object to session
		session.setAttribute("shoppingcart", cart);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("shoppingCart.jsp");
		
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
