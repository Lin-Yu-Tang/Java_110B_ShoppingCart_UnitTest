package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Iterator;
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

@WebServlet("/productServlet")
public class ProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("表單已送出");
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		System.out.println("is multipart? " + isMultipart);
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
			
			System.out.println(items.size());
			
			String name = items.get(0).getString();
			String price = items.get(1).getString();
			InputStream pictureIs = items.get(2).getInputStream();
			String desc = items.get(3).getString();
			
			// pass data to model
			Product tempProduct = new Product();
			
			tempProduct.setName(name);
			tempProduct.setDescription(desc);
			tempProduct.setPrice(Integer.valueOf(price));
			byte[] bytes = IOUtils.toByteArray(pictureIs);
			System.out.println("picture bytes length: " + bytes.length);
			try {
				tempProduct.setPicture(new SerialBlob(bytes));
			} catch (SerialException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			tempProduct.setDescription(desc);
			
			ProductServiceImpl productService = new ProductServiceImpl();
			
			productService.saveProduct(tempProduct);
			
			
			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
			    FileItem item = iter.next();
			    
			    if (item.isFormField()) {
			        String names = item.getFieldName();
			        String values = item.getString();
			        System.out.println("name: " + names +", value: " +values);
			    }else {
			    	String fieldName = item.getFieldName();
			        String fileName = item.getName();
			        String contentType = item.getContentType();
			        System.out.println("fieldName: " + fieldName
			        		+ ", fileName: " + fileName
			        		+ ", type: " + contentType);
			    }
			}
		
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
