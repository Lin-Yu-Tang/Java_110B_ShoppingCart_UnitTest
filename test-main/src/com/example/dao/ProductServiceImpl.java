package com.example.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
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
		String SQL_SELECT = "SELECT * FROM product";
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
			pstmt = conn.prepareStatement(SQL_SELECT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Product tempProduct = new Product();
				tempProduct.setId(Integer.toString(rs.getInt("id")));
				tempProduct.setName(rs.getString("name"));
				tempProduct.setPicture(rs.getBlob("picture"));
				tempProduct.setPrice(rs.getInt("price"));
				tempProduct.setDescription(rs.getString("description"));
				list.add(tempProduct);
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
	
	/**
	 * 給定參數: id，回傳指定的產品
	 */

	@Override
	public Product selectOneProduct(String id) {
		Product tempProduct = new Product();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL_SELECT = "SELECT * FROM product WHERE id = ?";
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
			pstmt = conn.prepareStatement(SQL_SELECT);
			
			// set prepared statement
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				tempProduct.setId(Integer.toString(rs.getInt("id")));
				tempProduct.setName(rs.getString("name"));
				tempProduct.setPicture(rs.getBlob("picture"));
				tempProduct.setPrice(rs.getInt("price"));
				tempProduct.setDescription(rs.getString("description"));
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
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
			if (conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return tempProduct;
	}

	@Override
	public void updateProduct(Product product) {
		Connection conn = null;
		PreparedStatement selectStmt = null;
		PreparedStatement updateStmt = null;
		String SQL_SELECT = "SELECT * FROM product WHERE id = ?";
		String SQL_UPDATE = "Update product set name = ? "
				+ ", picture = ? "
				+ ", price = ? "
				+ ", description = ? "
				+ "WHERE id = ?";
		
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
						
			// begin transaction
			conn.setAutoCommit(false);
			selectStmt = conn.prepareStatement(SQL_SELECT);
			updateStmt = conn.prepareStatement(SQL_UPDATE);
			// set pstmt
			selectStmt.setString(1, product.getId());
			if (!selectStmt.execute()) {
				System.out.println("could not find the Product, id = " + product.getId());
			}else {
				updateStmt.setString(1, product.getName());
				updateStmt.setBlob(2, product.getPicture());
				updateStmt.setInt(3, product.getPrice());
				updateStmt.setString(4, product.getDescription());
				updateStmt.setInt(5, Integer.parseInt(product.getId()));
				updateStmt.executeUpdate();
				conn.commit();
			}
			updateStmt.close();
			selectStmt.close();
			conn.close();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			if(conn != null) {
				try {
					System.out.print("transaction is being rolled back");
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally {
			try {
				selectStmt.close();
				updateStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteProdct(String id) {
		Connection conn = null;
		PreparedStatement selectStmt = null;
		PreparedStatement deleteStmt = null;
		String SQL_SELECT = "SELECT * FROM product WHERE id = ?";
		String SQL_DELETE = "DELETE FROM product WHERE id = ?";
		
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
						
			// begin transaction
			conn.setAutoCommit(false);
			
			selectStmt = conn.prepareStatement(SQL_SELECT);
			deleteStmt = conn.prepareStatement(SQL_DELETE);
			// set pstmt
			selectStmt.setString(1, id);
			if (!selectStmt.execute()) {
				System.out.println("could not find the Product, id = " + id);
			}else {
			
			deleteStmt.setInt(1, Integer.parseInt(id));
			deleteStmt.executeUpdate();
			conn.commit();
			System.out.println("product(id = " + id + ") had been deleted!!");
			}
			
			selectStmt.close();
			deleteStmt.close();
			conn.close();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			if(conn != null) {
				try {
					System.out.print("transaction is being rolled back");
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally {
			try {
				selectStmt.close();
				deleteStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}














