package com.fujitsu.ph.tsup.domain.oviedo;

public class CourseSchedule {
	private Long id, CourseId,VenueId,InstructorId;
	int minReq,maxAllowed;
	char status;

	protected CourseSchedule() {

	}

	private CourseSchedule(Builder builder) {
		this.id = builder.id;
		this.CourseId = builder.CourseId;
		this.VenueId = builder.VenueId;
		this.InstructorId = builder.InstructorId;
		this.minReq = builder.minReq;
		this.maxAllowed = builder.maxAllowed;
		this.status = builder.status;
	}

	public Long getId() {
		return id;
	}

	public Long getCourseId() {
		return CourseId;
	}

	public Long getVenueId() {
		return VenueId;
	}

	public Long getInstructorId() {
		return InstructorId;
	}

	public int getMinReq() {
		return minReq;
	}

	public int getMaxAllowed() {
		return maxAllowed;
	}

	public char getStatus() {
		return status;
	}



	public static class Builder {
		private Long id, CourseId,VenueId,InstructorId;
		int minReq,maxAllowed;
		char status;
		public Builder(Long courseId, Long instructorId, Long venueId, int minreq, int maxallowed, char status ) {
			this.CourseId = courseId;
			this.InstructorId = instructorId;
			this.VenueId = venueId;
			this.minReq = minreq;
			this.maxAllowed = maxallowed;
			this.status = status;
		}
		public Builder(Long id, Long courseId, Long instructorId, Long venueId, int minreq, int maxallowed, char status ) {
			this.id = id;
			this.CourseId = courseId;
			this.InstructorId = instructorId;
			this.VenueId = venueId;
			this.minReq = minreq;
			this.maxAllowed = maxallowed;
			this.status = status;
		}

		public CourseSchedule build() {
			return new CourseSchedule(this);
		}

	}
}
