package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.LoginDao;
import com.dao.UserDao;
import com.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	String username=request.getParameter("username");
	String password=request.getParameter("password");
	try {
		if (LoginDao.checkLogin(username, password)) {      //dao層中判斷,如果為true,跳轉到歡迎介面
			 request.setAttribute("username", username);
			 request.getRequestDispatcher("loginsuccess.jsp").forward(request, response);
			}else{                                        //如果為false,跳轉到登入介面,並返回錯誤資訊.
				request.setAttribute("inf", "你的帳號或密碼錯誤,請重新登入");
				request.getRequestDispatcher("fail.jsp").forward(request, response);
			}
	} catch (ClassNotFoundException | ServletException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	
}
}
