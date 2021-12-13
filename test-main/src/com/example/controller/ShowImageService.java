package com.example.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ProductServiceImpl;
import com.example.model.Product;

/**
 * @author Tom Lin
 * @apiNote 網頁端取得照片調用的servlet
 */
@WebServlet("/showImage")
public class ShowImageService extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pnum = request.getParameter("pnum");
		if (pnum != null) {
			response.setContentType("image/png");
			ProductServiceImpl service = new ProductServiceImpl();
			Product product = service.selectOneProduct(pnum);
			Blob picture = product.getPicture();
			ServletOutputStream outputStream = null;
			
			try {
				InputStream binaryStream = picture.getBinaryStream();
				BufferedImage bi = ImageIO.read(binaryStream);
				outputStream
				= response.getOutputStream();
				ImageIO.write(bi, "png", outputStream);
				outputStream.close();
			}catch (NullPointerException e) {
				System.out.println("picture not found");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (outputStream != null)
					outputStream.close();
			}
		}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
