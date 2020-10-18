package com.cjm;

public class Student {
	private String name;
	private String sno;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", sno=" + sno + "]";
	}
	
}
