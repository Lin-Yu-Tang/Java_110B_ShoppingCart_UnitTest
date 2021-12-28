package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.UserServiceImpl;

/**
 * Servlet implementation class CookieCheckServlet
 */
@WebServlet("/TestCheckServlet")
public class TestCheckServlet extends HttpServlet {   //目前沒轉向到這

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		if (request.getAttribute("username") == null) {
			response.sendRedirect("LoginCookieServlet");
		} else {
			response.sendRedirect("homePage.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	}

}
