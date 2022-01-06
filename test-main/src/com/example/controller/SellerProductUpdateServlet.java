package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
 * @author Tom Lin
 * @apiNote 導向商品更新頁面
 */
@WebServlet("/seller/update")
public class SellerProductUpdateServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String pid = request.getParameter("PID");
		
//		System.out.println("product update: " + pid);
		
		// TODO: 將商品資料導向編輯商品頁面
		if (pid != null) {
		ProductServiceImpl service = new ProductServiceImpl();
		Product product = service.selectOneProduct(pid);
		request.setAttribute("theProduct", product);
		}
		
		
		request.getRequestDispatcher("updatePage").forward(request, response);
		
		
	}
	
	/**
	 * 更新商品表單接收
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("seller update");
		
		
		ProductServiceImpl service = new ProductServiceImpl();
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// Parse the request
		InputStream pictureIs = null;
		try {
			List<FileItem> items = upload.parseRequest(request);
			
			System.out.println("items size: " + items.size());
			
			String id = items.get(0).getString("UTF-8");
			String name = items.get(1).getString("UTF-8");
			String price = items.get(2).getString();
			String quantity = items.get(3).getString();
			
			pictureIs = items.get(4).getInputStream();
			byte[] bytes = IOUtils.toByteArray(pictureIs);
//			System.out.println("picture bytes size: " + bytes.length);
			
			String desc = items.get(5).getString("UTF-8");
//			System.out.println("取得編輯後檔案:");
//			System.out.println("id:" + id);
//			System.out.println("name: " + name);
//			System.out.println("price: " + price);
//			System.out.println("quantity: " + quantity);
//			System.out.println("pictureIs: " + pictureIs);
//			System.out.println("desc: " + desc);
			
			// 取出商品原本內容
			Product tempProduct = service.selectOneProduct(id);
			tempProduct.setName(name);
			tempProduct.setPrice(Integer.parseInt(price));
			tempProduct.setQuantity(Integer.parseInt(quantity));
			tempProduct.setDescription(desc);
			
//			if (picClass.equals("class java.io.ByteArrayInputStream")) {
//				System.out.println("未更新圖檔!!");
//			}
			if (bytes.length != 0) {
				// 更新圖檔
//				System.out.println("有更新圖檔");
				tempProduct.setPicture(new SerialBlob(bytes));
			}
			// update date to database
			service.updateProduct(tempProduct);
			
			
			pictureIs.close();
		}catch (FileUploadException e) {
			e.printStackTrace();
		}catch (SerialException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pictureIs != null) {
				pictureIs.close();
			}
		}
		
		
		response.sendRedirect(getServletContext().getContextPath() + "/seller");
	}
}
