package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.*;
import com.example.model.*;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		User user = new User();
		user.setUsername(username);
		UserServiceImpl userDao = new UserServiceImpl();
		
		if (userDao.userIsCheck(username)) {				//ajax 的回應用GET

			response.getWriter().write("帳戶已重複");

		} else {
			userDao.RegisterUser(user);
			response.getWriter().write("帳戶無人使用");
		}

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
		String email = request.getParameter("email");
		String phone_number = request.getParameter("phone_number");
		String address = request.getParameter("address");
		String picture = request.getParameter("picture");

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone_number(phone_number);
		user.setAddress(address);
		user.setPicture(picture);
		UserServiceImpl userDao = new UserServiceImpl();

		if (userDao.userIsCheck(username)) {

			response.getWriter().write("帳戶已重複");
			response.sendRedirect("registerfail.jsp");
		} else {
			userDao.RegisterUser(user);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("registersuccess.jsp");
			dispatcher.forward(request, response);
//			response.sendRedirect("registersuccess.jsp");
		}

	}

}
