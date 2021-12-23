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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		// 取得登入/登出當前URL
		String currentURL = request.getParameter("loginCurrentUrl");
		String[] split = currentURL.split("/");
		String rebuiltURL = "";
		for(int i=3;i<split.length;i++) {
			rebuiltURL +="/"+ split[i];
		}
		System.out.println("URL: " + rebuiltURL);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User();

		user.setUsername(username);
		user.setPassword(password);
		UserServiceImpl userDao = new UserServiceImpl();

		String login = request.getParameter("login");
		System.out.println("[action] user is login: " + login);

		HttpSession session = request.getSession();
		// 使用者登入時候重設session時效
		session.setMaxInactiveInterval(7 * 24 * 60 * 60);

		if (login.equals("true") && userDao.LoginUser(username, password)) {
			
			session.setAttribute("username", username);
			
		}else if (login.equals("false")) {
			
			session.setAttribute("username", null);
		}
		
		// 登入程序結束返回當前頁面
		response.sendRedirect(rebuiltURL);

	}
}
