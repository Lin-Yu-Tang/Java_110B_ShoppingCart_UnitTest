package com.example.cookie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestCookie")
public class TestCookie extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		String name = request.getParameter("username");
		
		Cookie cookie = new Cookie("username", name);
		
		cookie.setMaxAge(2);
		
		response.addCookie(cookie);
		
//		RequestDispatcher view = request.getRequestDispatcher("cookieresult.jsp");
		RequestDispatcher view = request.getRequestDispatcher("/cookie.do");
		
		
		view.forward(request, response);
		
	
	
	
	
	
	
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
