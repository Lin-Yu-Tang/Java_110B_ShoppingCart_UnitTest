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
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.example.dao.ProductServiceImpl;
import com.example.dao.SellerServiceImpl;
import com.example.model.Product;

/**
 * @author Tom Lin
 * @apiNote doGet: seller home page -> 新增商品； doPost: 寫入新增商品表單至資料庫
 */
@WebServlet("/seller/create")
public class SellerProductCreateServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.sendRedirect("createPage");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
//		System.out.println("新增商品~~~");
		
		
		SellerServiceImpl sellerService = new SellerServiceImpl();
		
		HttpSession session = request.getSession();
		
		String sellerId = sellerService.getSellerId((String) session.getAttribute("SID"));
		
//		System.out.println(sellerId);
		
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
			
//			System.out.println("items size: " + items.size());
			
			String name = items.get(0).getString("UTF-8");
			String price = items.get(1).getString();
			String quantity = items.get(2).getString();
			
			pictureIs = items.get(3).getInputStream();
			byte[] bytes = IOUtils.toByteArray(pictureIs);
			
			String desc = items.get(4).getString("UTF-8");
			
			// 寫入商品bean中
			Product tempProduct = new Product();
			tempProduct.setName(name);
			tempProduct.setPrice(Integer.parseInt(price));
			tempProduct.setQuantity(Integer.parseInt(quantity));
			tempProduct.setPicture(new SerialBlob(bytes));
			tempProduct.setDescription(desc);
			tempProduct.setSeller_id(sellerId);
			
			
//			System.out.println(tempProduct);
			
			
			// 寫入資料庫
			service.saveProduct(tempProduct);
			
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
