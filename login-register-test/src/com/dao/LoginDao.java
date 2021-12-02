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

				String pwd = rs.getString("password");// ��������̭�user�ҹ�����passwrod
				if (pwd.equals(password)) {// ��ڭ̱q��Ʈw����X�Ӫ�password�M�qjsp���ǹL�Ӫ�passwrod���
					return true; // ture�N�����Ҧ��\
				} else {
					return false;// false�N�����ҥ���
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // �o�̬O�@�Ǿާ@��Ʈw���᪺�@�������ާ@
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
