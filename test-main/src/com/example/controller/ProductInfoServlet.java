package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ProductServiceImpl;
import com.example.model.Product;

/**
 * @author Tom Lin
 * @apiNote render a product & product information
 */
@WebServlet("/showProductInfo")
public class ProductInfoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		ProductServiceImpl service = new ProductServiceImpl();
		String theId = request.getParameter("pnum");
		Product theProduct = service.selectOneProduct(theId);
		int quantity_length = (""+theProduct.getQuantity()).length();
		request.setAttribute("product_quantity_len", quantity_length);
		request.setAttribute("product", theProduct);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("product_info");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
