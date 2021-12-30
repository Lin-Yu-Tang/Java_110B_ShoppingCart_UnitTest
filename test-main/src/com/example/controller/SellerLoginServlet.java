package com.example.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.service.SellerLoginVerifyService;

/**
 * Servlet implementation class SellerLoginServlet
 */
@WebServlet("/sellerLoginServlet")
public class SellerLoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("sellerLogin");
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		SellerLoginVerifyService service = new SellerLoginVerifyService();
		String sellerAccount = request.getParameter("sellerAccount");
		String sellerPasswd = request.getParameter("passwd");
		String action = request.getParameter("login");
		
		// 登入程序
		if (action.equals("true")) {
			if (!service.loginCheck(sellerAccount, sellerPasswd)) {
				response.sendRedirect("sellerLogin");
			}else {
				// login successful
				System.out.println("Seller " + sellerAccount + " login");
				session.setAttribute("SID", sellerAccount);
				session.setAttribute("sellername", sellerAccount);
				session.setMaxInactiveInterval(60 * 60 * 24 * 7);
				response.sendRedirect("seller");
			}
			
			
		// 登出程序
		}else if (action.equals("false")) {
			System.out.println("Seller " + session.getAttribute("SID") + "logout");
			
			Cookie[] cookies = request.getCookies();
			for (Cookie c:cookies) {
				if (c.getName().equals("JSESSIONID")) {
					c.setMaxAge(0);
				}
				
			}
			session.removeAttribute("UID");
			session.invalidate();
//			response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); // HTTP 1.1
		    response.setHeader("Cache-Control", "no-store");
		    response.setHeader("Expires", "0");
//		    response.setDateHeader("Expires", -1);
		    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
			
			response.sendRedirect("sellerLogin");
		}
		
		
		
		
		
		
	}
}
