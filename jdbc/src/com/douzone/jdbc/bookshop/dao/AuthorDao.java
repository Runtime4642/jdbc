package com.douzone.jdbc.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzone.jdbc.bookshop.vo.AuthorVo;

public class AuthorDao {
	
		
	public boolean insert(AuthorVo authorVo)
	{
		Statement stmt = null;
		PreparedStatement pstmt =null;
		Connection con =null;
		boolean result=false;
		try {

			 con = getConnection();
			
			 
			 String sql = "insert into author values(null,?,?)";
			 pstmt = con.prepareStatement(sql);
			 pstmt.setString(1,authorVo.getName());
			 pstmt.setString(2,authorVo.getBio());
			 
			 int count = pstmt.executeUpdate();
			result = count ==1;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("sql exception:"+e);
		} 
		finally
		{
		
				try {
					if(con!=null)
					con.close();
					if(stmt!=null)
						stmt.close();
					if(pstmt!=null)
						pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}
		return result;
	}

	public List<AuthorVo> getList(){
		List<AuthorVo> list = new ArrayList<AuthorVo>();
		
		Connection con =null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			 con = getConnection();		
			//3. Statment 객체를 생성
			stmt = con.createStatement();
			//4 sql 문 실행
			String sql = "select * from author";
			rs = stmt.executeQuery(sql);
			
			//5 결과 가져오기
			while(rs.next())
			{
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String bio = rs.getString(3);
				
				AuthorVo vo = new AuthorVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setBio(bio);
				
				list.add(vo);
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
		
		return list;
	}

	
	private Connection getConnection() throws SQLException  
	{
		Connection con =null;
		try {
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			//2 연결
			String url = "jdbc:mysql://localhost:3306/webdb?useSSL=false";
			 con = DriverManager.getConnection(url, "webdb", "webdb");
		}
		catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+e);
		}
		return con;
	}
}
