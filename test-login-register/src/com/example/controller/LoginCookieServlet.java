package com.example.controller;

import java.io.IOException;
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
 * Servlet implementation class LoginCookieServlet
 */
@WebServlet("/LoginCookieServlet")
public class LoginCookieServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		
//		String password = request.getParameter("password");
//		UserServiceImpl userDao = new UserServiceImpl();
//		String dbusername = userDao.selectusername(username).toString();// 抓資料庫username
//		String dbuserpassword = userDao.selectusername(password).toString();	
		
		
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies(); 

		
		if (cookies != null) {
			for (Cookie cookie : cookies) {

				if (cookie.getName() != null && cookie.getValue() != null) {
					String name = cookie.getName();
					String value = cookie.getValue();
					String autokey111 = "autokey111";  //cookie 名稱
					
					if (autokey111.equals(name) && ("歡迎").equals(value)) { //若放username 取不到值 
						session.setAttribute("username", value);
						request.getRequestDispatcher("homePage.jsp").forward(request, response);
						return;
					}

				}
			}
		}
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
