package com.example.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.SellerServiceImpl;
import com.example.service.SellerLoginVerifyService;

/**
 * @author Tom Lin
 * @apiNote doGet: 取得直接訪問賣家登入頁面的請求；doPost: 登入表單post mapping請求
 */
@WebServlet("/sellerLogin")
public class SellerLoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// if session is null ? sellerLogin : sellerManagemnet page
		
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Expires", "0");
		
		System.out.println("=========sellerLogin GET==========");
		HttpSession session = request.getSession();
		SellerServiceImpl service = new SellerServiceImpl();
		Cookie[] cookies = request.getCookies();
		
		String sid = (String) session.getAttribute("SID");
		
		if (cookies != null) {
			System.out.println("cookie:");
			for (Cookie c : cookies) {
				System.out.println("name: " + c.getName() + " ,value: " + c.getValue());
				if (c.getName().equals("AUSID")) {
					// seller have auto login cookie
					System.out.println("Cookie exists: " + c.getName() + ": " + c.getValue());
					session.setAttribute("SID", c.getValue());
					session.setAttribute("sellername", c.getValue());
					session.setMaxInactiveInterval(7 * 24 * 60 * 60);
					
					sid = (String) session.getAttribute("SID");
				}
			}
		}
		
		
		
		
		if (sid != null) {
			// imply session.getAttribute("SID") exists
			response.sendRedirect("seller");
		}
		else {
			request.getRequestDispatcher("sellerLoginPage").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		SellerLoginVerifyService service = new SellerLoginVerifyService();
		String action = request.getParameter("login");
		String autoLogin = request.getParameter("autologin");
		System.out.println("=========sellerLogin POST==========");

		// 登入程序
		if (action.equals("true")) {
			String sellerAccount = request.getParameter("sellerAccount").toLowerCase();
			String sellerPasswd = request.getParameter("passwd");

			if (!service.loginCheck(sellerAccount, sellerPasswd)) {
				response.sendRedirect("sellerLogin");
			} else {
				// credential
				System.out.println("Seller " + sellerAccount + " login");
				session.setAttribute("SID", sellerAccount);
				session.setAttribute("sellername", sellerAccount);
				session.setMaxInactiveInterval(7 * 24 * 60 * 60);

				if ("true".equals(autoLogin)) {
					Cookie cookie = new Cookie("AUSID", sellerAccount);
					cookie.setMaxAge(180 * 24 * 60 * 60);
					response.addCookie(cookie);
				}

				response.sendRedirect("seller");
			}

			// 登出程序
		} else if (action.equals("false")) {
			System.out.println("Seller " + session.getAttribute("SID") + "logout");

			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals("JSESSIONID")) {
					c.setMaxAge(0);
					response.addCookie(c);
					System.out.println(c.getName() + "adjust cookie age: " + c.getMaxAge());
				}
				if (c.getName().equals("AUSID")) {
					c.setMaxAge(0);
					response.addCookie(c);
					System.out.println(c.getName() + "adjust cookie age: " + c.getMaxAge());

				}

			}
			session.removeAttribute("SID");
			session.removeAttribute("sellername");
//			response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); // HTTP 1.1
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Expires", "0");
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0

			response.sendRedirect("sellerLogin");
		}

	}
}
