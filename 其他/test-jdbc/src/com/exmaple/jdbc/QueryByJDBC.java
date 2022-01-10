package com.exmaple.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC原型
 */

public class QueryByJDBC {

	public static void main(String[] args) {
		
		// 資料庫內容
		String dbUrl = "jdbc:mysql://localhost:3307/cmdev?useSSL=false"; // 連線到localhost的3307port的cmdev資料庫
		String user = "root"; // MYSQL connection 的帳號
		String passwd = "bdse1311"; // 密碼
		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // jdbc driver
		
		
		try {
			// Step 1 : 註冊jdbc driver
			Class.forName(JDBC_DRIVER);
	
			
			
			// Step 2 : 建立連線物件
			Connection conn = DriverManager.getConnection(dbUrl, user, passwd);
			
			System.out.println("Connecting to database...");

			// Step 3 : create a query
			Statement stmt = conn.createStatement();
			
			String sql = 
					"select * from dept where deptno = 1000";
			
			// Step 4 : execute the query
			ResultSet rs = stmt.executeQuery(sql);
			

			// Step 5 : extract data from result set
			while(rs.next() != false) {
					
				// retrieve each column
				int id = rs.getInt("deptno");
				String name = rs.getString("dname");
				String loc = rs.getString("location");
					
				// display
				System.out.printf("id: %d, name: %s, location: %s%n",
						id, name, loc);
				}
			
			
			
			// Step 6 : clean up environment
			rs.close();
			stmt.close();
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
