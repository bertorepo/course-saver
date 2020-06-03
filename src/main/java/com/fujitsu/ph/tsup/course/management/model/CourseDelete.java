package com.fujitsu.ph.tsup.course.management.model;

import javax.validation.constraints.NotBlank;

/* Created by: @Author Freo, Kristine Faith */

public class CourseDelete {
	private int courseId;
	private String courseName;
	@NotBlank
	(message="Field must not be empty")
	private String search;
	
	public CourseDelete() {	
	}
	
	public CourseDelete(int Id, String courseName, String search) {
		this.courseId = Id;
		this.courseName = courseName;
		this.search = search;
		
	}
		public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
		@Override
		public String toString() {
			return "courseDelete[CourseName=" + courseName + ", CourseId=" + courseId +", Search=" + search + "]";	
	}
}

