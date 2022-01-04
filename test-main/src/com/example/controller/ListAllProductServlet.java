package com.example.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ProductServiceImpl;
import com.example.model.Product;

/**
 * 
 * @author Tom Lin
 * @apiNote 列出所有商品內容
 */
@WebServlet("/listAllProductServlet")
public class ListAllProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductServiceImpl productService = new ProductServiceImpl();
		ArrayList<Product> listAllProduct = productService.listAllProduct();
		response.setContentType("text/html;charset=UTF-8");
		
		request.setAttribute("allProducts", listAllProduct);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("readPage");
		
		dispatcher.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
