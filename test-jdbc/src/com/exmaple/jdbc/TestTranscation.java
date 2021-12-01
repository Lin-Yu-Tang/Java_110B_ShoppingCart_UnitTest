package com.exmaple.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class TestTranscation {
	
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3307/cmdev?useSSL=false";
	static final String USER="root";
	static final String PASS="bdse1311";
	
	static final String selectQuery = "SELECT * FROM emp where ename = ?";
	static final String updateQuery = "UPDATE emp SET salary = ? where ename = ?";
	
	public static void main(String[] args) {
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		try {
			// pre-work
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			
			// begin transaction
			conn.setAutoCommit(false);
			ResultSet rs = null;
			/*
			 * ResultSet type:
			 * 1. FORWARD_ONLY: the constant indicating the type for a ResultSet object 
			 * whose cursor may move only forward
			 * 2. SCROLL_INSENSITIVE:
			 * that is scrollable but generally not sensitive to changes made by others
			 * 3. SCROLL_SENSITIVE: 
			 * that is scrollable and generally senesitive to changes made by others
			 */
			
			PreparedStatement selectStmt = conn.prepareStatement(selectQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
			
			// set savepoint
			Savepoint save1 = conn.setSavepoint();
			
			// set Stmt
			String ename = "Tom";
			selectStmt.setString(1, ename);
			
			if (!selectStmt.execute()) {
				System.out.println("could not find employee: " + ename);
			}else {
				rs = selectStmt.getResultSet();
				rs.first();
				float salary = rs.getFloat("salary");
				float newSalary = salary + 20000;
				
				updateStmt.setFloat(1, newSalary);
				updateStmt.setString(2, ename);
				updateStmt.executeUpdate();
			}
			
			
			conn.commit();
			
			
			
			
			
			
			
			
			
			
			
			
			rs.close();
			conn.close();
		}catch (Exception e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally {
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
		
		// 
		System.out.println("done!!");
	}
}
