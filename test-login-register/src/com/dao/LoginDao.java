package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.User;

public class LoginDao {
	
	/**
	 * 原因如 userdao
	 * 
	 */
	public boolean checkLogin(String username, String password) {
//	public static boolean checkLogin(String username, String password) throws ClassNotFoundException {

		String DB_URL = "jdbc:mysql://localhost:3306/shopping-cart-test?serverTimezone=UTC&useSSL=false";
		String USER = "root";
		String PASSWD = "root";
		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		Connection conn = null;
		PreparedStatement pstmt = null;
		/**
		 * 可以直接寫table名稱，DB_URL連線設定已經指明會用哪個資料庫
		 */
		String SQL_SELECT = "select * from user where username=? and password=?";
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
			/**
			 * 連線相關物件沒關閉，我的習慣這邊會寫關閉語法，最後finally一樣要寫關閉語法
			 * 但是這邊無法下關閉語法是因為你在這之前就已經有return方法；除非你在return以前都要關閉所有連線物件
			 * 我的寫法一定是等所有連線該做的事情都處理完畢，再return
			 * 這邊架構都要重寫
			 */
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			/**
			 * ClassNotFoundException 應該要在這邊捕捉，而不是throw掉
			 */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
//		} finally { // 這裡是一些操作資料庫之後的一些關閉操作
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				rs = null;
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				pstmt = null;
//			}
		}
		return false;
	}
}
