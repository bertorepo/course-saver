package com.fujitsu.ph.tsup.domain.freo;

public class Course {

	private Long id;
	private String courseName;
	
	protected Course() {
		
	}
	private Course (Builder builder) {
		this.id = builder.id;
		this.courseName = builder.courseName;	
	}
	
	public  Long getId() {
		return id;
	}
	public String getCourseName() {
		return courseName;
	}
	
	public static class Builder {
		private Long id;
		private String courseName;
		
		public Builder (Long id, String courseName) {
			validateId(id);
			validateCourseName(courseName);
		
			this.id = id;
			this.courseName= courseName;
		}
		
		public Builder (String courseName) {
			validateCourseName(courseName);
			
			this.CourseName(courseName);
		}
	
		private void CourseName(String courseName2) {
			
		}

		public Course builder() {
		return new Course(this);
	}

	private void validateId(Long id) {
		if (id== null) {
			throw new IllegalArgumentException("ID Should not be empty");
		}
	}
	
	private void validateCourseName(String courseName) {
		if (courseName == null) {
			throw new IllegalArgumentException("The Course Name Should not be empty");
		 } else if (courseName.isEmpty()) {
             throw new IllegalArgumentException("The Course Name should not be empty");
         } else if(courseName.length() > 50) {
             throw new IllegalArgumentException("The Course Name should not be more than 50 Characters");
        }	 
	 }
	}


}	