package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import com.example.model.SellerOrder;
import com.example.model.SellerOrderItem;

/**
 * @author Tom Lin
 * @apiNote 商品管理頁面相關資料庫操作
 */
public class SellerServiceImpl implements SellerService {
	// 資料庫內容
	private final String DB_URL = "jdbc:mysql://localhost:3306/shopping-cart-test?useSSL=false";
	private final String USER = "root";
	private final String PASSWD = "root";
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	
	/**
	 * 取得賣家password，登入驗證專用
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

	
	/**
	 * 透過使用者登入的帳號取得seller id 作為後續資料庫操作的參數
	 */
	@Override
	public String getSellerId(String sellerAccount) {
		String sellerId = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL_SELECT_ID = "SELECT id FROM seller WHERE account_name = ?";
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
			
			pstmt = conn.prepareStatement(SQL_SELECT_ID);
			pstmt.setString(1, sellerAccount);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				sellerId = "" + rs.getInt("id");
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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return sellerId;
	}

	/**
	 * 依賣家id 取得所有訂單資料
	 */
	@Override
	public ArrayList<SellerOrder> getAllSellerOrders(String sellerId) {
		ArrayList<SellerOrder> orderList = new ArrayList<SellerOrder>();
		ArrayList<SellerOrderItem> itemList = new ArrayList<SellerOrderItem>();
		HashSet<String> orderIdSet = new HashSet<String>(); 
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL_SELECT = 
				"SELECT o.id,o.user_id,o.order_status,o.shipping_address,o.created_time,"
				+ "i.id,p.name,i.price,i.quantity,i.order_id "
				+ "FROM seller_orders o "
				+ "LEFT OUTER JOIN seller_order_items i on o.id = i.order_id "
				+ "LEFT OUTER JOIN product p on i.product_id = p.id "
				+ "WHERE o.seller_id = ?";
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
			
			pstmt = conn.prepareStatement(SQL_SELECT);
			pstmt.setInt(1,Integer.parseInt(sellerId));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SellerOrder order = new SellerOrder();
				order.setId("" + rs.getInt("o.id"));
				order.setSellerId(sellerId);
				order.setUserId("" + rs.getInt("o.user_id"));
				order.setStatus(rs.getString("o.order_status"));
				order.setShippingAddress(rs.getString("o.shipping_address"));
				order.setCreateTime(rs.getDate("o.created_time"));
				
				
				// 將未重複訂單存到orderList
//				System.out.println("order: " + order);
				if (orderList.size() == 0) {
					orderList.add(order);
					orderIdSet.add(order.getId());
				}
				else {
//					System.out.println("orderSet" + orderIdSet);
					
					if (!orderIdSet.contains(order.getId())) {
						orderList.add(order);
						orderIdSet.add(order.getId());
					}
						
				}
					
					// foreach會產生ConcurrentModificationException
				
//					System.out.println("OrderId: " + tempOrderId);
//					for (SellerOrder o:orderList) {
//						if (!o.getId().equals(tempOrderId))
//							System.out.println("not equals");
//							orderList.add(order);
//					}
					
//					orderList.forEach( i -> {
//						System.out.println("orderId: " + i.getId());
//						if (!i.getId().equals(tempOrderId))
//							orderList.add(order);
//					});
				SellerOrderItem orderItem = new SellerOrderItem();
				orderItem.setId("" + rs.getInt("i.id"));
				orderItem.setSellerId(sellerId);
				orderItem.setProductId(rs.getString("p.name"));
				orderItem.setPrice(rs.getInt("i.price"));
				orderItem.setQuantity(rs.getInt("i.quantity"));
				orderItem.setOrderId(order.getId());
				
//				System.out.println("add orderItem: " + orderItem);
				itemList.add(orderItem);
			}
			/*
			System.out.println("items size: " + itemList.size());
			System.out.println("order size: " + orderList.size());
			orderList.forEach(i -> System.out.println(i));
			itemList.forEach(i -> System.out.println(i));
			 */
			
			// 將order items存入 orders
			for (int i = 0; i<orderList.size(); i++) {
				SellerOrder tempOrder = orderList.get(i);
				ArrayList<SellerOrderItem> tempItemList = new ArrayList<SellerOrderItem>();
				
				for (int j = 0; j<itemList.size(); j++) {
					SellerOrderItem tempOrderItem = itemList.get(j);
					if (tempOrder.getId().equals(tempOrderItem.getOrderId())) {
						tempItemList.add(tempOrderItem);
					}
				}
				tempOrder.setItems(tempItemList);
			}
			
//			orderList.forEach(i -> System.out.println(i));
			
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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return orderList;
	}
	
	

}
