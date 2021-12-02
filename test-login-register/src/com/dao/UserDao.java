package com.dao;

import java.sql.*;

import com.model.User;

public class UserDao {
	
	/**
	 * 不需要拋出例外，不應該宣告為static，如果這個方法沒有要回傳值就改成void方法
	 */
	public void registerUser(User user) {
//	public static int registerUser(User user) throws ClassNotFoundException {
		String DB_URL = "jdbc:mysql://localhost:3306/shopping-cart-test?serverTimezone=UTC&useSSL=false";
		String USER = "root";
		String PASSWD = "root";
		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		//驅動參數
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL_INSERT = "INSERT INTO user(username, password) VALUES(?,?)";
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
			pstmt = conn.prepareStatement(SQL_INSERT);

			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		/**
		 * finally區塊
		 */
		finally {
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
		/**
		 * 
		 */
//		return 0;
		
	}
	
}

