package com.douzone.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest {

	public static void main(String[] args) {
		
		System.out.println(update("안대혁","m","Fluffy"));

	}
	public static boolean update(String owner,String gender,String name)
	{
		Connection con =null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		boolean result=false;
		try {
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			//2 연결
			String url = "jdbc:mysql://localhost:3306/webdb?useSSL=false";
			 con = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. SQL문 준비
			 String sql="update pet set owner=?,gender=? where name = ?";
			 
			pstmt =con.prepareStatement(sql);
			
			//4. binding
			pstmt.setString(1, owner);
			pstmt.setString(2, gender);
			pstmt.setString(3, name);
			
			int count= pstmt.executeUpdate();
			result = count==1;
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("sql exception:"+e);
		} 
		
		catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+e);
		}
		finally
		{
		
				try {
					if(con!=null)
					con.close();
					if(stmt!=null)
						stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}
		return result;
		
		
		
		
	}

}
