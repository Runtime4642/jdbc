package com.douzone.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest {

	public static void main(String[] args) {
	
		boolean result =insert("마음이3","또치","dog","f","2010-10-10");
		System.out.println(result);
	}
	public static boolean insert(String name,String owner,String species,String gender,String birth)
	{
		Connection con =null;
		Statement stmt = null;
		boolean result=false;
		try {
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			//2 연결
			String url = "jdbc:mysql://localhost:3306/webdb?useSSL=false";
			 con = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. Statment 객체를 생성
			stmt = con.createStatement();

			//4 sql 문 실행
			String sql ="insert into pet values('"+name+"','"+owner+"','"+species+"','"+gender+"','"+birth+"',null)";
			int count= stmt.executeUpdate(sql);
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
