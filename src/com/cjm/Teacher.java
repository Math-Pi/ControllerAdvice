package com.cjm;

public class Teacher {
	private String name;
	private String tno;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	@Override
	public String toString() {
		return "Teacher [name=" + name + ", tno=" + tno + "]";
	}
	
}
