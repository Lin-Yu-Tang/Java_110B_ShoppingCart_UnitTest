package com.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Cookie[] cookies = request.getCookies();
		
		if (cookies == null) {
			System.out.println("no cookie");
			out.print("您好，初次見面");
		}else {
			
		
		String username = null;
		for (Cookie c:cookies) {
			if (c.getName().equals("UID")) {
				username = c.getValue();
			}
		}
		
		if (username != null) {
			out.print("好久不見~~~~ " + username);
		}
		else {
			System.out.println("no cookie (i.e. no auto login)");
			out.print("您好，初次見面");
		}
		
		}
		
		
		
	}

	
}
