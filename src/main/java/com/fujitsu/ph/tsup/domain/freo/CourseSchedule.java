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
	  this.status= builder.status;
	    
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
		
		public Builder (Long Id, Long courseId, Long instructorId, Long venueId, int minRequired, int maxAllowed, String status) {
			validateId(Id);
			validatecourseId(courseId);
			validateinstructorId(instructorId);
			validatevenueId(venueId);
			validateminRequired(minRequired);
			validatemaxAllowed(maxAllowed);
			validatestatus(status);
			
			this.Id = Id;
			this.courseId = courseId;
			this.instructorId = instructorId;
			this.venueId = venueId;
			this.minRequired = minRequired;
			this.maxAllowed = maxAllowed;
			this.status = status;
		}
		

		public Builder( Long Id, long courseId, long instructorId, long venueId, int minRequired, int maxAllowed, String status) {
			validateId(Id);
			validatecourseId(courseId);
			validateinstructorId(instructorId);
			validatevenueId(venueId);
			validateminRequired(minRequired);
			validatemaxAllowed(maxAllowed);
			validatestatus(status);
		
			this.Id=Id;
			this.courseId = courseId;
			this.instructorId = instructorId;
			this.venueId = venueId;
			this.minRequired = minRequired;
			this.maxAllowed = maxAllowed;
			this.status = status;

		}

		public Builder(long id, long courseId1, long instructorId1, long venueId1, long Id1, int maxAllowed1, int minRequired1, String Status ) {
			validateId(id);
			validatecourseId(courseId1);
			validateinstructorId(instructorId1);
			validatevenueId(venueId1);
			validateminRequired(minRequired1);
			validatemaxAllowed(maxAllowed1);
			validatestatus(status);
			
			this.Id = Id1;
			this.courseId = courseId1;
			this.instructorId = instructorId1;
			this.venueId = venueId1;
			this.minRequired = minRequired1;
			this.maxAllowed = maxAllowed1;
			this.status = Status;
		}

		public CourseSchedule builder() {
			 return new CourseSchedule(this);
		 }
		
		private void validateId(Long id) {
			 if (id == null) {
	                throw new IllegalArgumentException("ID should not be null");
	            }
			
		}

		private void validatecourseId(Long courseId) {
			if (courseId == null) {
                throw new IllegalArgumentException("Course ID should not be null");
            }
         }
		private void validateinstructorId(Long instructorId) {
			if ( instructorId == null) {
                throw new IllegalArgumentException(" Instructor ID should not be null");
            }
			
		}
		private void validatevenueId(Long venueId) {
			if (venueId == null) {
                throw new IllegalArgumentException("ID should not be null");
            }
        }
		private void validateminRequired(int minRequired) {
			if (minRequired < 5 ) {
				throw new IllegalArgumentException("Minimum number of Participants Required should not be empty");
			} else if(minRequired > 99999) {
                throw new IllegalArgumentException("Minimum number of Participants Required should not exceed 99999");
            }  else if (String.valueOf(minRequired).equals(null)) {
                throw new NullPointerException("Min Required should not be null.");
            }
		  }
		}
		private static void validatemaxAllowed (int maxAllowed ) {
			if (maxAllowed < 5 ) {
                throw new IllegalArgumentException("Maximum Number of Participants should not be empty");
			 } else if(maxAllowed > 99999) {
                throw new IllegalArgumentException("Max Allowed should not exceed 99999");
            } else if (String.valueOf(maxAllowed).equals(null)) {
                throw new IllegalArgumentException("Max Allowed should not be null.");
            }
		}
		private static  void validatestatus(String status) {
			if (status.isEmpty()) {
	            throw new IllegalArgumentException("Status must not be empty field");
			 } else if(status.length() > 1) {
	             throw new IllegalArgumentException("The status should be 1 characters");
	        }	
  }
}	
