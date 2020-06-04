package com.fujitsu.ph.tsup.domain.freo;

public class Course {
	
	private Long Id;
	private String courseName;

	protected Course() {
		
	}
	private Course (Builder builder) {
		this.Id=builder.Id;
		this.courseName=builder.courseName;	
	}
	
	public Long getId() {
		return Id;
	}
	public String getCourseName() {
		return courseName;
	}
	
	public static class Builder {
		
		private Long Id;
		private String courseName;
		
	
	public Builder (Long Id, String courseName) {
		validateId(Id);
		validatecourseName(courseName);
		
		this.Id = Id;
		this.courseName = courseName;	
	}
	
	public Course builder() {
		return new Course(this);
	}

	private void validateId(Long Id) {
		if (Id== null) {
			throw new IllegalArgumentException("ID Should not be empty");
		}
	}
	private void validatecourseName(String courseName) {
		if (courseName.isEmpty() || courseName== null) {
			throw new IllegalArgumentException("Course Name Should not be empty");
			
		 }
	 }
  }
}

	
	