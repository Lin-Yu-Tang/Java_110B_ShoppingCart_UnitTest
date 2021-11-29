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


@WebServlet("/updateProductServlet")
public class UpdateProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("表單已送出");
		
		ProductServiceImpl service = new ProductServiceImpl();
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
			request.getParameter("picture");
			if (items.get(2).getString("UTF-8").equals("")) {
				// 沒有更新圖片
				String name = items.get(0).getString("UTF-8");
				String price = items.get(1).getString();
				String desc = items.get(3).getString("UTF-8");
				String id = items.get(4).getString("UTF-8");
//				System.out.println("取得編輯後檔案:");
//				System.out.println("id:" + id);
//				System.out.println("name: " + name);
//				System.out.println("price: " + price);
//				System.out.println("desc: " + desc);
				
				Product tempProduct = service.selectOneProduct(id);
				tempProduct.setName(name);
				tempProduct.setPrice(Integer.parseInt(price));
				tempProduct.setDescription(desc);
				service.updateProduct(tempProduct);
				
			} else {
				// 有更新圖片
				String name = items.get(0).getString("UTF-8");
				String price = items.get(1).getString();
				InputStream pictureIs = items.get(2).getInputStream();
				String desc = items.get(3).getString("UTF-8");
				String id = items.get(4).getString("UTF-8");
//				System.out.println("取得編輯後檔案:(有圖檔)");
//				System.out.println("id:" + id);
//				System.out.println("name: " + name);
//				System.out.println("price: " + price);
//				System.out.println("pictureIs" + pictureIs);
//				System.out.println("desc: " + desc);
				
				Product tempProduct = service.selectOneProduct(id);
				tempProduct.setName(name);
				tempProduct.setPrice(Integer.parseInt(price));
				tempProduct.setDescription(desc);
				byte[] bytes = IOUtils.toByteArray(pictureIs);
				try {
					tempProduct.setPicture(new SerialBlob(bytes));
				} catch (SerialException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				service.updateProduct(tempProduct);
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
	}

}
