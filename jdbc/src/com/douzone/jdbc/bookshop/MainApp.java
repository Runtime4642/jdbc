package com.douzone.jdbc.bookshop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.douzone.jdbc.bookshop.dao.BookDao;
import com.douzone.jdbc.bookshop.vo.BookVo;

public class MainApp {
	public static void main(String[] args) {
		

		displayBookInfo();	
		Scanner scanner = new Scanner(System.in);
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
		int num = scanner.nextInt();
		scanner.close();
		rent(num);
		displayBookInfo();

	}
	public static void rent(long no)
	{
		new BookDao().updateStatus(no,"대여중");
	}
	
	public static void displayBookInfo() {
		List<BookVo> list = new ArrayList<BookVo>();
		System.out.println("*****도서 정보 출력하기******");
		list = new BookDao().getList();
		
		for(BookVo vo : list)
		{
			System.out.println("책 번호:"+vo.getNo()+", 책 제목:"+vo.getTitle()+", 저자:"+vo.getAuthorName()+", 대여 유무:"+vo.getStatus());
		}
		
		
	}

}
