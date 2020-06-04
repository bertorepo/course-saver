package com.fujitsu.ph.tsup.domain.macabudbud;

public class CourseSchedule {
	private Long id;
	private Long courseId;
	private Long instructorId;
	private Long venueId;
	private int minRequired;
	private int maxAllowed;
	private char status;
	
	protected CourseSchedule() {
	}
	
	private CourseSchedule(Builder builder) {
		this.id = builder.id;
		this.courseId = builder.courseId;
		this.instructorId = builder.instructorId;
		this.venueId = builder.venueId;
		this.minRequired = builder.minRequired;
		this.maxAllowed = builder.maxAllowed;
		this.status = builder.status;
	}
	
	public static class Builder {
		private Long id;
		private Long courseId;
		private Long instructorId;
		private Long venueId;
		private int minRequired;
		private int maxAllowed;
		private char status;
		
		public Builder(Long courseId, Long instructorId, Long venueId, int minRequired, int maxAllowed, char status) {
			validateCourseId(courseId);
			validateInstructorId(instructorId);
			validateVenueId(venueId);
			validateMinRequired(minRequired);
			validateMaxAllowed(maxAllowed);
			validateStatus(status);
			
			this.courseId = courseId;
			this.instructorId = instructorId;
			this.venueId = venueId;
			this.minRequired = minRequired;
			this.maxAllowed = maxAllowed;
			this.status = status;
		}
		
        public CourseSchedule build() {
            return new CourseSchedule(this);
        }

		public void validateCourseId(Long courseId) {
			if(courseId == null) {
				throw new IllegalArgumentException("Course ID should not be null");
			}	
		}
		public void validateInstructorId(Long instructorId) {
			if(instructorId == null) {
				throw new IllegalArgumentException("Instructor ID should not be null");
			}	
		}
		public void validateVenueId(Long venueId) {
			if(venueId == null) {
				throw new IllegalArgumentException("Venue ID should not be null");
			}	
		}
		public void validateMinRequired(int minRequired) {
			if(String.valueOf(minRequired).length() <= 0 || String.valueOf(minRequired).isEmpty()) {
				throw new IllegalArgumentException("Minimum required number of participants is required");
			}else if(String.valueOf(minRequired).length() > 5) {
				throw new IllegalArgumentException("Minimum required number of participants exceeds the limit.");
			}
		}
		public void validateMaxAllowed(int maxRequired) {
			if(String.valueOf(maxRequired).length() <= 0 || String.valueOf(maxRequired).isEmpty()) {
				throw new IllegalArgumentException("Maximun number allowed of participants is required");
			}else if(String.valueOf(maxRequired).length() > 5) {
				throw new IllegalArgumentException("Minimum number allowed of participants exceeds the limit.");
			}else if(maxRequired < this.minRequired) {
				throw new IllegalArgumentException("Minimum number allowed of participants must not be less than the minimum required.");
			}
		}
		public void validateStatus(char status) {
			if (!Character.isLetter(status)) {
                throw new IllegalArgumentException("Invalid input. Please enter 'A' or 'C'");
            } else if (Character.toUpperCase(status) != 'A' || Character.toUpperCase(status) != 'C') {
                throw new IllegalArgumentException("Invalid status. Please enter 'A' or 'C'");
            }
		}	
	}
	
	public Long getId() {
		return id;
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
	public char getStatus() {
		return status;
	}
	
}
