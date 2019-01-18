package com.douzone.jdbc.hr.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzone.jdbc.hr.vo.EmployeeVo;

public class EmployeeDao {

	public List<EmployeeVo> search(String name)
	{
	List<EmployeeVo> list = new ArrayList<EmployeeVo>();
		
		Connection con =null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			 con = getConnection();		
			//3. Statment 객체를 생성
			stmt = con.createStatement();
			//4 sql 문 실행
			String sql = "select emp_no,first_name,hire_date from employees where first_name='"+name+"' or last_name='"+name+"'";
			rs = stmt.executeQuery(sql);
			
			//5 결과 가져오기
			while(rs.next())
			{
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				Date hireDate = rs.getDate(3);
				EmployeeVo vo = new EmployeeVo();
				vo.setNo(no);
				vo.setFisrt_name(firstName);
				vo.setHire_date(hireDate);			
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
			String url = "jdbc:mysql://localhost:3306/employees?useSSL=false";
			 con = DriverManager.getConnection(url, "hr", "hr");
		}
		catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+e);
		}
		return con;
	}
}
