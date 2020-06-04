package com.fujitsu.ph.tsup.domain.macabudbud;

public class Course {
	private Long id;
	private String courseName;
	
	protected Course() {	
	}
	
	private Course(Builder builder) {
		this.id = builder.id;
		this.courseName = builder.courseName;
	}
	
	public static class Builder {
		private Long id;
		private String courseName;
		
		public Builder(Long id, String courseName) {
			validateCourseName(courseName);
			
			this.id = id;
			this.courseName = courseName;
		}
		
		public Course build() {
			return new Course(this);
		}
		
		public void validateCourseName(String courseName) {
			if(courseName == null || courseName.isEmpty()) {
				throw new IllegalArgumentException("Field required. Course name should notn be empty");
			}else if(courseName.length() > 100){
				throw new IllegalArgumentException("Characters exceeds to 100.");
			}
		}
	}
	
	public Long getId() {
		return id;
	}
	public String getCourseName() {
		return courseName;
	}
	
	
}
