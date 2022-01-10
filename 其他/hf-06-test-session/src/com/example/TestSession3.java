package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author a0972
 * @apiNote 測試session.invalidate
 */
@WebServlet("/TestSesion3")
public class TestSession3 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		session.setAttribute("foo", "42");
		session.setAttribute("bar", "420");
		// invalidate the session
		session.invalidate();
		
		// getAttribute 回傳object物件
		String foo = (String) session.getAttribute("foo");
		out.print("Foo: " + foo);
		
		
		
		// result: java.lang.IllegalStateException: 
		// getAttribute: Session already invalidated
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
