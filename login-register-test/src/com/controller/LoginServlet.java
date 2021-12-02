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
		if (LoginDao.checkLogin(username, password)) {      //dao�h���P�_,�p�G��true,������w�虜��
			 request.setAttribute("username", username);
			 request.getRequestDispatcher("loginsuccess.jsp").forward(request, response);
			}else{                                        //�p�G��false,�����n�J����,�ê�^���~��T.
				request.setAttribute("inf", "�A���b���αK�X���~,�Э��s�n�J");
				request.getRequestDispatcher("fail.jsp").forward(request, response);
			}
	} catch (ClassNotFoundException | ServletException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	
}
}
