package com.example.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.UserServiceImpl;
import com.example.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	String username=request.getParameter("username");
	String password=request.getParameter("password");
	User user=new User();
	
	user.setUsername(username);
	user.setPassword(password);
	UserServiceImpl userDao=new UserServiceImpl();
	
	
		if (userDao.LoginUser(username, password)) {      //dao層中判斷,如果為true,跳轉到歡迎介面
			request.setAttribute("username", username);
			
			 request.getRequestDispatcher("loginsuccess.jsp").forward(request, response);
			}else{                                        //如果為false,跳轉到登入介面,並返回錯誤資訊.
				
				request.getRequestDispatcher("fail.jsp").forward(request, response);
			}



	
}
}
