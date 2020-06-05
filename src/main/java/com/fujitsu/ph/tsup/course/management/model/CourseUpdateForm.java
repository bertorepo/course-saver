//@Author Thomas Oviedo
package com.fujitsu.ph.tsup.course.management.model;

import javax.validation.constraints.Size;

public class CourseUpdateForm {
	private int id;
	@Size(min = 5)
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

	public String getName() {
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
		return "COURSE [ID = " + id + ", CourseName = " + name + "]";
	}
}
