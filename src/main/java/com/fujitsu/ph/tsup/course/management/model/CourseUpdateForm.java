package com.fujitsu.ph.tsup.course.management.model;

public class CourseUpdateForm {
	private int id;
	private String name;

	public CourseUpdateForm() {

	}
	public CourseUpdateForm(int Id, String Name) {
		this.id = Id;
		this.name = Name;
	}


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
