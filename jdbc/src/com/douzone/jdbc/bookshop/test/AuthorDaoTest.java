package com.douzone.jdbc.bookshop.test;

import java.util.ArrayList;
import java.util.List;

import com.douzone.jdbc.bookshop.dao.AuthorDao;
import com.douzone.jdbc.bookshop.dao.BookDao;
import com.douzone.jdbc.bookshop.vo.AuthorVo;
import com.douzone.jdbc.bookshop.vo.BookVo;

public class AuthorDaoTest {

	public static void main(String[] args) {
		
		insertTest("ㅎㅇㅎㅇ");
		getListTest();
		
		
	}
	public static void insertTest(String name)
	{
		AuthorVo vo = new AuthorVo();
		vo.setName(name);
		vo.setBio("");
		System.out.println(new AuthorDao().insert(vo));
	}
	public static void getListTest() {
		List<AuthorVo> list = new ArrayList<AuthorVo>();
		list = new AuthorDao().getList();
		for(AuthorVo vo : list)
		{
			System.out.println(vo);
		}
	}

	
}
