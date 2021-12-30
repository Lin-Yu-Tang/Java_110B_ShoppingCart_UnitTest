package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerServiceImpl implements SellerService {
	// 資料庫內容
	private final String DB_URL = "jdbc:mysql://localhost:3306/shopping-cart-test?useSSL=false";
	private final String USER = "root";
	private final String PASSWD = "root";
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	
	/**
	 * 取得賣家pass，登入驗證專用
	 */
	@Override
	public String getLoginPass(String sellerAccount) {
		String passwd = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL_SELECT = "SELECT password FROM seller "
				+ "WHERE account_name = ?";
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
			pstmt = conn.prepareStatement(SQL_SELECT);
			
			pstmt.setString(1, sellerAccount);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				passwd = rs.getString("password");
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
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
		return passwd;
	}

}
