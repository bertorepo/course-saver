package com.fujitsu.ph.tsup.enrollment.domain;

import java.time.ZonedDateTime;

import java.util.Set;

//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :CourseParticipant.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/23/2020 | WS) K.Freo        | New Creation
//0.01    | 07/06/2020 | WS) J.Macabugao   | Update
//0.01	  | 07/07/2020 | WS) T.Oviedo	   | Update
//==================================================================================================
/**
* <pre>
* Enrollment Model Use as Course Participant Builder Pattern <added
* description>
* 
* <pre>
* 
* @version 0.01
* @author K.Freo
*/

public class CourseParticipant {

	/** id **/
	private Long id;

	/** courseScheduleId **/
	private Long courseScheduleId;

	/** courseName **/
	private String courseName;

	/** instructorName **/
	private String instructorName;

	/** venueName **/
	private String venueName;

	/** participantId **/
	private Long participantId;

	/** participantName **/
	private String participantName;

	/** courseScheduleDetails **/
	private Set<CourseScheduleDetail> courseScheduleDetail;

	/** registrationDate **/
	private ZonedDateTime registrationDate;

	/** reason **/
	private String reason;

	/** declineDate **/
	private ZonedDateTime declineDate;

	protected CourseParticipant() {

	}

	private CourseParticipant(Builder builder) {
		this.id = builder.id;
		this.courseScheduleId = builder.courseScheduleId;
		this.courseName = builder.courseName;
		this.instructorName = builder.instructorName;
		this.venueName = builder.venueName;
		this.participantId = builder.participantId;
		this.participantName = builder.participantName;
		this.courseScheduleDetail = builder.courseScheduleDetail;
		this.registrationDate = builder.registrationDate;
		this.reason = builder.reason;
		this.declineDate = builder.declineDate;

	}

	public Long getId() {
		return id;
	}

	public Long getCourseScheduleId() {
		return courseScheduleId;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public String getVenueName() {
		return venueName;
	}

	public Long getParticipantId() {
		return participantId;
	}

	public String getParticipantName() {
		return participantName;
	}

	public Set<CourseScheduleDetail> getCourseScheduleDetail() {
		return courseScheduleDetail;
	}

	public ZonedDateTime getRegistrationDate() {
		return registrationDate;
	}

	public String getReason() {
		return reason;
	}

	public ZonedDateTime getDeclineDate() {
		return declineDate;
	}

	/**
	 * <pre>
	 * The builder class of the course participant The builder is a public static
	 * member class of
	 * 
	 * <pre>
	 * 
	 * @author K.Freo
	 *
	 */

	public static class Builder {

		/** id **/
		private Long id;

		/** from the Course Schedule id **/
		private Long courseScheduleId;

		/** Course Name **/
		private String courseName;

		/** Instructor Name (LASTNAME, FIRSTNAME) **/
		private String instructorName;

		/** Venue Name **/
		private String venueName;

		/** from the Employee Id **/
		private Long participantId;

		/** Participant Name(LASTNAME, FIRSTNAME) **/
		private String participantName;

		/** The course schedule detail **/
		private Set<CourseScheduleDetail> courseScheduleDetail;

		/** Registration Date **/
		private ZonedDateTime registrationDate;

		/** Reason **/
		private String reason;

		/** Decline Date **/
		private ZonedDateTime declineDate;

		/**
		 * <pre>
		 * Creates a new instance of Builder for creating a participants. It validates
		 * and sets the argument into the Builder instance variables. This method is
		 * used for creating a course participant.
		 * 
		 * <pre>
		 * 
		 * @param courseScheduleId
		 * @param participantId
		 */

		public Builder(Long courseScheduleId, Long participantId) {

			validateCourseScheduleId(courseScheduleId);
			validateParticipantId(participantId);

			this.registrationDate = ZonedDateTime.now();
			
			this.courseScheduleId = courseScheduleId;
			this.participantId = participantId;
		}

		/**
		 * <pre>
		 * Creates a new instance of Builder for creating a course participant. It
		 * validates and sets the argument into the Builder instance variables. This
		 * method is used for creating a course participant for decline course..
		 * 
		 * <pre>
		 * 
		 * @param Id
		 */
		public Builder(Long id) {
			validateId(id);

			this.id = id;
		}

		/**
		 * <pre>
		 * Creates a new instance course participant Builder. Validates and sets the
		 * argument into the Builder instance variables. This method is used for setting
		 * the data from the database..
		 * 
		 * <pre>
		 * 
		 * @param Id
		 * @param courseScheduleId
		 * @param participantId
		 * @param registrationDate
		 * @param courseName
		 * @param instructorName
		 * @param venueName
		 * @param participantName
		 */
		public Builder(Long id, Long courseScheduleId, String courseName, String instructorName, String venueName,
				Long participantId, String participantName, ZonedDateTime registrationDate,
				String reason, ZonedDateTime declineDate) {

			validateId(id);
			validateCourseScheduleId(courseScheduleId);
			validateParticipantId(participantId);
			validateRegistrationDate(registrationDate);
			validateCourseName(courseName);
			validateInstructorName(instructorName);
			validateVenueName(venueName);
			validateParticipantName(participantName);


			this.id = id;
			this.courseScheduleId = courseScheduleId;
			this.courseName = courseName;
			this.instructorName = instructorName;
			this.venueName = venueName;
			this.participantId = participantId;
			this.participantName = participantName;
			this.registrationDate = registrationDate;
			this.reason = reason;
			this.declineDate = declineDate;
			
		}
		public Builder(Long id, Long courseScheduleId, Long participantId, ZonedDateTime registrationDate) {
			validateId(id);
			validateCourseScheduleId(courseScheduleId);
			validateParticipantId(participantId);
			validateRegistrationDate(registrationDate);
			
			this.id = id;
			this.courseScheduleId = courseScheduleId;
			this.participantId = participantId;
			this.registrationDate = registrationDate;
		}

		/**
		 * <pre>
		 * Validates and add the argument into the Builder instance variables
		 * 
		 * <pre>
		 * 
		 * @param courseScheduleDetail
		 * @return builder
		 */
		public Builder addDetail(Set<CourseScheduleDetail> courseScheduleDetail) {

			validateCourseScheduleDetail(courseScheduleDetail);
			this.courseScheduleDetail = courseScheduleDetail;

			return this;
		}

		/**
		 * <pre>
		 * Validates and add the argument into the Builder instance variables
		 * 
		 * <pre>
		 * 
		 * @param CourseParticipant
		 * @return builder
		 */
		public Builder decline(String reason) {

			validateReason(reason);
			this.declineDate=ZonedDateTime.now();
			this.reason = reason;
				
			return this;
		}

		/**
		 * Creates a new instance of the course schedule detail.
		 * 
		 * @return new CourseParticipant(this)
		 */

		public CourseParticipant build() {

			return new CourseParticipant(this);
		}

		/**
		 * <pre>
		 * Validate the id based on the condition below. If it is invalid then throw an
		 * IllegalArgumentException with the corresponding message.
		 * 
		 * <pre>
		 * 
		 * @param id
		 */
		private void validateId(Long id) {
			if (id == null || id == 0L) {
				throw new IllegalArgumentException("Id should not be empty");
			}
		}

		/**
		 * <pre>
		 * Validate the Course Schedule id based on the condition below. If it is
		 * invalid then throw an IllegalArgumentException with the corresponding
		 * message.
		 * 
		 * <pre>
		 * 
		 * @param courseScheduleId
		 */
		private void validateCourseScheduleId(Long courseScheduleId) {
			if (courseScheduleId == null || courseScheduleId == 0L) {
				throw new IllegalArgumentException("Course Schedule Id should not be empty");
			}

		}

		/**
		 * <pre>
		 * Validate the Participant id based on the condition below. If it is invalid
		 * then throw an IllegalArgumentException with the corresponding message.
		 * 
		 * <pre>
		 * 
		 * @param participantId
		 */
		private void validateParticipantId(Long participantId) {
			if (participantId == null || participantId == 0L) {
				throw new IllegalArgumentException("Participant Id should not be empty");
			}

		}

		/**
		 * <pre>
		 * Validate the Registration Date based on the condition below. If it is invalid
		 * then throw an IllegalArgumentException with the corresponding message.
		 * 
		 * <pre>
		 * 
		 * @param registrationDate
		 */
		private void validateRegistrationDate(ZonedDateTime registrationDate) {
			if (registrationDate == null) {
				throw new IllegalArgumentException("Registration Date should not be empty");
			}
		}

		/**
		 * <pre>
		 * Validate the Course name based on the condition below. If it is invalid then
		 * throw an IllegalArgumentException with the corresponding message.
		 * 
		 * <pre>
		 * 
		 * @param courseName
		 */
		private void validateCourseName(String courseName) {
			if (courseName == null || courseName.isEmpty()) {
				throw new IllegalArgumentException("Course name should not be empty");
			}

		}

		/**
		 * <pre>
		 * Validate the Instructor Name based on the condition below. If it is invalid
		 * then throw an IllegalArgumentException with the corresponding message.
		 * 
		 * <pre>
		 * 
		 * @param instructorName
		 */
		private void validateInstructorName(String instructorName) {
			if (instructorName == null || instructorName.isEmpty()) {
				throw new IllegalArgumentException("Instructor Name should not be empty");
			}
		}

		/**
		 * <pre>
		 * Validate the Venue Name based on the condition below. If it is invalid then
		 * throw an IllegalArgumentException with the corresponding message.
		 * 
		 * <pre>
		 * 
		 * @param VenueName
		 */
		private void validateVenueName(String venueName) {
			if (venueName == null || venueName.isEmpty()) {
				throw new IllegalArgumentException("Venue name should not be empty");
			}
		}
		
		/**
		 * <pre>
		 * Validate the Participant Name based on the condition below. If it is invalid then
		 * throw an IllegalArgumentException with the corresponding message.
		 * 
		 * <pre>
		 * 
		 * @param participantName
		 */
		private void validateParticipantName(String participantName) {
			if (participantName == null || participantName.isEmpty()) {
				throw new IllegalArgumentException("Participant Name should not be empty");
			}
		}


		/**
		 * <pre>
		 * Validate the Course Schedule Detail based on the condition below. If it is
		 * invalid then throw an IllegalArgumentException with the corresponding
		 * message.
		 * 
		 * <pre>
		 * 
		 * @param courseScheduleDetail
		 */
		private void validateCourseScheduleDetail(Set<CourseScheduleDetail> courseScheduleDetail) {
			if (courseScheduleDetail == null || courseScheduleDetail.isEmpty()) {
				throw new IllegalArgumentException("Course Schedule Detail should have atleast 1 record");
			}
		}

		/**
		 * <pre>
		 * Validate the Reason based on the condition below. If it is invalid then throw
		 * an IllegalArgumentException with the corresponding message.
		 * 
		 * <pre>
		 * 
		 * @param reason
		 */
		private void validateReason(String reason) {
			if (reason == null || reason.isEmpty()) {
				throw new IllegalArgumentException("Reason should not be empty");
			}
		}

	}
	
}

