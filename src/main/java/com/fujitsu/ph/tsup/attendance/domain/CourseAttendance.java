package com.fujitsu.ph.tsup.attendance.domain;

import java.time.ZonedDateTime;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :CourseAttendance.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/22/2020 |  WS) R. Ramos   | New Creation
//==================================================================================================
/**
 * <pre>
 * The Course Attendance model. This uses a builder pattern
 * 
 * <pre>
 * 
 * @version 0.01
 * @author r.ramos
 */
public class CourseAttendance {
    /**
     * Course Attendance Id
     */
    private Long id;

    /**
     * from the Course Schedule detail id
     */
    private Long courseScheduleDetailId;

    /**
     * Course Name
     */
    private String courseName;

    /**
     * Instructor Name (LASTNAME, FIRSTNAME)
     */
    private String instructorName;

    /**
     * Venue Name
     */
    private String venueName;

    /**
     * from the Employee Id
     */
    private Long participantId;

    /**
     * Participant Name(LASTNAME, FIRSTNAME)
     */
    private String participantName;

    /**
     * Start Date and Time
     */
    private ZonedDateTime scheduledStartDateTime;

    /**
     * End Date and Time
     */
    private ZonedDateTime scheduledEndDateTime;

    /**
     * Duration
     */
    private float duration;

    /**
     * Registration Date
     */
    private ZonedDateTime registrationDate;

    /**
     * Login Date and Time
     */
    private ZonedDateTime loginDateTime;

    /**
     * Status
     */
    private char status;

    protected CourseAttendance() {
    }

    /**
     * <pre>
     * Creates an instance of the CourseAttendance using the given builder class.
     * 
     * <pre>
     * 
     * @param builder
     */

    private CourseAttendance(Builder builder) {
        this.id = builder.id;
        this.courseScheduleDetailId = builder.courseScheduleDetailId;
        this.courseName = builder.courseName;
        this.instructorName = builder.instructorName;
        this.venueName = builder.venueName;
        this.participantId = builder.participantId;
        this.participantName = builder.participantName;
        this.scheduledStartDateTime = builder.scheduledStartDateTime;
        this.scheduledEndDateTime = builder.scheduledEndDateTime;
        this.duration = builder.duration;
        this.registrationDate = builder.registrationDate;
        this.loginDateTime = builder.loginDateTime;
        this.status = builder.status;
    }

    public Long getId() {
        return id;
    }

    public Long getCourseScheduleDetailId() {
        return courseScheduleDetailId;
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

    public ZonedDateTime getScheduleStartDateTime() {
        return scheduledStartDateTime;
    }

    public ZonedDateTime getScheduleEndDateTime() {
        return scheduledEndDateTime;
    }

    public float getDuration() {
        return duration;
    }

    public ZonedDateTime getRegistrationDate() {
        return registrationDate;
    }

    public ZonedDateTime getLoginDateTime() {
        return loginDateTime;
    }

    public char getStatus() {
        return status;
    }
    
	@Override
	public String toString() {
		return "CourseAttendance [id = " + id + ", courseScheduleDetailId = " + courseScheduleDetailId
				+ ", courseName = " + courseName + ", instructorName = " + instructorName + ", venueName = " + venueName
				+ ", participantId = " + participantId + ", participantName = " + participantName
				+ ", scheduledStartDateTime = " + scheduledStartDateTime + ", scheduledEndDateTime = "
				+ scheduledEndDateTime + ", duration = " + duration + ", registrationDate = " + registrationDate
				+ ", loginDateTime = " + loginDateTime + ", status = " + status + "]";
	}

    /**
     * <pre>
     * The builder class of the course attendance. The builder is a public static
     * member class of CourseAttendance
     * 
     * <pre>
     * 
     * @author r.ramos
     *
     */

    public static class Builder {

        /**
         * Course Attendance Id
         */
        private Long id;

        /**
         * from the Course Schedule detail id
         */
        private Long courseScheduleDetailId;

        /**
         * Course Name
         */
        private String courseName;

        /**
         * Instructor Name (LASTNAME, FIRSTNAME)
         */
        private String instructorName;

        /**
         * Venue Name
         */
        private String venueName;

        /**
         * from the Employee Id
         */
        private Long participantId;

        /**
         * Participant Name(LASTNAME, FIRSTNAME)
         */
        private String participantName;

        /**
         * Start Date and Time
         */
        private ZonedDateTime scheduledStartDateTime;

        /**
         * End Date and Time
         */
        private ZonedDateTime scheduledEndDateTime;

        /**
         * Duration
         */
        private float duration;

        /**
         * Registration Date
         */
        private ZonedDateTime registrationDate;

        /**
         * Login Date and Time
         */
        private ZonedDateTime loginDateTime;

        /**
         * Status
         */
        private char status;

        /**
         * <pre>
         * Creates a new instance of course attendance Builder. Validates and sets the
         * argument into the Builder instance variables. This method is used for setting
         * the data from the database
         * 
         * <pre>
         * 
         * @param id
         * @param courseScheduleDetailId
         * @param courseName
         * @param venueName
         * @param participantId
         * @param participantName
         * @param scheduledStartDateTime
         * @param scheduledEndDateTime
         * @param duration
         * @param registrationDate
         * @param loginDateTime
         * @param status
         */

        public Builder(Long id, Long courseScheduleDetailId, String courseName, String instructorName, String venueName,
                Long participantId, String participantName, ZonedDateTime scheduledStartDateTime,
                ZonedDateTime scheduledEndDateTime, float duration, ZonedDateTime registrationDate,
                ZonedDateTime loginDateTime, char status) {

            validateId(id);
            validateCourseScheduleDetailId(courseScheduleDetailId);
            validateParticipantId(participantId);
            validateRegistrationDate(registrationDate);
            validateCourseName(courseName);
            validateInstructorName(instructorName);
            validateVenueName(venueName);
            validateParticipantName(participantName);
            validateScheduledStartDateTime(scheduledStartDateTime);
            validateScheduledEndDateTime(scheduledEndDateTime);
            validateScheduledEndDateTime(scheduledEndDateTime);

            this.id = id;
            this.courseScheduleDetailId = courseScheduleDetailId;
            this.participantId = participantId;
            this.registrationDate = registrationDate;
            this.courseName = courseName;
            this.instructorName = instructorName;
            this.venueName = venueName;
            this.participantName = participantName;
            this.scheduledStartDateTime = scheduledStartDateTime;
            this.scheduledEndDateTime = scheduledEndDateTime;
            this.scheduledEndDateTime = scheduledEndDateTime;
        }

        /**
         * <pre>
         * Creates a new instance of course attendance Builder. Validates and sets the
         * argument into the Builder instance variables. This method is used for setting
         * the data from the database
         * 
         * <pre>
         * 
         * @param id
         * @param courseScheduleDetailId
         * @param courseName
         * @param venueName
         * @param participantId
         * @param participantName
         * @param scheduledStartDateTime
         * @param scheduledEndDateTime
         * @param duration
         * @param registrationDate
         */

        public Builder(Long id, Long courseScheduleDetailId, String courseName, String instructorName, String venueName,
                Long participantId, String participantName, ZonedDateTime scheduledStartDateTime,
                ZonedDateTime scheduledEndDateTime, float duration, ZonedDateTime registrationDate) {

            validateId(id);
            validateCourseScheduleDetailId(courseScheduleDetailId);
            validateParticipantId(participantId);
            validateRegistrationDate(registrationDate);
            validateCourseName(courseName);
            validateInstructorName(instructorName);
            validateVenueName(venueName);
            validateParticipantName(participantName);
            validateScheduledStartDateTime(scheduledStartDateTime);
            validateScheduledEndDateTime(scheduledEndDateTime);
            validateScheduledEndDateTime(scheduledEndDateTime);

            this.id = id;
            this.courseScheduleDetailId = courseScheduleDetailId;
            this.participantId = participantId;
            this.registrationDate = registrationDate;
            this.courseName = courseName;
            this.instructorName = instructorName;
            this.venueName = venueName;
            this.participantName = participantName;
            this.scheduledStartDateTime = scheduledStartDateTime;
            this.scheduledEndDateTime = scheduledEndDateTime;
            this.scheduledEndDateTime = scheduledEndDateTime;
        }

        /**
         * <pre>
         * Creates a new instance of course attendance Builder. Validates and sets the
         * argument into the Builder instance variables. This method is used for setting
         * the data from the database
         * 
         * <pre>
         * 
         * @param id
         */

        public Builder(Long id) {
            validateId(id);

            this.id = id;
        }

        /**
         * <pre>
         * Creates a new instance of course attendance Builder. Validates and sets the
         * argument into the Builder instance variables. This method is used for setting
         * the data from the database
         * 
         * <pre>
         * 
         * @param courseScheduleDetailId
         * @param particiantId
         */

        public Builder(Long courseScheduleDetailId, Long particiantId) {
            validateCourseScheduleDetailId(courseScheduleDetailId);
            validateParticipantId(particiantId);

            this.courseScheduleDetailId = courseScheduleDetailId;
            this.participantId = particiantId;
        }

        /**
         * <pre>
         * Set the status to absent
         * 
         * <pre>
         * 
         * @return builder
         */

        public Builder absent() {
            this.status = 'A';
            this.loginDateTime = null;

            return this;
        }

        /**
         * <pre>
         * Set the status to present
         * 
         * <pre>
         * 
         * @param loginDateTime
         * @return builder
         */

        public Builder present(ZonedDateTime loginDateTime) {
            this.status = 'P';
            this.loginDateTime = loginDateTime;

            return this;
        }

        /**
         * <pre>
         * Creates a new instance
         * 
         * <pre>
         * 
         * @return builder
         */

        public CourseAttendance build() {
            return new CourseAttendance(this);
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
            if (id == null || id == 0) {
                throw new IllegalArgumentException("Id should not be empty");
            }
        }

        /**
         * <pre>
         * Validate the course schedule detail id based on the condition below. If it is
         * invalid then throw an IllegalArgumentException with the corresponding
         * message.
         * 
         * <pre>
         * 
         * @param courseScheduleDetailId
         */

        private void validateCourseScheduleDetailId(Long courseScheduleDetailId) {
            if (courseScheduleDetailId == null || courseScheduleDetailId == 0) {
                throw new IllegalArgumentException("Course schedule detail id should not be empty");
            }
        }

        /**
         * <pre>
         * Validate the participant id based on the condition below. If it is invalid
         * then throw an IllegalArgumentException with the corresponding message.
         * 
         * <pre>
         * 
         * @param participantId
         */

        private void validateParticipantId(Long participantId) {
            if (participantId == null || participantId == 0) {
                throw new IllegalArgumentException("Participant should not be empty");
            }
        }

        /**
         * <pre>
         * Validate the registration date based on the condition below. If it is invalid
         * then throw an IllegalArgumentException with the corresponding message.
         * 
         * <pre>
         * 
         * @param registrationDate
         */

        private void validateRegistrationDate(ZonedDateTime registrationDate) {
            if (registrationDate == null) {
                throw new IllegalArgumentException("Registration date should not be empty");
            }
        }

        /**
         * <pre>
         * Validate the course name based on the condition below. If it is invalid then
         * throw an IllegalArgumentException with the corresponding message.
         * 
         * <pre>
         * 
         * @param courseName
         */

        private void validateCourseName(String courseName) {
            if (courseName == null || courseName.isEmpty()) {
                throw new IllegalArgumentException("Course Name should not be empty");
            }
        }

        /**
         * <pre>
         * Validate the instructor name based on the condition below. If it is invalid
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
         * Validate the venue name based on the condition below. If it is invalid then
         * throw an IllegalArgumentException with the corresponding message.
         * 
         * <pre>
         * 
         * @param venueName
         */

        private void validateVenueName(String venueName) {
            if (venueName == null || venueName.isEmpty()) {
                throw new IllegalArgumentException("Venue Name should not be empty");
            }
        }

        /**
         * <pre>
         * Validate the participant name based on the condition below. If it is invalid
         * then throw an IllegalArgumentException with the corresponding message.
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
         * Validate the scheduled start date time based on the condition below. If it is
         * invalid then throw an IllegalArgumentException with the corresponding
         * message.
         * 
         * <pre>
         * 
         * @param scheduledStartDateTime
         */

        private void validateScheduledStartDateTime(ZonedDateTime scheduledStartDateTime) {
            if (scheduledStartDateTime == null) {
                throw new IllegalArgumentException("Scheduled start date should not be empty");
            }
        }

        /**
         * <pre>
         * Validate the scheduled end date time based on the condition below. If it is
         * invalid then throw an IllegalArgumentException with the corresponding
         * message.
         * 
         * <pre>
         * 
         * @param scheduledEndDateTime
         */

        private void validateScheduledEndDateTime(ZonedDateTime scheduledEndDateTime) {
            if (scheduledEndDateTime == null) {
                throw new IllegalArgumentException("Scheduled end date should not be empty");

            } 
            if (scheduledEndDateTime.isBefore(scheduledStartDateTime)) {
                throw new IllegalArgumentException(
                        "Scheduled end date and time should be greater than or equal to Scheduled start date and time");
            }
        }
    }
}
