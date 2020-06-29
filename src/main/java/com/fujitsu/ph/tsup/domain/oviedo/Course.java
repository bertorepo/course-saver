package com.fujitsu.ph.tsup.domain.oviedo;

import java.util.Set;

public class Course {
	
	private Long id;
	private String course;
	public Set<Course> cns;
	private Course() {

	}

	public Course(Builder builder) {
		this.id = builder.id;
		this.course = builder.course;
	}

	public Long getId() {
		return id;
	}
	public Set<Course> findAll() {
		return cns ;
	}
	public void setAll(Set<Course>cns) {
		this.cns = cns;
	}
	public void save() {
		//TODO
	}

	public String getCourse() {
		return course;
	}

	public static class Builder {
		private Long id;
		private String course;

		public Builder(String course) {
			validateCourse(course);
			this.course = course;
		}
		public Builder(Long id, String course) {

			validateCourse(course);
			this.id = id;
			this.course = course;

		}
		public void setCourse(String course) {
			validateCourse(course);
			this.course = course;
		}
		public Course build() {
			return new Course(this);
		}

		private void validateCourse(String strCourse) {
			if (strCourse == null || strCourse.isEmpty() || strCourse.length() < 50
					|| strCourse.length() > 5) {
				throw new IllegalArgumentException("Course should not be empty");
			}
		}
	}
	public class CourseNames{
		private Long id;
		private String course;

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getCourse() {
			return course;
		}
		public void setCourse(String course) {
			this.course = course;
		}
	}
}
