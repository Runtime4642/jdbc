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
import com.douzone.jdbc.bookshop.vo.BookVo;

public class BookDao {
	
	
	public List<BookVo> getList()
	{
	List<BookVo> list = new ArrayList<BookVo>();
		
		Connection con =null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			 con = getConnection();		
			//3. Statment 객체를 생성
			stmt = con.createStatement();
			//4 sql 문 실행
			String sql = "select a.no,a.title,a.status,b.no,b.name from book a, author b where a.author_no =b.no";
			rs = stmt.executeQuery(sql);
			
			//5 결과 가져오기
			while(rs.next())
			{
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String status = rs.getString(3);
				Long authorNo = rs.getLong(4);
				String authorName = rs.getString(5);
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setStatus(status);
				vo.setTitle(title);
				vo.setAuthorName(authorName);
				vo.setAuthorNo(authorNo);
				
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

	public boolean insert(BookVo bookVo)
	{
		Statement stmt = null;
		PreparedStatement pstmt =null;
		Connection con =null;
		boolean result=false;
		try {

			 con = getConnection();
			
			 
			 String sql = "insert into book values(null,?,'대여가능',?)";
			 pstmt = con.prepareStatement(sql);
			 pstmt.setString(1,bookVo.getTitle());
			 pstmt.setLong(2,bookVo.getAuthorNo()); 
			 
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
	
	public boolean updateStatus(long no,String state)
	{
		Connection con =null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		boolean result=false;
		try {
		
			 con = getConnection();
			
			//3. SQL문 준비
			 String sql="select status from book where no="+no;
			 stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			 while(rs.next())
			 {
				 String status = rs.getString(1);
				 if(status.equals("대여중"))
				 {
					 System.out.println("해당 책은 이미 대여 중입니다.");
					 return false;
				 }
				 else
					 break;
			 }
			 
			 sql="update book set status=? where no=?";
			 
			pstmt =con.prepareStatement(sql);
			
			//4. binding
			pstmt.setString(1, state);
			pstmt.setLong(2, no);
			
			int count= pstmt.executeUpdate();
			result = count==1;
			
			
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
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}
		return result;
	
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
