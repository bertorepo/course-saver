package com.fujitsu.ph.tsup.course.management.model;

public class CourseDelete {
	private int courseId;
	private String courseName;
	
	public CourseDelete() {	
	}
	
	public CourseDelete(int Id, String courseName, String Delete) {
		this.courseId = Id;
		this.courseName = courseName;
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

		@Override
		public String toString() {
			return "courseDelete[CourseName=" + courseName + ", CourseId=" + courseId + "]";
		
	}
}

