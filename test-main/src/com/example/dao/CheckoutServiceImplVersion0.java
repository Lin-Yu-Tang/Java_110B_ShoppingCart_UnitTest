package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.model.Order;
import com.example.model.OrderItem;
import com.example.model.Product;
import com.example.model.ShoppingCart;

/**
 * @author Tom Lin
 * @apiNote 第一個結帳版本，錯誤寫法
 */
public class CheckoutServiceImplVersion0 implements CheckoutService {
	// 資料庫內容
	private final String DB_URL = "jdbc:mysql://localhost:3306/shopping-cart-test?useSSL=false";
	private final String USER = "root";
	private final String PASSWD = "root";
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	/**
	 * 給定userId、ShoppingCart寫入orders & orders_items table
	 */
	@Override
	public boolean checkout(String userId, String shippingAddress, ShoppingCart cart) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmtProductUpdate = null;
		PreparedStatement pstmtOrder = null;
		ResultSet rs = null;
		String SQL_UPDATE_PRODUCT = "UPDATE product SET quantity = ? WHERE id = ?";
		String SQL_INSERT_ORDERS = "INSERT INTO orders(user_id,total_price,shipping_address) "
				+ "VALUES (?,?,?); ";
		String SQL_SELECT_ORDERS = "SELECT id FROM orders "
				+ "WHERE user_id = ? "
				+ "ORDER BY created_time DESC LIMIT 1;";
		String SQL_INSERT_ORDER_ITEM = 
				"INSERT INTO order_items(id_sorted,product_id,quantity,order_id) "
				+ "VALUES (?,?,?,?) ";
		// 依照商品數量重建SQL statement
//		if (cart.getProducts().size() > 1) {
//			for (int i = 1; i<cart.getProducts().size();i++) {
//				SQL_INSERT_ORDER_ITEM += ",(?,?,?,?)";
////				SQL_UPDATE_PRODUCT += "UPDATE product SET quantity = ? WHERE id = ? ;";
//			}
//			SQL_INSERT_ORDER_ITEM += ";";
//		}
		
		Order order = new Order();
		ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
		// specify the customer & set order fields
		order.setUserId(userId);
		order.setTotalPrice(cart.getTotalAmount());
		order.setShippingAddress(shippingAddress);
		
		
		// 從cart 中取得商品資料
		ArrayList<Product> products = cart.getProducts();
		ProductServiceImpl productService = new ProductServiceImpl();
		
		// 將商品庫存狀況存入map, productID:storage
		HashMap<Integer, Integer> map = productService.getCurrentStorage(cart.getProducts());
		
		// 確認所有products array與map 資料正確
//		cart.getProducts().forEach(i -> 
//		System.out.println(i.getId() + " : " + map.get(Integer.parseInt(i.getId()))));
		
		for (int i = 0; i < products.size(); i++) {
			int temp = map.get(Integer.parseInt( products.get(i).getId() )) 
					- products.get(i).getQuantity();
			
			if (temp < 0) {
				return false;
			}
		}
			
		
		
		
		// prepare orderitems 
		for (int i=0; i<products.size();i++) {
			OrderItem items = new OrderItem();
			items.setId(i+1+"");
			items.setProductId(products.get(i).getId());
			items.setQuantity(products.get(i).getQuantity());
			orderItems.add(items);
		}
//		orderItems.forEach(i -> System.out.println(i)); // 確認orderItems成功被建立
		order.setItems(orderItems);
		
		
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
			
			// begin transaction
			conn.setAutoCommit(false);
			
			// update product資料
			for (int i = 0; i < products.size(); i++) {
				pstmtProductUpdate = conn.prepareStatement(SQL_UPDATE_PRODUCT);
				pstmtProductUpdate.setInt(1, (map.get(Integer.parseInt(products.get(i).getId())) -
							products.get(i).getQuantity()) );
				pstmtProductUpdate.setInt(2, Integer.parseInt(products.get(i).getId()));
				
				// execute update
				System.out.println(pstmtProductUpdate.toString());
				pstmtProductUpdate.executeUpdate();
				
				//clear parameters
				pstmtProductUpdate.clearParameters();
			}
			
			
			
			
			/* 第一版寫法
			for (int i = 0; i < products.size();i++) {
				// 更新後的數量為: 庫存 - 購買數量
				if (i == 0) {
					pstmtProductUpdate.setInt(i+1, 
							(map.get(Integer.parseInt(products.get(i).getId())) -
							products.get(i).getQuantity()) );
					pstmtProductUpdate.setInt(i+2, Integer.parseInt(products.get(i).getId()));
				}else {
					pstmtProductUpdate.setInt(i*2 + 1, 
							(map.get(Integer.parseInt(products.get(i).getId())) -
							products.get(i).getQuantity()) );
					pstmtProductUpdate.setInt(i*2 + 2, Integer.parseInt(products.get(i).getId()));
				}
			}
			System.out.println(pstmtProductUpdate.toString());
			pstmtProductUpdate.executeUpdate();
			*/
			
			//存入 order
//			pstmtOrder = conn.prepareStatement(SQL_INSERT_ORDERS, Statement.RETURN_GENERATED_KEYS);
			pstmtOrder = conn.prepareStatement(SQL_INSERT_ORDERS);
			pstmtOrder.setInt(1, Integer.parseInt(userId));
			pstmtOrder.setInt(2, order.getTotalPrice());
			pstmtOrder.setString(3, order.getShippingAddress());
			
			pstmtOrder.executeUpdate();
//			rs = pstmtOrder.getGeneratedKeys();
//			System.out.println("get generated keys : " + rs.getInt("id"));
			// 無法取得寫入的資訊
			
			// 取得order id
			pstmtOrder.clearParameters();
			
			pstmtOrder = conn.prepareStatement(SQL_SELECT_ORDERS);
			pstmtOrder.setInt(1, Integer.parseInt(userId));
			System.out.println(pstmtOrder.toString());
			
			
			rs = pstmtOrder.executeQuery();
			int orderId = -1;
			
			while (rs.next()) {
				orderId = rs.getInt("id");
			}
			
			System.out.println("current oder id: " + orderId);
			
			
			
			// 存入 order item

			for (int i = 0; i < orderItems.size(); i++) {
				System.out.println("order items: ");
				System.out.println(i + " : " + orderItems.get(i));
				pstmtOrder.clearParameters();
				pstmtOrder = conn.prepareStatement(SQL_INSERT_ORDER_ITEM);
				
				pstmtOrder.setInt(1, Integer.parseInt(orderItems.get(i).getId()));
				pstmtOrder.setInt(2, Integer.parseInt(orderItems.get(i).getProductId()));
				pstmtOrder.setInt(3, orderItems.get(i).getQuantity());
				pstmtOrder.setInt(4, orderId);
				
				pstmtOrder.executeUpdate();
			}
//			String SQL_INSERT_ORDER_ITEM = 
//					"INSERT INTO order_items(id_sorted,product_id,quantity,order_id) "
//					+ "VALUES (?,?,?,?) ";
			
			/*
			pstmtOrder = conn.prepareStatement(SQL_INSERT_ORDER);
			pstmtOrder.setInt(1, Integer.parseInt(userId));
			pstmtOrder.setInt(2, order.getTotalPrice());
			pstmtOrder.setString(3, order.getShippingAddress());
			for (int i = 0; i<orderItems.size();i++) {
				// set string: id_sorted, product_id, quantity
				if (i == 0) {
					pstmtOrder.setInt(i+4, i+1);
					pstmtOrder.setInt(i+5, Integer.parseInt(orderItems.get(i).getProductId()));
					pstmtOrder.setInt(i+6, orderItems.get(i).getQuantity());
				}else {
					pstmtOrder.setInt(i*3 + 4, i+1);
					pstmtOrder.setInt(i*3 + 5, Integer.parseInt(orderItems.get(i).getProductId()));
					pstmtOrder.setInt(i*3 + 6, orderItems.get(i).getQuantity());
				}

				0 4 5 6      	i+4 i+5 i+6
				1 7 8 9			3i+4 3i+5 3i+6
				2 10 11 12		3i+4 3i+5 3i+6
				3 13 14 15		3i+4 3i+5 3i+6

			}
			System.out.println(pstmtOrder.toString());
			*/
			
			// 存入seller銷貨單
			// order 是否加入付款方式(轉帳/到貨付款)
			
			// execute update
//			pstmtOrder.executeUpdate();
			conn.commit();
			
		
		
		
		
//			conn.rollback();
		
		// close all connection object
			pstmtProductUpdate.close();
			pstmtOrder.close();
			conn.close();
		
		result = true;
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			try {
				System.out.print("[checkout]: transaction is being rolled back"
						+ "userID: " + userId);
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			if (pstmtProductUpdate != null) {
				try {
					pstmtProductUpdate.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmtOrder != null) {
				try {
					pstmtOrder.close();
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
		// transaction successful? true:false
		return result;
	}
}
