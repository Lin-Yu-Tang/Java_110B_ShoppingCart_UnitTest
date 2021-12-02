package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.User;

public class LoginDao {
	public static boolean checkLogin(String username, String password) throws ClassNotFoundException {

		String DB_URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useSSL=false";
		String USER = "root";
		String PASSWD = "123456";
		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL_SELECT = "select * from test.user where username=? and password=?";
		ResultSet rs = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
			pstmt = conn.prepareStatement(SQL_SELECT);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				String pwd = rs.getString("password");// 找到資料類裡面user所對應的passwrod
				if (pwd.equals(password)) {// 把我們從資料庫中找出來的password和從jsp中傳過來的passwrod比較
					return true; // ture代表驗證成功
				} else {
					return false;// false代表驗證失敗
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // 這裡是一些操作資料庫之後的一些關閉操作
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rs = null;
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pstmt = null;
			}
		}
		return false;
	}
}
