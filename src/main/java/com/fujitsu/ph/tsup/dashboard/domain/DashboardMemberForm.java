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
        public Builder(String courseName, String instructorName, ZonedDateTime startDateTime, 
                ZonedDateTime endDateTime, String venueName, String status) {
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
        public Builder(String courseName, String instructorName, ZonedDateTime startDateTime, 
                ZonedDateTime endDateTime, String venueName, Long employeeId, String status) {
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
}
    /**
     * Overrides toString()
     * 
     * @return String containing employeeId, courseName, instructorName, startDateTime, endDateTime, 
     * venueName, status
     */
    @Override
    public String toString() {
        return "DashboardMemberForm [employeeId=" + employeeId + ", courseName=" + courseName + 
                ", instructorName= " + instructorName + ",startDateTime=" + startDateTime + 
                ", endDateTime=" + endDateTime + ", venueName=" + venueName + ", status=" + status + "]";
    }
}
