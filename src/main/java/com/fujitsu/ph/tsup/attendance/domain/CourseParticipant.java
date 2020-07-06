package com.fujitsu.ph.tsup.attendance.domain;

import java.time.ZonedDateTime;

//========================================================================================
//$Id:PR03$
//Project Name : Training Sign Up
//System Name: Attendance process
//Class Name : CourseParticipant.java
//
//<<Modification History>>
//Version |    Date    | Updated By    | Content
//--------+------------+---------------+-----------
// 0.01   | 06/23/2020 | WS) M.Angara  | New Creation
// 0.02   | 06/25/2020 | WS) M.Angara  | Updated
// 0.03   | 06/26/2020 | WS) M.Angara  | Updated
// 0.04   | 06/26/2020 | WS) R.Ramos   | Updated
// 0.05   | 07/03/2020 | WS) R.Ramos   | Updated
// 0.06   | 07/06/2020 | WS) R.Ramos   | Updated
//=========================================================================================
/**
 * <pre>
 * The course participant model. This uses a builder pattern
 * 
 * <pre>
 * 
 * @version 0.4
 * @author m.angara
 */

public class CourseParticipant {
    /* Course Schedule Id */
    private Long id;

    /* from the Course Schedule Id */
    private Long courseScheduleId;

    /* Course Name */
    private String courseName;

    /* Instructor Name(LastName, FirstName) */
    private String instructorName;

    /* Venue Name */
    private String venueName;

    /* from the Employee Id */
    private Long participantId;

    /* Participant Name (LastName, FirstName) */
    private String participantName;

    /* Start Date and Time */
    private ZonedDateTime scheduledStartDateTime;

    /* End Date and Time */
    private ZonedDateTime scheduledEndDateTime;

    /* Duration */
    private float duration;

    /* Registration Date */
    private ZonedDateTime registrationDate;

    /* Email Address */
    private String email;

    /* Employee Number */
    private String employeeNumber;

    protected CourseParticipant() {
    }

    /*
     * <pre>
     * Creates an instance of the CourseParticipant using the given builder class.
     * <pre>
     * 
     * @param builder
     */

    private CourseParticipant(Builder builder) {
        this.id = builder.id;
        this.courseScheduleId = builder.courseScheduleId;
        this.courseName = builder.courseName;
        this.instructorName = builder.instructorName;
        this.venueName = builder.venueName;
        this.participantId = builder.participantId;
        this.participantName = builder.participantName;
        this.scheduledStartDateTime = builder.scheduledStartDateTime;
        this.scheduledEndDateTime = builder.scheduledEndDateTime;
        this.duration = builder.duration;
        this.registrationDate = builder.registrationDate;
        this.email = builder.email;
        this.employeeNumber = builder.employeeNumber;

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

    public ZonedDateTime getScheduledStartDateTime() {
        return scheduledStartDateTime;
    }

    public ZonedDateTime getScheduledEndDateTime() {
        return scheduledEndDateTime;
    }

    public float getDuration() {
        return duration;
    }

    public ZonedDateTime getRegistrationDate() {
        return registrationDate;
    }

    public String getEmail() {
        return email;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }
    
	@Override
	public String toString() {
		return "CourseParticipant [id = " + id + ", courseScheduleId = " + courseScheduleId
				+ ", courseName = " + courseName + ", instructorName = " + instructorName + ", venueName = " + venueName
				+ ", participantId = " + participantId + ", participantName = " + participantName
				+ ", scheduledStartDateTime = " + scheduledStartDateTime + ", scheduledEndDateTime = "
				+ scheduledEndDateTime + ", duration = " + duration + ", registrationDate = " + registrationDate
				+ ", email = " + email + ", employeeNumber = " + employeeNumber + "]";
	}

    /**
     * <pre>
     * The builder class of the course participant. The builder is a public static
     * member class of CourseParticipant
     * 
     * <pre>
     * 
     * @author m.angara
     *
     */

    public static class Builder {
        /* Course Schedule Id */
        private Long id;

        /* from the Course Schedule Id */
        private Long courseScheduleId;

        /* Course Name */
        private String courseName;

        /* Instructor Name(LastName, FirstName) */
        private String instructorName;

        /* Venue Name */
        private String venueName;

        /* from the Employee Id */
        private Long participantId;

        /* Participant Name (LastName, FirstName) */
        private String participantName;

        /* Start Date and Time */
        private ZonedDateTime scheduledStartDateTime;

        /* End Date and Time */
        private ZonedDateTime scheduledEndDateTime;

        /* Duration */
        private float duration;

        /* Registration Date */
        private ZonedDateTime registrationDate;

        /* Email Address */
        private String email;

        /* Employee Number */
        private String employeeNumber;

        /**
         * <pre>
         * Creates a new instance of course participant Builder. Validates and sets the
         * argument into the Builder instance variables. This method is used for setting
         * the data from the database
         * 
         * <pre>
         * 
         * @param id
         * @param courseScheduleId
         * @param courseName
         * @param instructorName
         * @param venueName
         * @param participantId
         * @param participantName
         * @param scheduledStartDateTime
         * @param scheduledEndDateTime
         * @param duration
         * @param registrationDate
         * @param email
         * @param employeeNumber
         */

        public Builder(Long id, Long courseScheduleId, String courseName, String instructorName, String venueName,
                Long participantId, String participantName, ZonedDateTime scheduledStartDateTime,
                ZonedDateTime scheduledEndDateTime, float duration, ZonedDateTime registrationDate, String email,
                String employeeNumber) {

            validateId(id);
            validateCourseScheduleId(courseScheduleId);
            validateParticipantId(participantId);
            validateRegistrationDate(registrationDate);
            validateCourseName(courseName);
            validateInstructorName(instructorName);
            validateVenueName(venueName);
            validateParticipantName(participantName);
            validateScheduledStartDateTime(scheduledStartDateTime);
            validateScheduledEndDateTime(scheduledEndDateTime, scheduledStartDateTime);
            

            this.id = id;
            this.courseScheduleId = courseScheduleId;
            this.participantId = participantId;
            this.registrationDate = registrationDate;
            this.courseName = courseName;
            this.instructorName = instructorName;
            this.venueName = venueName;
            this.scheduledStartDateTime = scheduledStartDateTime;
            this.scheduledEndDateTime = scheduledEndDateTime;
            this.participantName = participantName;
            this.email = email;
            this.employeeNumber = employeeNumber;
        }

        /*
         * <pre> Validate the id based on the condition below. If it is invalid then
         * throw an IllegalArgumentException with the corresponding message.
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

        /*
         * <pre> Validate the courseScheduleId based on the condition below. If it is
         * invalid then throw an IllegalArgumentException with the corresponding
         * message.
         * 
         * <pre>
         * 
         * @param courseScheduleId
         */

        private void validateCourseScheduleId(Long courseScheduleId) {
            if (courseScheduleId == null || courseScheduleId == 0) {
                throw new IllegalArgumentException("Course schedule id id should not be empty");
            }
        }

        /*
         * <pre> Validate the participantId based on the condition below. If it is
         * invalid then throw an IllegalArgumentException with the corresponding
         * message.
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

        /*
         * <pre> Validate the registrationDate based on the condition below. If it is
         * invalid then throw an IllegalArgumentException with the corresponding
         * message.
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

        /*
         * <pre> Validate the courseName based on the condition below. If it is invalid
         * then throw an IllegalArgumentException with the corresponding message.
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

        /*
         * <pre> Validate the instructorName based on the condition below. If it is
         * invalid then throw an IllegalArgumentException with the corresponding
         * message.
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

        /*
         * <pre> Validate the venueName based on the condition below. If it is invalid
         * then throw an IllegalArgumentException with the corresponding message.
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

        /*
         * <pre> Validate the participantName based on the condition below. If it is
         * invalid then throw an IllegalArgumentException with the corresponding
         * message.
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

        /*
         * <pre> Validate the scheduledStartDatetime based on the condition below. If it
         * is invalid then throw an IllegalArgumentException with the corresponding
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

        /*
         * <pre> Validate the scheduledEndDateTime based on the condition below. If it
         * is invalid then throw an IllegalArgumentException with the corresponding
         * message.
         * 
         * <pre>
         * 
         * @param scheduledEndDateTime
         */

        private void validateScheduledEndDateTime(ZonedDateTime scheduledEndDateTime, ZonedDateTime scheduledStartDateTime) {
            if (scheduledEndDateTime == null) {
                throw new IllegalArgumentException("Scheduled end date should not be empty");
            }
            if (scheduledEndDateTime.isAfter(scheduledStartDateTime)) {
                throw new IllegalArgumentException(
                        "Scheduled end date and time should be greater than or equal to Scheduled start date and time");
            }
        }

        /**
         * <pre>
         * Creates a new instance of the course schedule detail
         * 
         * <pre>
         * 
         * @param builder
         */

        public CourseParticipant build() {
            return new CourseParticipant(this);
        }

    }
}
