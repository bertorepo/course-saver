// Michael Rivera

package com.fujitsu.ph.tsup.course.management.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CourseCreate {
	private int id;
	
	@NotBlank(message = "name must not be blank")
	@NotNull
	private String name;
	private String submit;
	
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSubmit() {
		return submit;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	@Override
	public String toString() {
		return "COURSE [ID = " + id + ", Course Name = " + name + "Submit" + submit + "]";
	}
	
	
	

}
