package com.dao;

import java.sql.*;

import com.model.User;

public class UserDao {
	public static int registerUser(User user) throws ClassNotFoundException {
		String DB_URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useSSL=false";
		String USER = "root";
		String PASSWD = "123456";
		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		//ÅX°Ê°Ñ¼Æ
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL_INSERT = "INSERT INTO test.user(username, password) VALUES(?,?)";
		
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
		return 0;
		
	}
	
}

