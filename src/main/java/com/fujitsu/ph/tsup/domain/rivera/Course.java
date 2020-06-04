package com.fujitsu.ph.tsup.domain.rivera;

public class Course {
	private Long courseId;
	private String courseName;
	
	protected Course() {
		
	}
	
	private Course(Builder builder) {
		this.courseId = builder.courseId;
		this.courseName = builder.courseName;
	}
	
	public Long getCourseId() {
		return courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public static class Builder{
		private Long courseId;
		private String courseName;
		
		public Builder(String courseName) {
			validateCourseName(courseName);
			this.courseName = courseName;
		}
		
		public Course build() {
			return new Course(this);
		}
		
		private void validateCourseName(String courseName) {
			if(courseName == null || courseName.isEmpty() || courseName.length() < 5 || courseName.length() > 50) {
				throw new IllegalArgumentException("course name should NOT be null, characters should NOT be less than 5 and NOT greater than 50.");
			}
		}
	}
}
