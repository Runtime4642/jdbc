package com.douzone.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {

	public static void main(String[] args) {
		Connection con =null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			//2 연결
			String url = "jdbc:mysql://localhost:3306/webdb?useSSL=false";
			 con = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. Statment 객체를 생성
			stmt = con.createStatement();
			//4 sql 문 실행
			String sql = "select name,owner,birth from pet";
			rs = stmt.executeQuery(sql);
			
			//5 결과 가져오기
			while(rs.next())
			{
				String name = rs.getString(1);
				String owner = rs.getString(2);
				String birth = rs.getString(3);
				
				System.out.println(name+ " : " + owner +" : "+birth);
				
				//예시
//				int bId = resultSet.getInt("bId");
//				String bName = resultSet.getString("bName");
//				String bTitle = resultSet.getString("bTitle");
//				String bContent = resultSet.getString("bContent");
//				Timestamp bDate = resultSet.getTimestamp("bDate");
//				int bHit = resultSet.getInt("bHit");
//				int bGroup = resultSet.getInt("bGroup");
//				int bStep = resultSet.getInt("bStep");
//				int bIndent = resultSet.getInt("bIndent");
			}
			
			
			
			
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
					if(rs!=null)
						rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}
		
	}

}
