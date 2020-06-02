package com.fujitsu.ph.tsup.course.management.model;

public class CourseCreate {
	private int id;
	private String name;
	
	
	public int getId() {
		return id;
	}
	
	public String getCousrseName() {
		return name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "COURSE [ID = " + id + ", Course Name = " + name + "]";
	}
	
	
	

}
