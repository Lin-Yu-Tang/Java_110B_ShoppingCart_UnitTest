package m12;
import java.sql.*;

public class lab2222 {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/world?useSSL=false&serverTimezone=CST";
	static final String USER="root";
	static final String PASS="";
	

	public static void main(String[] args) {
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("transaction");
			Savepoint savepoint = conn.setSavepoint();
			try {
				conn.setAutoCommit(false);
				stmt=conn.createStatement();
				stmt.execute("update city set population =0 where name = 'Kabul'");
				savepoint = conn.setSavepoint();
				stmt.execute("update city set population =0 where name = 'Qandahar'");
				stmt.execute("insert into hello value('hello')");
				conn.commit();
			} catch (SQLException e) {
				try {
					conn.rollback(savepoint);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}finally {
					conn.commit();
				}
				e.printStackTrace();
			}
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id,name,countrycode,district,population FROM city limit 10";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String counCode = rs.getString("countryCode");
				String district = rs.getString("district");
				int population = rs.getInt("population");
				System.out.println(id);
				System.out.println(name+counCode+district+population);
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt != null)
					stmt.close();
			}catch (SQLException se2) {
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
	}

}
