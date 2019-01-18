package com.douzone.jdbc.hr.vo;

import java.sql.Date;

public class EmployeeVo {
	private Long no;
	private Date birth_date;
	private String fisrt_name;
	private String last_name;
	private String gender;
	private Date hire_date;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	public String getFisrt_name() {
		return fisrt_name;
	}
	public void setFisrt_name(String fisrt_name) {
		this.fisrt_name = fisrt_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getHire_date() {
		return hire_date;
	}
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}
	@Override
	public String toString() {
		return "EmployeeVo [no=" + no + ", birth_date=" + birth_date + ", fisrt_name=" + fisrt_name + ", last_name="
				+ last_name + ", gender=" + gender + ", hire_date=" + hire_date + "]";
	}
	
	

}
