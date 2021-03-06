package com.example.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.model.Product;

/**
 * @author Tom Lin
 * @apiNote 實作商品相關的服務(crud)
 */

public class ProductServiceImpl implements ProductService {
	// 資料庫內容
	private final String DB_URL = "jdbc:mysql://localhost:3306/shopping-cart-test?useSSL=false";
	private final String USER = "root";
	private final String PASSWD = "root";
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	
	/**
	 * 新增商品，存入資料庫
	 */
	@Override
	public void saveProduct(Product tempProduct) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL_INSERT = "INSERT INTO "
				+ "product(name,picture,price,quantity,seller_id,description) "
				+ "VALUES(?,?,?,?,?,?)"; 
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
			pstmt = conn.prepareStatement(SQL_INSERT);
			
			// set prepared statement
			pstmt.setString(1, tempProduct.getName());
			pstmt.setBlob(2, tempProduct.getPicture());
			pstmt.setInt(3, tempProduct.getPrice());
			pstmt.setInt(4, tempProduct.getQuantity());
			pstmt.setInt(5, Integer.parseInt(tempProduct.getSeller_id()));
			pstmt.setString(6, tempProduct.getDescription());
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
	/**
	 * 取得所有商品，首頁載入商品時使用
	 */
	@Override
	public ArrayList<Product> listAllProduct() {
		ArrayList<Product> list = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL_SELECT = "SELECT id, name, picture, "
				+ "price, quantity, seller_id, description "
				+ "FROM product";
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
				tempProduct.setQuantity(rs.getInt("quantity"));
				tempProduct.setSeller_id(Integer.toString(rs.getInt("seller_id")));
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
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
		return list;
	}
	
	/**
	 * 給定參數: id，回傳指定的產品所有欄位
	 */

	@Override
	public Product selectOneProduct(String id) {
		Product tempProduct = new Product();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL_SELECT = "SELECT id, name, picture, "
				+ "price, quantity, seller_id, description "
				+ "FROM product "
				+ "WHERE id = ?";
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
				tempProduct.setQuantity(rs.getInt("quantity"));
				tempProduct.setSeller_id(Integer.toString(rs.getInt("seller_id")));
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
	/**
	 * 更新特定商品
	 */
	
	@Override
	public void updateProduct(Product product) {
		Connection conn = null;
		PreparedStatement selectStmt = null;
		PreparedStatement updateStmt = null;
		String SQL_SELECT = "SELECT id, name, picture, "
				+ "price, quantity, seller_id, description "
				+ "FROM product "
				+ "WHERE id = ?";
		String SQL_UPDATE = "Update product set name = ? "
				+ ", picture = ? "
				+ ", price = ? "
				+ ", quantity = ? "
				+ ", seller_id = ? "
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
				updateStmt.setInt(4, product.getQuantity());
				updateStmt.setInt(5, Integer.parseInt(product.getSeller_id()));
				updateStmt.setString(6, product.getDescription());
				// where condition
				updateStmt.setInt(7, Integer.parseInt(product.getId()));
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

	/**
	 * 刪除特定商品
	 */
	@Override
	public void deleteProdct(String id) {
		Connection conn = null;
		PreparedStatement selectStmt = null;
		PreparedStatement deleteStmt = null;
		String SQL_SELECT = "SELECT name FROM product WHERE id = ?";
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
			if (selectStmt != null) {
				try {
					selectStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (deleteStmt != null) {
				try {
					deleteStmt.close();
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
	
	/**
	 * 依照關鍵字搜尋商品(商品名稱/商品描述符合條件者)
	 */
	@Override
	public ArrayList<Product> searchProduct(String[] strs) {
		ArrayList<Product> list = new ArrayList<Product>();
		final int keywordSize = strs.length;
		String SQL_SELECT = "SELECT id, name, picture, price, quantity, seller_id, description "
				+ "FROM product WHERE NAME LIKE ? or DESCRIPTION LIKE ?";
		// rebuild the SQL statement
		for (int i=0; i<keywordSize-1; i++) {
			SQL_SELECT += "or NAME LIKE ? or DESCRIPTION LIKE ?";
		}
		
		// create conection object
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
			pstmt = conn.prepareStatement(SQL_SELECT);
			
			// set prepared statement
			for (int i=0; i<keywordSize; i++) {
				if (i == 0) {
					pstmt.setString(i+1, "%" + strs[i] + "%");
					pstmt.setString(i+2, "%" + strs[i] + "%");
				}else {
					pstmt.setString(i*2 + 1, "%" + strs[i] + "%");
					pstmt.setString(i*2 + 2, "%" + strs[i] + "%");
				}
				// mapping rule
				/*
				k SQL
				0 : 1 2       k+1 k+2
				1 : 3 4       2k+1 2k+2
				2 : 5 6       2k+1 2k+2
				3 : 7 8       2k+1 2k+2
				 */
			}

			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Product tempProduct = new Product();
				tempProduct.setId(Integer.toString(rs.getInt("id")));
				tempProduct.setName(rs.getString("name"));
				tempProduct.setPicture(rs.getBlob("picture"));
				tempProduct.setPrice(rs.getInt("price"));
				tempProduct.setQuantity(rs.getInt("quantity"));
				tempProduct.setSeller_id(Integer.toString(rs.getInt("seller_id")));
				tempProduct.setDescription(rs.getString("description"));
				list.add(tempProduct);
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
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
		
		
		return list;
	}
	
	/**
	 * 取得商品當前庫存
	 * map = {商品Id : 商品數量}
	 */
	@Override
	public HashMap<Integer, Integer> getCurrentStorage(ArrayList<Product> products) {
		if (products == null) {return null;}
		HashMap map = new HashMap();
		String SQL_SELECT = "SELECT quantity FROM product WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
			pstmt = conn.prepareStatement(SQL_SELECT);
			
			for (int i=0; i<products.size();i++) {
				pstmt.setInt(1, Integer.parseInt(products.get(i).getId()));
				rs = pstmt.executeQuery();
				while(rs.next()) {
					map.put(Integer.parseInt(products.get(i).getId()), rs.getInt("quantity"));
				}
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
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
		return map;
	}
	
	/**
	 * 依賣家ID 取得賣家所有商品
	 */
	@Override
	public ArrayList<Product> getAllProductsBySellerId(String sellerId) {
		ArrayList<Product> products = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL_SELECT = "SELECT id,name,picture,price,quantity,description,created_time "
				+ "FROM product WHERE seller_id = ?";
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
			pstmt = conn.prepareStatement(SQL_SELECT);
			pstmt.setInt(1, Integer.parseInt(sellerId));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Product tempProduct = new Product();
				tempProduct.setId(Integer.toString(rs.getInt("id")));
				tempProduct.setName(rs.getString("name"));
				tempProduct.setPicture(rs.getBlob("picture"));
				tempProduct.setPrice(rs.getInt("price"));
				tempProduct.setQuantity(rs.getInt("quantity"));
				tempProduct.setSeller_id(sellerId);
				tempProduct.setDescription(rs.getString("description"));
				tempProduct.setCreated_time(rs.getDate("created_time"));
				products.add(tempProduct);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
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
		return products;
	}
	
	
	
	
	
}














