package com.fujitsu.ph.tsup.domain.cabiling;

public class Course {

	private Long id;
	private String courseName;

	protected Course() {

	}

	private Course(Builder course) {
		this.id = course.id;
		this.courseName = course.courseName;
	}

	public Long getId() {
		return id;
	}

	public String getCourseName() {
		return courseName;
	}

	public static class Builder {
		private Long id;
		private String courseName;

		public Builder(String courseName) {
			validateId(id);
			validateCourse(courseName);
			
			this.courseName = courseName;
		}

		public Course course() {
			return new Course(this);
		}

		private void validateId(Long id) {
			if (id == null) {
				throw new IllegalArgumentException("Id can not be null");
			}

		}

		private void validateCourse(String courseName) {
			if (courseName == null) {
				throw new IllegalArgumentException("Course Name can not be null");
			}

		}
	}
}
