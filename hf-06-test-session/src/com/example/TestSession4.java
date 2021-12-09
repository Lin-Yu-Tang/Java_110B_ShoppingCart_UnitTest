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
 * @apiNote 測試session.setMaxInactiveInterval(0) (never timeout)
 */
@WebServlet("/TestSession4")
public class TestSession4 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		session.setAttribute("foo", "42");
		session.setMaxInactiveInterval(1);
		String foo = (String) session.getAttribute("foo");
		int time = session.getMaxInactiveInterval();
		if (session.isNew()) {
			out.print("This is a new session");
			out.print("<p>Session life: " + time + "</p>");
		}
		else {
			out.print("Welcome back");
			out.println("!!");
			out.print("this is a session test!!");
			out.print("<p>Session life: " + time + "</p>");
		}
		
		out.print("Foo: " + foo);
	
	
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
