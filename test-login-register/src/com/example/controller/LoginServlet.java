package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.UserServiceImpl;
import com.example.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String username = request.getParameter("username");

		String password = request.getParameter("password");
	
		
		UserServiceImpl userDao = new UserServiceImpl();

	

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(7 * 24 * 60 * 60);

		String autokey111 = "autokey111"; // cookie名稱

		if (userDao.LoginUser(username, password)) { // dao層中判斷,如果為true,跳轉到首頁

			String autologin = request.getParameter("autologin");

			if ("auto".equals(autologin)) { // 判斷有沒有勾選自動登入

				Cookie cookie = new Cookie(autokey111, "歡迎"); // new cookie
				cookie.setMaxAge(60);
				response.addCookie(cookie);
		//		request.getRequestDispatcher("LoginCookieServlet").forward(request, response);
				response.sendRedirect("LoginCookieServlet"); //轉向判斷cookie是否存在
			} else {
				response.sendRedirect("homePage.jsp");
			}

			session.setAttribute("username", username);

			// request.getRequestDispatcher("CookieCheckServlet").forward(request,
			// response);
		} else {
			session.setAttribute("username", null);
			response.sendRedirect("fail.jsp");
		}

	}
}
