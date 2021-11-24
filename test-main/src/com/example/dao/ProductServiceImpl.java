package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.model.Product;

public class ProductServiceImpl implements ProductService {
	// 資料庫內容
	private final String DB_URL = "jdbc:mysql://localhost:3306/shopping-cart-test?useSSL=false";
	private final String USER = "root";
	private final String PASSWD = "root";
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
			
	@Override
	public void saveProduct(Product tempProduct) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL_INSERT = "INSERT INTO product(name,picture,price,description) VALUES(?,?,?,?)"; 
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
			pstmt = conn.prepareStatement(SQL_INSERT);
			
			// set prepared statement
			pstmt.setString(1, tempProduct.getName());
			pstmt.setBlob(2, tempProduct.getPicture());
			pstmt.setInt(3, tempProduct.getPrice());
			pstmt.setString(4, tempProduct.getDescription());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public ArrayList<Product> ListAllProduct() {
		ArrayList<Product> list = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL_SELECT = "SELECT name,picture,price,description FROM product";
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
			pstmt = conn.prepareStatement(SQL_SELECT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Product tempProduct = new Product();
				tempProduct.setName(rs.getString("name"));
				tempProduct.setPicture(rs.getBlob("picture"));
				tempProduct.setPrice(rs.getInt("price"));
				tempProduct.setDescription(rs.getString("description"));
				System.out.println(tempProduct);
				list.add(tempProduct);
			}
			
			pstmt.close();
			conn.close();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
}
