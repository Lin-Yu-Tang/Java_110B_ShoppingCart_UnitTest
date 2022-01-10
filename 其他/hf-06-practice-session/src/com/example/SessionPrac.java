package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 測試session本質
 */
@WebServlet("/sessionPrac")
public class SessionPrac extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Cookie newCookie = new Cookie("username", "helloworld");
		response.addCookie(newCookie);
		Cookie newCookie2 = new Cookie("gift", "the-end-of-party!!");
		response.addCookie(newCookie2);
		
		// If argument is false 
		// and the request has no valid HttpSession, 
		// this method returns null.
		HttpSession session = request.getSession(false);
		// session.getAttribute() 必須是session.setAttr建立的物件
//		Object usr = session.getAttribute("username");
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			int len = cookies.length;
			out.print("cookie項目數量: " + len + "<br>");
			
			
			for (Cookie c:cookies) {
				out.print("<h1>cookies info:</h1><div>" );
				out.print(c.getName() + ": " + c.getValue());
				out.print("<br>");
				out.print("age: " + c.getMaxAge());
				out.print("<br>");
				c.setMaxAge(300);
				out.print("age: " + c.getMaxAge());
				out.print("<br>");
				out.print("domain: " + c.getDomain());
				out.print("<br>");
				out.print("path: " + c.getPath());
				out.print("<br>");
				out.print("version: " + c.getVersion());
				out.print("<br>");
				out.print("secure: " + c.getSecure());
				out.print("<br>");
				out.print("comment: " + c.getComment());
				
				out.print("<br>");
				out.print("</div><br>");
			}
			
		}
		
		Cookie newCookie4 = new Cookie("bye", "see-u-latter");
		response.addCookie(newCookie4);
		
		if (session == null) {
			// 當使用者關閉瀏覽器重新進來此畫面session都是新的
			out.print("初次見面，歡迎!!!");
			out.print("<br>");
			out.print("no session available");
			out.print("<br>");
			out.print("making one ...");
			out.print("<br>");
			session = request.getSession();
			int sessionLife = session.getMaxInactiveInterval();
			// session預設存續期間是1800秒
			out.print("default session life: " + sessionLife);
			out.print("<br>");
			session.setMaxInactiveInterval(1);
			
			int sessionLife2 = session.getMaxInactiveInterval();
			out.print("life after setting: " + sessionLife2);
			out.print("<br>");
			out.print(session.getId());
			out.print("<br>");
			
		}
		else {
			out.print("好久不見，歡迎回來~~~");
			out.print("<br>");
			out.print(session.getId());
			out.print("<br>");
			request.changeSessionId();
			out.print("on change: " + session.getId());
			out.print("<br>");
		}
		
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
