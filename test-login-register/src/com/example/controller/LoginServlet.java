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
		User user = new User();

		user.setUsername(username);
		user.setPassword(password);
		UserServiceImpl userDao = new UserServiceImpl();

		String login = request.getParameter("login");
		System.out.println("[action] user is login: " + login);

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(7 * 24 * 60 * 60);

		if (userDao.LoginUser(username, password)) { // dao層中判斷,如果為true,跳轉到首頁
			session.setAttribute("username", username);
			response.sendRedirect("homePage.jsp");
		} else { 
			session.setAttribute("username", null);	
			response.sendRedirect("fail.jsp");
		}
		
		

	}
}
