package com.fujitsu.ph.tsup.domain.freo;

public class CourseSchedule {
	
	private Long Id;
	private Long courseId;
	private Long instructorId;
	private Long venueId;
	private int minRequired; 
	private int maxAllowed; 
	private String status;
	
	protected CourseSchedule() {

	 	}
	   
	private CourseSchedule(Builder builder) {
	  this.Id = builder.Id;
	  this.courseId = builder.courseId;
	  this.instructorId = builder.instructorId;
	  this.venueId = builder.venueId;
	  this.minRequired = builder.minRequired;
	  this.maxAllowed= builder.maxAllowed;
	  this.status=builder.status;
	    
	}
	
	public Long getId() {
		return Id;
	}

	public Long getCourseId() {
		return courseId;
	}

	public Long getInstructorId() {
		return instructorId;
	}

	public Long getVenueId() {
		return venueId;
	}

	public int getMinRequired() {
		return minRequired;
	}

	public int getMaxAllowed() {
		return maxAllowed;
	}

	public  String getStatus() {
		return status;
	}
	
	public static class Builder {
		
		
		private Long Id;
		private Long courseId;
		private Long instructorId;
		private Long venueId;
		private int minRequired; 
		private int maxAllowed; 
		private String status;
		
		public Builder (Long id, Long courseId, Long instructorId, Long venueId, int minRequired, int maxRequired, int maxAllowed,String status) {
			validatecourseId(courseId);
			validateinstructorId(instructorId);
			validatevenueId(venueId);
			validateminRequired(minRequired);
			validatemaxAllowed(maxAllowed);
			validatestatus(status);
			
			this.courseId = courseId;
			this.instructorId = instructorId;
			this.venueId = venueId;
			this.minRequired = minRequired;
			this.maxAllowed = maxAllowed;
		}
		
		 

		public CourseSchedule builder() {
			 return new CourseSchedule(this);
		 }
		private void validatestatus(String status) {
			if (status == null) {
                throw new IllegalArgumentException("ID should not be null");
            }  else if(status.length() > 3 || status.length() < 1) {
                throw new IllegalArgumentException("Status should be 3 characters");
            } 
         }
			
		private void validatecourseId(Long courseId) {
			if (courseId == null) {
                throw new IllegalArgumentException("ID should not be null");
            }
         }
		private void validateinstructorId(Long instructorId) {
			if ( instructorId == null) {
                throw new IllegalArgumentException(" Instructor Id should not be null");
            }
			
		}
		private void validatevenueId(Long venueId) {
			if (venueId == null) {
                throw new IllegalArgumentException("ID should not be null");
            }
        }
		private void validateminRequired(int minRequired) {
			if (minRequired < 1 || minRequired > 99999) {
				throw new IllegalArgumentException("Minimum number of Participants Required should not be empty");
			} else if(minRequired > 99999) {
                throw new IllegalArgumentException("Minimum number of Participants Required should not exceed 99999");
            }
		}
		private void validatemaxAllowed (int maxAllowed ) {
			if (maxAllowed < 1 || maxAllowed > 99999) {
                throw new IllegalArgumentException("Maximum Number of Participants should not be empty");
			 } else if(maxAllowed > 99999) {
                throw new IllegalArgumentException("Max Allowed should not exceed 99999");
            }
		}
	}
}
