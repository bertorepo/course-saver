package com.fujitsu.ph.tsup.attendance.domain;

import java.time.ZonedDateTime;
import java.util.List;

import com.fujitsu.ph.tsup.attendance.model.ChangeStatusParticipant;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :CourseAttendance.java
//
//<<Modification History>>
//Version | Date       | Updated By                                  | Content
//--------+------------+---------------------------------------------+---------------------------------
//0.01    | 06/22/2020 |  WS) R. Ramos                               | New Creation
//0.02    | 07/03/2020 |  WS) R. Ramos                               | Updated
//0.03    | 07/07/2020 |  WS) R. Ramos                               | Updated
//0.04    | 07/08/2020 |  WS) R. Ramos                               | Updated
//0.05    | 07/09/2020 |  WS) R. Ramos                               | Updated
//0.06    | 08/26/2020 |  WS) K. Abad, WS) J. Iwarat, WS) R.Ramos    | Updated
//==================================================================================================
/**
 * <pre>
 * The Course Attendance model. This uses a builder pattern
 * 
 * <pre>
 * 
 * @version 0.06
 * @author k.abad
 * @author j.iwarat
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
     * Login Date and Time
     */
    private ZonedDateTime loginDateTime;
    
    /**
     * Logout Date and Time
     */
    private ZonedDateTime logoutDateTime;

    /**
     * Status
     */
    private char status;
  
    /**
     * Course Description
     */
    private String courseDescription;  
  
    /**
     * Email
     */
    private String email;
   
    /**
     * Department Id
     */
    private Long departmentId;
 
    /**
     * Department Name
     */
    private String departmentName;
    
    /**
     * Employee Number
     */
    private String employeeNumber;  
  
    /**
     * participants
     */
    private List<ChangeStatusParticipant> participants;

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
        this.loginDateTime = builder.loginDateTime;
        this.logoutDateTime = builder.logoutDateTime;
        this.status = builder.status;
        this.courseDescription = builder.courseDescription;
        this.email = builder.email;
        this.departmentId = builder.departmentId;
        this.departmentName = builder.departmentName;
        this.employeeNumber = builder.employeeNumber;
        this.participants = builder.participants;
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

    public ZonedDateTime getLoginDateTime() {
        return loginDateTime;
    }
    
    public ZonedDateTime getLogoutDateTime() {
        return logoutDateTime;
    }


    public char getStatus() {
        return status;
    }
    
    public String getCourseDescription() {
        return courseDescription;
    }

    public String getEmail() {
        return email;
    }
        
    public Long getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }


    public List<ChangeStatusParticipant> getParticipants() {
        return participants;
    }

    @Override
    public String toString() {
        return "CourseAttendance [id = " + id + ", courseScheduleDetailId = " + courseScheduleDetailId
                + ", courseName = " + courseName + ", instructorName = " + instructorName + ", venueName = " + venueName
                + ", participantId = " + participantId + ", participantName = " + participantName
                + ", scheduledStartDateTime = " + scheduledStartDateTime + ", scheduledEndDateTime = " + scheduledEndDateTime 
                + ", duration = " + duration + ", loginDateTime = " + loginDateTime
                + ", logoutDateTime = " + logoutDateTime + ", status = " + status + ", courseDescription = " + courseDescription 
                + ", email = " + email + ", departmentId = " + departmentId + ", departmentName= " + departmentName 
                + ", employeeNumber = " + employeeNumber + ", participants = " + participants + "]";
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
         * Login Date and Time
         */
        private ZonedDateTime loginDateTime;
        
        /**
         * Logout Date and Time
         */
        private ZonedDateTime logoutDateTime;

        /**
         * Status
         */
        private char status;
       
        /**
         * Course Description
         */
        private String courseDescription;
        
        /**
         * Email
         */
        private String email;
        
        /**
         * Department Id
         */
        private Long departmentId;
        
        /**
         * Department Name
         */
        private String departmentName;
       
        /**
         * Employee Number
         */
        private String employeeNumber;  

        /**
         * participants
         */
        private List<ChangeStatusParticipant> participants;

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
         * @param instructorName
         * @param venueName
         * @param participantId
         * @param participantName
         * @param scheduledStartDateTime
         * @param scheduledEndDateTime
         * @param duration
         * @param loginDateTime
         * @param logoutDateTime
         * @param status
         * @param courseDescription
         * @param email
         * @param departmentId
         * @param departmentName
         * @param employeeNumber
         */
        public Builder(Long id, Long courseScheduleDetailId, String courseName, String instructorName, 
                String venueName, Long participantId, String participantName, ZonedDateTime scheduledStartDateTime,
                ZonedDateTime scheduledEndDateTime, float duration, ZonedDateTime loginDateTime, ZonedDateTime logoutDateTime, 
                char status, String courseDescription, String email, Long departmentId, String departmentName, String employeeNumber) {

            validateId(id);
            validateCourseScheduleDetailId(courseScheduleDetailId);
            validateParticipantId(participantId);
            validateCourseName(courseName);
            validateInstructorName(instructorName);
            validateVenueName(venueName);
            validateParticipantName(participantName);
            validateScheduledStartDateTime(scheduledStartDateTime);
            validateScheduledEndDateTime(scheduledEndDateTime, scheduledStartDateTime);

            this.id = id;
            this.courseScheduleDetailId = courseScheduleDetailId;
            this.participantId = participantId;
            this.courseName = courseName;
            this.instructorName = instructorName;
            this.venueName = venueName;
            this.participantName = participantName;
            this.scheduledStartDateTime = scheduledStartDateTime;
            this.scheduledEndDateTime = scheduledEndDateTime;
            this.status = status;
            this.loginDateTime = loginDateTime;
            this.logoutDateTime = logoutDateTime;
            this.duration = duration;
            this.email = email;
            this.courseDescription = courseDescription;
            this.departmentId = departmentId;
            this.departmentName = departmentName;
            this.employeeNumber = employeeNumber;
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
         * @param instructorName
         * @param venueName
         * @param participantId
         * @param participantName
         * @param scheduledStartDateTime
         * @param scheduledEndDateTime
         * @param duration
         * @param departmentId
         * @param departmentName
         * @param employeeNumber
         */
        public Builder(Long id, Long courseScheduleDetailId, String courseName, String instructorName, 
                String venueName, Long participantId, String participantName, ZonedDateTime scheduledStartDateTime,
                ZonedDateTime scheduledEndDateTime, float duration, Long departmentId, String departmentName, String employeeNumber) {

            validateId(id);
            validateCourseScheduleDetailId(courseScheduleDetailId);
            validateParticipantId(participantId);
            validateCourseName(courseName);
            validateInstructorName(instructorName);
            validateVenueName(venueName);
            validateParticipantName(participantName);
            validateScheduledStartDateTime(scheduledStartDateTime);
            validateScheduledEndDateTime(scheduledEndDateTime, scheduledStartDateTime);

            this.id = id;
            this.courseScheduleDetailId = courseScheduleDetailId;
            this.participantId = participantId;
            this.courseName = courseName;
            this.instructorName = instructorName;
            this.venueName = venueName;
            this.participantName = participantName;
            this.scheduledStartDateTime = scheduledStartDateTime;
            this.scheduledEndDateTime = scheduledEndDateTime;
            this.scheduledEndDateTime = scheduledEndDateTime;
            this.duration = duration;
            this.departmentId = departmentId;
            this.departmentName = departmentName;
            this.employeeNumber = employeeNumber;
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
         * @param participantId
         */
        public Builder(Long id, Long courseScheduleDetailId, Long participantId) {
            validateCourseScheduleDetailId(courseScheduleDetailId);
            validateParticipantId(participantId);
            
            this.id = id;
            this.courseScheduleDetailId = courseScheduleDetailId;
            this.participantId = participantId;
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
         * @param participants
         */
        public Builder(Long id, List<ChangeStatusParticipant> participants) {
            validateId(id);

            this.id = id;
            this.participants = participants;
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
         * Set the logout date time 
         * 
         * <pre>
         * 
         * @param logoutDateTime
         * @return builder
         */
        public Builder logout(ZonedDateTime logoutDateTime) {
			this.logoutDateTime = logoutDateTime;
			
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
                //throw new IllegalArgumentException("Id should not be empty");
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
        private void validateScheduledEndDateTime(ZonedDateTime scheduledEndDateTime,
                ZonedDateTime scheduledStartDateTime) {
            if (scheduledEndDateTime == null || String.valueOf(scheduledEndDateTime).isEmpty()) {
                throw new IllegalArgumentException("Scheduled end date and time should not be empty");
            } else if (scheduledStartDateTime.isAfter(scheduledEndDateTime)) {
                throw new IllegalArgumentException(
                        "Scheduled end date and time should be greater than or equal to the the scheduled start date and time");
            }
        }
    }
}
