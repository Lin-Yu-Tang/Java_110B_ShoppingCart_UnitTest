package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Tom Lin
 * @apiNote this is a test servlet provide root login without validation
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String login = request.getParameter("login");
		System.out.println("[action] user is login: " + login);
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(7 * 24 * 60 * 60);
		
		
		if (login.equals("true")) {
			
			String username = "root";
			session.setAttribute("username", username);
			
		}else if (login.equals("false")) {
			
			session.setAttribute("username", null);
		}
		
		
		response.sendRedirect("/test-main/");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
