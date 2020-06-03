package com.fujitsu.ph.tsup.course.management.model;

import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class CourseViewForm {
	@PositiveOrZero
	private String courseid;
	@NotBlank
	private String coursename;
	private String submit;

	public String getCourseid() {
		return courseid;
	}
	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	@Override
	public String toString() {
		return "CoursesForm1 [courseid=" + courseid + ",coursename= "+ coursename + ",submit="
				+ submit + "]";
	}

}

