package com.douzone.jdbc.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDriverTest {

	public static void main(String[] args) {
		Connection con =null;

		try {
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.douzone.jdbc.driver.MyDriver");
			
			//2 연결
			String url = "jdbc:mysql://localhost:3306/webdb?useSSL=false";
			 con = DriverManager.getConnection(url, null); //mydriver 의 Connection connect 실행
			
			 
			
			
			
		}
		catch (SQLException e) {
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
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}

	}

}
