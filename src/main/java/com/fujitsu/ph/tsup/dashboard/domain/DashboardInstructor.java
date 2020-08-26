package com.fujitsu.ph.tsup.dashboard.domain;

import java.time.ZonedDateTime;

/**
 * <pre>
 * It is a JavaBean for Dashboard (Instructor)
 * In this class, variables and builder are defined that will be an instance when calling the data from the database
 * </pre>
 * 
 * @version 0.01
 * @author Jm.Deguzman
 *
 */

//==================================================================================================
//$Id:PR06$
//Project Name :Training Sign up
//System Name  :Dashboard
//Class Name   :DashboardInstructorForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/24/2020 | WS) Jm.Deguzman       | New Creation
//0.02    | 08/24/2020 | WS) Jm.Deguzman       | Update

public class DashboardInstructor {
    /**
     * Employee ID
     */
    private Long employeeId;
    /**
     * Course Name
     */
    private String courseName;
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

    protected DashboardInstructor() {

    }

    /**
     * <pre>
     * Creates an instance of the DashboardInstructorForm using the given builder
     * class.
     * 
     * <pre>
     * 
     * @param builder
     */
    private DashboardInstructor(Builder builder) {
        this.employeeId = builder.employeeId;
        this.courseName = builder.courseName;
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
         * Coruse Name
         */
        private String courseName;
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
         * @param startDateTime
         * @param endDateTime
         * @param venueName
         * @param status
         */

        public Builder(String courseName, ZonedDateTime startDateTime, ZonedDateTime endDateTime, String venueName,
                String status) {
            validateCourseName(courseName);
            validateStartDateTime(startDateTime);
            validateEndDateTime(endDateTime);
            validateVenueName(venueName);
            validateStatus(status);

            this.courseName = courseName;
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
        public Builder(String courseName, ZonedDateTime startDateTime, ZonedDateTime endDateTime, String venueName,
                Long employeeId, String status) {
            validateEmployeeId(employeeId);
            validateCourseName(courseName);
            validateStartDateTime(startDateTime);
            validateEndDateTime(endDateTime);
            validateVenueName(venueName);
            validateStatus(status);

            this.employeeId = employeeId;
            this.courseName = courseName;
            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
            this.venueName = venueName;
            this.status = status;
        }

        /**
         * Creates a new instance of the DashboardInstructorForm.
         * 
         * @return new DashboardInstructorForm(this)
         */
        public DashboardInstructor build() {
            return new DashboardInstructor(this);
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
         * Validates End Date Time
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
     * @return String containing employeeId, courseName, startDateTime, endDateTime,
     *         venueName, status
     */
    @Override
    public String toString() {
        return "DashboardInstructorForm [employeeId=" + employeeId + ", courseName=" + courseName + ", startDateTime="
                + startDateTime + ", endDateTime=" + endDateTime + ", venueName=" + venueName + ", status=" + status
                + "]";
    }
}
