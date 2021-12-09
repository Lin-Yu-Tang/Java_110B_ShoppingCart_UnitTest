package com.example.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cookie.do")
public class TestCookieResp extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (int i = 0; i<cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("username")) {
					String userName = cookie.getValue();
					out.print("Hello " + userName);
					break;
				}
				else {
					String theName = cookie.getName();
					String theValue = cookie.getValue();
					
					out.print("<p>"+theName+": "+theValue+"</p>");
				}
			}
		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
