package m12;

import java.sql.*;

import m12.City;

public class lba22 {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/world?useSSL=false&serverTimezone=CST";
	static final String USER="root";
	static final String PASS="";
	

	public static void main(String[] args) {
		
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			Savepoint savepoint = conn.setSavepoint();

			try {
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				stmt.execute("update city set poppulation = 0 where name='Kabul'");
				savepoint = conn.setSavepoint();
				stmt.execute("update city set population = 0 where name='Qandahar'");
				stmt.execute("insert into hello value('hello')");
			}catch (SQLException sqe) {
				try {
					conn.rollback(savepoint);
					
				} catch (SQLException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				sqe.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn.commit();
				System.out.println("execute savepoint");
			}

			ResultSet rs=pstmt.executeQuery();
//			String name = rs.getString("District");
//			int population = rs.getInt("population");
//			System.out.println(" , "+population);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String countryCode = rs.getString("countryCode");
				String district = rs.getString("district");
				int population = rs.getInt("population");
				City myCity=new City();
				myCity.setId(id);
				myCity.setName(name);
				myCity.setCountrycode(countryCode);
				myCity.setDistrict(district);
				myCity.setPopulation(population);
				
				System.out.println("id: "+myCity.getId()+", name: "+myCity.getName()+", countryCode: "+myCity.getCountrycode()+", district: "+myCity.getDistrict()+", population: "+myCity.getPopulation());
			}
			
			pstmt.close();
			rs.close();
			stmt.close();
			conn.close();
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null)
					stmt.close();
			}catch (SQLException se2) {
			}
			try {
			if(conn!=null)
				conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			System.out.println("Goodbye!");
		}
		
			
	}



}
