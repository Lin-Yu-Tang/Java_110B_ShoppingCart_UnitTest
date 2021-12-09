package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ProductServiceImpl;

/**
 * 
 * @author Tom Lin
 * @apiNote deal with product delete
 */
@WebServlet("/deleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductServiceImpl service = new ProductServiceImpl();
		request.setCharacterEncoding("UTF-8");
		String pnum = request.getParameter("pnum");
		System.out.println("deleteProductServlet!! ID = " + pnum);
		service.deleteProdct(pnum);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
