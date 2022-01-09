package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import com.example.model.Order;
import com.example.model.OrderItem;
import com.example.model.Product;
import com.example.model.SellerOrderItem;
import com.example.model.ShoppingCart;

/**
 * @author Tom Lin
 * @apiNote 實作結帳方法
 */
public class CheckoutServiceImpl implements CheckoutService {
	// 資料庫內容
	private final String DB_URL = "jdbc:mysql://localhost:3306/shopping-cart-test?useSSL=false";
	private final String USER = "root";
	private final String PASSWD = "root";
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	/**
	 * 給定userId、ShoppingCart、shippingAddress
	 * 寫入買家訂單table(orders & orders_items)
	 * 與賣家訂單table(seller_orders & seller_order_items)
	 */
	@Override
	public boolean checkout(String userId, String shippingAddress, ShoppingCart cart) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmtProduct = null;
		PreparedStatement pstmtOrder = null;
		ResultSet rs = null;
		String SQL_SELECT_PRODUCT = "SELECT quantity FROM product WHERE id = ?";
		String SQL_UPDATE_PRODUCT = "UPDATE product SET quantity = ? WHERE id = ?";
		String SQL_INSERT_ORDERS = "INSERT INTO orders(user_id,total_price,shipping_address) "
				+ "VALUES (?,?,?); ";
		String SQL_SELECT_ORDERS = "SELECT id FROM orders "
				+ "WHERE user_id = ? "
				+ "ORDER BY created_time DESC LIMIT 1;";
		String SQL_INSERT_ORDER_ITEMS = 
				"INSERT INTO order_items(id_sorted,product_id,quantity,order_id) "
				+ "VALUES (?,?,?,?) ";
		// for seller
		String SQL_INSERT_SELLER_ORDERS = "INSERT INTO seller_orders(seller_id,user_id,shipping_address)"
				+ "VALUES (?,?,?)";
		String SQL_SELECT_SELLER_ORDERS = "SELECT id FROM seller_orders "
				+ "WHERE seller_id = ? "
				+ "ORDER BY created_time DESC LIMIT 1;";
		String SQL_INSERT_SELLER_ORDER_ITEMS = "INSERT seller_order_items(product_id,price,quantity,order_id) "
				+ "VALUES (?,?,?,?)";

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
		
		// 若商品庫存小於購買數量，直接return false，不進入transaction
		for (int i = 0; i < products.size(); i++) {
			int temp = map.get(Integer.parseInt( products.get(i).getId() )) 
					- products.get(i).getQuantity();
			
			if (temp < 0) {
				System.out.println("庫存不足!!!");
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
//		System.out.println("All order items: ");
//		orderItems.forEach(i -> System.out.println(i)); // 確認orderItems成功被建立
		order.setItems(orderItems);
		
		// prepare for seller order & seller order item
		ArrayList<String> sellerIdList = new ArrayList<String>();
		ArrayList<SellerOrderItem> sellerItemList = new ArrayList<SellerOrderItem>();
		cart.getProducts().forEach(i -> {
			if (!sellerIdList.contains(i.getSeller_id()))
			sellerIdList.add(i.getSeller_id());
		});
		
		
		sellerIdList.forEach(i -> System.out.println(i));
		
		for (int i = 0; i < products.size(); i++) {
			SellerOrderItem items = new SellerOrderItem();
			items.setSellerId(products.get(i).getSeller_id());
			items.setProductId(products.get(i).getId());
			items.setPrice(products.get(i).getPrice());
			items.setQuantity(products.get(i).getQuantity());
			sellerItemList.add(items);
		}
		
		System.out.println("All seller items: ");
		sellerItemList.forEach(i -> System.out.println(i)); // 確認seller order items建立
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
			
			// begin transaction
			System.out.println("Begin transaction");
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			HashMap<Integer, Integer> currentProductQuantity = new HashMap<Integer, Integer>();
			// select product 資料
			pstmtProduct = conn.prepareStatement(SQL_SELECT_PRODUCT);
			for (int i = 0; i < products.size(); i++) {
				pstmtProduct.setInt(1, Integer.parseInt(products.get(i).getId()));
//				System.out.println(pstmtProduct.toString());
				rs = pstmtProduct.executeQuery();
				
				while(rs.next()) {
					currentProductQuantity.put(Integer.parseInt(products.get(i).getId()), 
							rs.getInt("quantity"));
				}
				pstmtProduct.clearParameters();
				
			}
			int calResult;
			for (int i = 0; i < products.size(); i++) {
				calResult = currentProductQuantity.get(Integer.parseInt(products.get(i).getId())) -  products.get(i).getQuantity();
				if (calResult < 0) {
					System.out.println("[庫存不足]: productID: " + products.get(i).getId() + ", storage: " 
							+ currentProductQuantity.get(Integer.parseInt(products.get(i).getId()))
							+ " - " + products.get(i).getQuantity() + " = "+ calResult + " < 0");
					return false;
				}
			}
			
			
			
			
			// update product資料
			for (int i = 0; i < products.size(); i++) {
				pstmtProduct.clearParameters();
				pstmtProduct = conn.prepareStatement(SQL_UPDATE_PRODUCT);
				pstmtProduct.setInt(1, (map.get(Integer.parseInt(products.get(i).getId())) -
							products.get(i).getQuantity()) );
				pstmtProduct.setInt(2, Integer.parseInt(products.get(i).getId()));
				
				// execute update
//				System.out.println(pstmtProduct.toString());
				pstmtProduct.executeUpdate();
				
				//clear parameters
				pstmtProduct.clearParameters();
			}
			

			//存入 order
			pstmtOrder = conn.prepareStatement(SQL_INSERT_ORDERS);
			pstmtOrder.setInt(1, Integer.parseInt(userId));
			pstmtOrder.setInt(2, order.getTotalPrice());
			pstmtOrder.setString(3, order.getShippingAddress());
			
			pstmtOrder.executeUpdate();
			
			// 取得order id
			pstmtOrder.clearParameters();
			pstmtOrder = conn.prepareStatement(SQL_SELECT_ORDERS);
			pstmtOrder.setInt(1, Integer.parseInt(userId));
//			System.out.println(pstmtOrder.toString());
			
			rs = pstmtOrder.executeQuery();
			int orderId = -1;
			
			while (rs.next()) {
				orderId = rs.getInt("id");
			}
//			System.out.println("current oder id: " + orderId);
			
			
			// 存入 order item
			for (int i = 0; i < orderItems.size(); i++) {
//				System.out.println("order items: ");
//				System.out.println(i + " : " + orderItems.get(i));
				pstmtOrder.clearParameters();
				pstmtOrder = conn.prepareStatement(SQL_INSERT_ORDER_ITEMS);
				
				pstmtOrder.setInt(1, Integer.parseInt(orderItems.get(i).getId()));
				pstmtOrder.setInt(2, Integer.parseInt(orderItems.get(i).getProductId()));
				pstmtOrder.setInt(3, orderItems.get(i).getQuantity());
				pstmtOrder.setInt(4, orderId);
				
				pstmtOrder.executeUpdate();
			}

			
			// 存入seller_orders 銷貨單
			for (int i = 0; i < sellerIdList.size(); i++) {
				
				pstmtOrder.clearParameters();
				pstmtOrder = conn.prepareStatement(SQL_INSERT_SELLER_ORDERS);
				pstmtOrder.setInt(1, Integer.parseInt(sellerIdList.get(i)));
				pstmtOrder.setInt(2, Integer.parseInt(userId));
				pstmtOrder.setString(3, shippingAddress);
//				System.out.println(pstmtOrder.toString());
				
				pstmtOrder.executeUpdate();
				
			}


			// 存入seller_order_items 銷貨單明細
			// 透過 
			// 取得 seller_orders id
			HashMap<String,Integer> sellerOrderMap = new HashMap<String,Integer>();
			rs = null;
			orderId = -1;
			for (int i = 0; i < sellerIdList.size();i++) {
				pstmtOrder.clearParameters();
				pstmtOrder = conn.prepareStatement(SQL_SELECT_SELLER_ORDERS);
				pstmtOrder.setInt(1, Integer.parseInt(sellerIdList.get(i)));
				
				rs = pstmtOrder.executeQuery();
				while (rs.next()) {
					orderId = rs.getInt("id");
				}
				System.out.println("current seller_oder id: " + orderId);
				
				sellerOrderMap.put(sellerIdList.get(i), orderId);
			}
			

			// 存入seller_order_items
			System.out.println("seller_order_items");
			for (int i = 0; i < sellerIdList.size();i++) {
				String id = sellerIdList.get(i);
				int currentOrderId = sellerOrderMap.get(id);

				for (int j = 0; j < sellerItemList.size(); j++) {
					String sellerId = sellerItemList.get(i).getSellerId();
					
					if (sellerIdList.get(i).equals(sellerId)) {
						pstmtOrder.clearParameters();
						pstmtOrder = conn.prepareStatement(SQL_INSERT_SELLER_ORDER_ITEMS);
						pstmtOrder.setInt(1, 
								Integer.parseInt(sellerItemList.get(j).getProductId()));
						pstmtOrder.setInt(2, sellerItemList.get(j).getPrice());
						pstmtOrder.setInt(3, sellerItemList.get(j).getQuantity());
						pstmtOrder.setInt(4, currentOrderId);
						
						System.out.println(pstmtOrder.toString());
						pstmtOrder.executeUpdate();
					}
				}
			}

			
			// execute update
			conn.commit();
			
		
		
		
		
		
		// close all connection object
			pstmtProduct.close();
			pstmtOrder.close();
			conn.close();
		// successfully complete this transaction, set result = true
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
			if (pstmtProduct != null) {
				try {
					pstmtProduct.close();
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
