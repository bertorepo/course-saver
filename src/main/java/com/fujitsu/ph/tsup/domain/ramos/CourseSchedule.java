package com.fujitsu.ph.tsup.domain.ramos;

public class CourseSchedule {
	private Long id;
	private Long courseId;
	private Long instructorId;
	private Long venueId;
	private int minRequired;
	private int maxAllowed;
	private String status;

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

	public String getStatus() {
		return status;
	}

	public static class Builder {
		private Long id;
		private Long courseId;
		private Long instructorId;
		private Long venueId;
		private int minRequired;
		private int maxAllowed;
		private String status;

		public Builder(Long courseId, Long instructorId, Long venueId, int minRequired, int maxAllowed, String status) {
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

		private void validateCourseId(Long courseId) {
			if (courseId == null) {
				throw new IllegalArgumentException("Course Id is empty");
			}
		}

		private void validateInstructorId(Long instructorId) {
			if (instructorId == null) {
				throw new IllegalArgumentException("Instructor Id is empty");
			}
		}

		private void validateVenueId(Long venueId) {
			if (venueId == null) {
				throw new IllegalArgumentException("Venue Id is empty");
			}
		}

		private void validateMinRequired(int minRequired) {
			if (minRequired < 15 || minRequired <= 0) {
				throw new IllegalArgumentException("Minimum number of participants should be atleast 15");
			}
		}

		private void validateMaxAllowed(int maxAllowed) {
			if (maxAllowed > 15 || maxAllowed >= 51) {
				throw new IllegalArgumentException("Maximum number of participants should not exceed 50");
			}
		}

		private void validateStatus(String status) {
			if (status == null || status.isEmpty()) {
				throw new IllegalArgumentException("Status is empty");
			}
		}
	}
}
