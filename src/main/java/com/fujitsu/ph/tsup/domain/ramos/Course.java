package com.fujitsu.ph.tsup.domain.ramos;

public class Course {
	private Long id;
	private String courseName;

	protected Course() {
	}

	private Course(Builder builder) {
		this.id = builder.id;
		this.courseName = builder.courseName;
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

		public Builder(Long id, String courseName) {
			validateId(id);
			this.id = id;
			validateCourseName(courseName);
			this.courseName = courseName;
		}

		public Course build() {
			return new Course(this);
		}
		
		private void validateId(Long id) {
			if (id == null) {
				throw new IllegalArgumentException("Id is empty");
			}
		}

		private void validateCourseName(String courseName) {
			if (courseName == null || courseName.isEmpty() || courseName.toUpperCase().length() < 10 || courseName.length() > 50) {
				throw new IllegalArgumentException("Course Name should not be empty");
			}
		}
	}
}
