package m12;

import java.sql.*;
import java.util.ArrayList;
import java.util.jar.Attributes.Name;



public class First {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3307/world?useSSL=false&serverTimezone=CST";
	static final String USER="root";
	static final String PASS="bdse1311";
	

	public static void main(String[] args) {
		
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			conn.setAutoCommit(false);
			System.out.println("Creating statement");
			stmt=conn.createStatement();
//			String sql;
			stmt.execute("update city set population = 0 where name='Kabul'");
			stmt.execute("insert into hello value('hello')");
			stmt.executeQuery("SELECT * from city where name='Kabul'");
			conn.commit();
			pstmt=conn.prepareStatement("SELECT * from city where name=?");
			pstmt.setString(1, "Kabul");

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
				ArrayList cityaArrayList=new ArrayList<>();
				cityaArrayList.add(myCity);
				
				System.out.println("id: "+myCity.getId()+", name: "+myCity.getName()+", countryCode: "+myCity.getCountrycode()+", district: "+myCity.getDistrict()+", population: "+myCity.getPopulation());
			}
			
			pstmt.close();
			rs.close();
			stmt.close();
			conn.close();
		}catch (SQLException se) {
			try {
				conn.rollback();
			}catch (SQLException e3) {
				e3.printStackTrace();
			}
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
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
			System.out.println("Goodbye!");
		}
		
			
	}

}
