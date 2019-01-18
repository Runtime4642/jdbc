package com.douzone.jdbc.hr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.douzone.jdbc.hr.dao.EmployeeDao;
import com.douzone.jdbc.hr.vo.EmployeeVo;

public class MainApp {

	public static void main(String[] args) {
		System.out.println("사원의 이름을 입력하세요: ");
		Scanner sc = new Scanner(System.in);
		
		search(sc.nextLine());
		
		sc.close();

	}
	public static void search(String name)
	{
		List<EmployeeVo> list = new ArrayList<EmployeeVo>();
		list =new EmployeeDao().search(name);
		for(EmployeeVo vo:list)
		{
			System.out.println("사원번호:"+vo.getNo()+",이름(성):"+vo.getFisrt_name()+", 입사일:"+vo.getHire_date());
		}
	}

}
