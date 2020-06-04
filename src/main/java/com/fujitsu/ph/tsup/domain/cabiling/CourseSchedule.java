package com.fujitsu.ph.tsup.domain.cabiling;

public class CourseSchedule {

	private Long id;
	private Long courseId;
	private Long instructorId;
	private Long venueId;
	private Integer minRequired;
	private Integer maxAllowed;
	private char sts;

	protected CourseSchedule() {
	}

	private CourseSchedule(Builder cSched) {
		this.id = cSched.id;
		this.courseId = cSched.courseId;
		this.instructorId = cSched.instructorId;
		this.venueId = cSched.venueId;
		this.minRequired = cSched.minRequired;
		this.maxAllowed = cSched.maxAllowed;
		this.sts = cSched.sts;

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

	public Integer getMinRequired() {
		return minRequired;
	}

	public Integer getMaxAllowed() {
		return maxAllowed;
	}

	public char getSts() {
		return sts;
	}

	public static class Builder {
		private Long id;
		private Long courseId;
		private Long instructorId;
		private Long venueId;
		private Integer minRequired;
		private Integer maxAllowed;
		private char sts;

		public Builder(Long id, Long courseId, Long instructorId, Long venueId, Integer minRequired, Integer maxAllowed,
				char sts) {
			validateId(id);
			validateCourseId(courseId);
			validateInstructorId(instructorId);
			validateVenueId(venueId);
			validateMinRequired(minRequired);
			validateMaxAllowed(maxAllowed);
			validateStatus(sts);

			this.id = id;
			this.courseId = courseId;
			this.instructorId = instructorId;
			this.venueId = venueId;
			this.minRequired = minRequired;
			this.maxAllowed = maxAllowed;
			this.sts = sts;
		}

		public CourseSchedule cSched() {
			return new CourseSchedule(this);
		}

		private void validateId(Long id) {
			if (id == null) {
				throw new IllegalArgumentException("ID should not be null");
			}
		}

		private void validateCourseId(Long courseId) {
			if (courseId == null) {
				throw new IllegalArgumentException("Course ID should not be null");
			}
		}

		private void validateInstructorId(Long instructorId) {
			if (instructorId == null) {
				throw new IllegalArgumentException("Insructor ID should not be null");
			}
		}

		private void validateVenueId(Long venueId) {
			if (venueId == null) {
				throw new IllegalArgumentException("Venue ID should not be null");
			}
		}

		private void validateMinRequired(Integer minRequired) {
			if (minRequired <= 0 || minRequired > 99999 || minRequired == null) {
				throw new IllegalArgumentException(
						"Min Required should not be less than or equal to zero, greater than 99999 or null");
			}
		}

		private void validateMaxAllowed(Integer maxAllowed) {
			if (maxAllowed <= 0 || maxAllowed > 99999 || maxAllowed == null) {
				throw new IllegalArgumentException(
						"Max Allowed should not be less than or equal to zero, greater than 99999 or null");
			}
		}

		private void validateStatus(char sts) {
			if (sts == '\u0000' || Character.isWhitespace(sts)) {
				throw new IllegalArgumentException("Status should not be null or should not only consist white space");
			}
		}

	}

}
