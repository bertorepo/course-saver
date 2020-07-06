package com.fujitsu.ph.tsup.dashboard.domain;

import java.time.ZonedDateTime;

/**
 * <pre>
 * It is a JavaBean for Dashboard (Member)
 * In this class, variables and builder are defined that will be an instance when calling the data from the database
 * </pre>
 * 
 * @version 0.01
 * @author Jm.Deguzman
 *
 */

//==================================================================================================
//$Id:$
//Project Name :Training Sign up
//System Name  :Dashboard
//Class Name   :DashboardMemberForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/23/2020 | WS) Jm.Deguzman       | New Creation

public class DashboardMemberForm {
    /**
     * Employee ID
     */
    private Long employeeId;
    /**
     * Course Name
     */
    private String courseName;
    /**
     * Instructor Name
     */
    private String instructorName;
    /**
     * Start Date Time
     */
    private ZonedDateTime startDateTime;
    /**
     * End Date Time
     */
    private ZonedDateTime endDateTime;
    /**
     * Venue Name
     */
    private String venueName;
    /**
     * Course Status
     */
    private String status;

    protected DashboardMemberForm() {

    }

    /**
     * <pre>
     * Creates an instance of the DashboardMemberForm using the given builder class.
     * 
     * <pre>
     * 
     * @param builder
     */
    private DashboardMemberForm(Builder builder) {
        this.employeeId = builder.employeeId;
        this.courseName = builder.courseName;
        this.instructorName = builder.instructorName;
        this.startDateTime = builder.startDateTime;
        this.endDateTime = builder.endDateTime;
        this.venueName = builder.venueName;
        this.status = builder.status;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getStatus() {
        return status;
    }

    public static class Builder {
        /**
         * Employee ID
         */
        private Long employeeId;
        /**
         * Course Name
         */
        private String courseName;
        /**
         * Instructor Name
         */
        private String instructorName;
        /**
         * Start Date Time
         */
        private ZonedDateTime startDateTime;
        /**
         * End Date Time
         */
        private ZonedDateTime endDateTime;
        /**
         * Venue Name
         */
        private String venueName;
        /**
         * Course Status
         */
        private String status;

        /**
         * <pre>
         * Creates a new instance of course schedule Builder. Validates and sets the
         * argument into the Builder instance variables. This method is used for setting
         * the data from the database.
         * 
         * <pre>
         * 
         * @param courseName
         * @param instructorName
         * @param startDateTime
         * @param endDateTime
         * @param venueName
         * @param status
         */
        public Builder(String courseName, String instructorName, ZonedDateTime startDateTime, ZonedDateTime endDateTime,
                String venueName, String status) {
            validateCourseName(courseName);
            validateInstructorName(instructorName);
            validateStartDateTime(startDateTime);
            validateEndDateTime(endDateTime);
            validateVenueName(venueName);
            validateStatus(status);

            this.courseName = courseName;
            this.instructorName = instructorName;
            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
            this.venueName = venueName;
            this.status = status;
        }

        /**
         * <pre>
         * Creates a new instance of course schedule Builder. Validates and sets the
         * argument into the Builder instance variables. This method is used for setting
         * the data from the database.
         * 
         * <pre>
         * 
         * @param courseName
         * @param instructorName
         * @param startDateTime
         * @param endDateTime
         * @param venueName
         * @param employeeId
         * @param status
         */
        public Builder(String courseName, String instructorName, ZonedDateTime startDateTime, ZonedDateTime endDateTime,
                String venueName, Long employeeId, String status) {
            validateEmployeeId(employeeId);
            validateCourseName(courseName);
            validateInstructorName(instructorName);
            validateStartDateTime(startDateTime);
            validateEndDateTime(endDateTime);
            validateVenueName(venueName);
            validateStatus(status);

            this.employeeId = employeeId;
            this.courseName = courseName;
            this.instructorName = instructorName;
            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
            this.venueName = venueName;
            this.status = status;
        }

        /**
         * Creates a new instance of the DashboardMemberForm.
         * 
         * @return new DashboardMemberForm(this)
         */
        public DashboardMemberForm build() {
            return new DashboardMemberForm(this);
        }

        /*
         * Validates Employee ID
         * 
         * @param employeeId
         */
        private void validateEmployeeId(Long employeeId) {
            if (employeeId == null || employeeId == 0L) {
                throw new IllegalArgumentException("Employee ID should not be empty");
            }
        }

        /*
         * Validates Course Name
         * 
         * @param courseName
         */
        private void validateCourseName(String courseName) {
            if (courseName == null || courseName.isEmpty()) {
                throw new IllegalArgumentException("Course Name should not be empty");
            }
        }

        /*
         * Validates Instructor Name
         * 
         * @param instructorName
         */
        private void validateInstructorName(String instructorName) {
            if (instructorName == null || instructorName.isEmpty()) {
                throw new IllegalArgumentException("Instructor Name should not be empty");
            }
        }

        /*
         * Validates Start Date Time
         * 
         * @param startDateTime
         */
        private void validateStartDateTime(ZonedDateTime startDateTime) {
            if (startDateTime == null) {
                throw new IllegalArgumentException("Start Date Time should not be empty");
            }
        }

        /*
         * Validates EndDateTime
         * 
         * @param endDateTime
         */
        private void validateEndDateTime(ZonedDateTime endDateTime) {
            if (endDateTime == null) {
                throw new IllegalArgumentException("End Date Time should not be empty");
            }
        }

        /*
         * Validates Venue Name
         * 
         * @param venueName
         */
        private void validateVenueName(String venueName) {
            if (venueName == null || venueName.isEmpty()) {
                throw new IllegalArgumentException("Venue Name should not be empty");
            }
        }

        /*
         * Validates Status
         * 
         * @param status
         */
        private void validateStatus(String status) {
            if (status == null || status.isEmpty()) {
                throw new IllegalArgumentException("Status should not be empty");
            }
        }
    }

    /**
     * Overrides toString()
     * 
     * @return String containing employeeId, courseName, instructorName,
     *         startDateTime, endDateTime, venueName, status
     */
    @Override
    public String toString() {
        return "DashboardMemberForm [employeeId=" + employeeId + ", courseName=" + courseName + ", instructorName= "
                + instructorName + ",startDateTime=" + startDateTime + ", endDateTime=" + endDateTime + ", venueName="
                + venueName + ", status=" + status + "]";
    }
}
