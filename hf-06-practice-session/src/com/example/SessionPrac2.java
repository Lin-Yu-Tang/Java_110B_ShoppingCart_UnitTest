package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionPrac2")
public class SessionPrac2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		String userpass = request.getParameter("userpass");
		
		System.out.println(username+", "+userpass);
		String page;
		HttpSession session = request.getSession();
		if ("root".equalsIgnoreCase(username) && "1234".equals(userpass)) {
			if (request.getSession(false) != null) {
				request.changeSessionId();
			}
			
			session.setAttribute("login", username);
			session.setMaxInactiveInterval(60 * 60 * 24);
			
			page = "successful.jsp";
		}
		else {
			page = "first-page.html";
			
			
		}
		request.getRequestDispatcher(page).forward(request, response);
		
	}

}
