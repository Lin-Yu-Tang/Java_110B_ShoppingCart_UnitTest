package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.example.dao.ProductServiceImpl;
import com.example.model.Product;

/**
 * 
 * @author Tom Lin
 * @apiNote 取得新增商品表單資料，寫入資料庫
 */
@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("表單已送出");
		
		// true if request with multipart file
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// Parse the request
		try {
			List<FileItem> items = upload.parseRequest(request);
			
			String name = items.get(0).getString("UTF-8");
			String price = items.get(1).getString();
			String quantity = items.get(2).getString();
			InputStream pictureIs = items.get(3).getInputStream();
			String desc = items.get(4).getString("UTF-8");
			
			// pass data to model
			Product tempProduct = new Product();
			
			/**
			 * 賣家id預計由session確認身分後，由SellerService取得賣家資訊傳入賣家id
			 * 目前先設定為1
			 */
			tempProduct.setSeller_id("1");
			tempProduct.setName(name);
			tempProduct.setDescription(desc);
			tempProduct.setPrice(Integer.parseInt(price));
			tempProduct.setQuantity(Integer.parseInt(quantity));
			byte[] bytes = IOUtils.toByteArray(pictureIs);
			try {
				tempProduct.setPicture(new SerialBlob(bytes));
			} catch (SerialException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			ProductServiceImpl productService = new ProductServiceImpl();
			
			productService.saveProduct(tempProduct);
		
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
