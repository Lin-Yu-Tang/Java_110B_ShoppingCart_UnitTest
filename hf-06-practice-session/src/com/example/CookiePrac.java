package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookiePrac")
public class CookiePrac extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		String username = request.getParameter("username");
		String userpass = request.getParameter("userpass");
		
		if (username != null) {
			out.print("login");
			Cookie uidCookie = new Cookie("uid", username);
			uidCookie.setMaxAge(300);
			response.addCookie(uidCookie);
		}
		
	
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (Cookie c:cookies) {
				
				out.print(c.getName() +" : " +c.getValue());
				if ("uid".equals(c.getName())) {
					out.print("歡迎回來");
				}
			}
		}
		
		
	
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
