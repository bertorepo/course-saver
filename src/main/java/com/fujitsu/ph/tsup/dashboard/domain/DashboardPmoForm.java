package com.fujitsu.ph.tsup.dashboard.domain;

import java.time.ZonedDateTime;

/**
 * <pre>
 * It is a JavaBean for Dashboard (PMO)
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
//Class Name   :DashboardPmoForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/25/2020 | WS) Jm.Deguzman       | New Creation

public class DashboardPmoForm {
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
     * Minimum Required Participants
     */
    private int minRequired;
    /**
     * Maximum Allowed Participants
     */
    private int maxAllowed;
    /**
     * Total number of enrolled participants
     */
    private int enrolled;
    /**
     * Course status
     */
    private String status;

    protected DashboardPmoForm() {

    }

    /**
     * <pre>
     * Creates an instance of the DashboardPmoForm using the given builder class.
     * 
     * <pre>
     * 
     * @param builder
     */
    private DashboardPmoForm(Builder builder) {
        this.courseName = builder.courseName;
        this.instructorName = builder.instructorName;
        this.startDateTime = builder.startDateTime;
        this.endDateTime = builder.endDateTime;
        this.minRequired = builder.minRequired;
        this.maxAllowed = builder.maxAllowed;
        this.enrolled = builder.enrolled;
        this.status = builder.status;
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

    public int getMinRequired() {
        return minRequired;
    }

    public int getMaxAllowed() {
        return maxAllowed;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public String getStatus() {
        return status;
    }

    /**
     * <pre>
     * The builder class of the course schedule. The builder is a public static
     * member class of DashboardPmoForm
     * 
     * <pre>
     * 
     */
    public static class Builder {
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
         * Minimum Required participants
         */
        private int minRequired;
        /**
         * Maximum Allowed Participants
         */
        private int maxAllowed;
        /**
         * Total number of enrolled participants
         */
        private int enrolled;
        /**
         * Course status
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
         * @param minRequired
         * @param maxAllowed
         * @param enrolled
         * @param status
         */
        public Builder(String courseName, String instructorName, ZonedDateTime startDateTime, ZonedDateTime endDateTime,
                int minRequired, int maxAllowed, int enrolled, String status) {
            validateCourseName(courseName);
            validateInstructorName(instructorName);
            validateStartDateTime(startDateTime);
            validateEndDateTime(endDateTime);
            validateMinRequired(minRequired);
            validateMaxAllowed(maxAllowed);
            validateStatus(status);

            this.courseName = courseName;
            this.instructorName = instructorName;
            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
            this.minRequired = minRequired;
            this.maxAllowed = maxAllowed;
            this.enrolled = enrolled;
            this.status = status;
        }

        /**
         * Creates a new instance of the DashboardPmoForm.
         * 
         * @return new DashboardPmoForm(this)
         */
        public DashboardPmoForm build() {
            return new DashboardPmoForm(this);
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
         * Validates Minimum Required
         * 
         * @param minRequired
         */
        private void validateMinRequired(int minRequired) {
            if (minRequired < 1) {
                throw new IllegalArgumentException("Minimum required should not be zero");
            }
        }

        /*
         * Validates Maximum Allowed
         * 
         * @param maxAllowed
         */
        private void validateMaxAllowed(int maxAllowed) {
            if (maxAllowed < 1) {
                throw new IllegalArgumentException("Maximum allowed should not be zero");
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
     * @return String containing courseName, instructorName, startDateTime,
     *         endDateTime, minRequired, maxAllowed, enrolled, status
     */
    @Override
    public String toString() {
        return "DashboardPmoForm [courseName=" + courseName + ", instructorName=" + instructorName + ", startDateTime="
                + startDateTime + ", endDateTime=" + endDateTime + ", minRequired=" + minRequired + ", maxAllowed="
                + maxAllowed + ", enrolled=" + enrolled + ", status=" + status + "]";
    }
}
