package com.example.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ProductServiceImpl;
import com.example.model.Product;

/**
 * 
 * @author Tom Lin
 * @apiNote forward to editProduct.jsp
 */
@WebServlet("/editProductServlet")
public class EditProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("pnum");
		ProductServiceImpl service = new ProductServiceImpl();
		
		Product etidingProduct = service.selectOneProduct(id);
		request.setAttribute("product", etidingProduct);
		RequestDispatcher dispatcher = request.getRequestDispatcher("editProduct.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
