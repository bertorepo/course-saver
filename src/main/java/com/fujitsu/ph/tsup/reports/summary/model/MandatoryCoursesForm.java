/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.reports.summary.model;

/**
 * MandatoryCoursesForm class
 * 
 * @author z.deguia (New Creation by: z.deguia)
 * @version Revision: 0.01 Date: 2021-04-21
 */

import java.time.ZonedDateTime;

public class MandatoryCoursesForm {

    /**
     * Course Id
     */
    private Long courseId;

    /**
     * Mandatory course flag
     */
    private String mandatory;

    /**
     * Course Schedule Id
     */
    private Long courseScheduleId;

    /**
     * Course Schedule Detail ID
     */
    private Long courseScheduleDetailId;

    /**
     * Scheduled Start Date Time
     */
    private ZonedDateTime scheduledStartDatetime;

    /**
     * Rescheduled Start Date Time
     */
    private ZonedDateTime rescheduledStartDatetime;

    /**
     * Employee ID
     */
    private Long employeeId;

    /**
     * Department ID
     */
    private Long departmentId;

    /**
     * Course Attendance ID
     */
    private Long courseAttendanceId;

    /**
     * Participant ID
     */
    private Long participantId;

    /**
     * Status
     */
    private String status;

    /**
     * Empty Constructor for MandatoryCoursesForm class
     */
    protected MandatoryCoursesForm() {

    }

    /**
     * MandatoryCoursesForm Constructor
     * @param builder Builder
     */
    private MandatoryCoursesForm(Builder builder) {
        this.courseId = builder.courseId;
        this.mandatory = builder.mandatory;
        this.courseScheduleId = builder.courseScheduleId;
        this.courseScheduleDetailId = builder.courseScheduleDetailId;
        this.scheduledStartDatetime = builder.scheduledStartDatetime;
        this.rescheduledStartDatetime = builder.rescheduledStartDatetime;
        this.employeeId = builder.employeeId;
        this.departmentId = builder.departmentId;
        this.courseAttendanceId = builder.courseAttendanceId;
        this.participantId = builder.participantId;
        this.status = builder.status;

    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getMandatory() {
        return mandatory;
    }

    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }

    public Long getCourseScheduleId() {
        return courseScheduleId;
    }

    public void setCourseScheduleId(Long courseScheduleId) {
        this.courseScheduleId = courseScheduleId;
    }

    public Long getCourseScheduleDetailId() {
        return courseScheduleDetailId;
    }

    public void setCourseScheduleDetailId(Long courseScheduleDetailId) {
        this.courseScheduleDetailId = courseScheduleDetailId;
    }

    public ZonedDateTime getScheduledStartDatetime() {
        return scheduledStartDatetime;
    }

    public void setScheduledStartDatetime(ZonedDateTime scheduledStartDatetime) {
        this.scheduledStartDatetime = scheduledStartDatetime;
    }

    public ZonedDateTime getRescheduledStartDatetime() {
        return rescheduledStartDatetime;
    }

    public void setRescheduledStartDatetime(ZonedDateTime rescheduledStartDatetime) {
        this.rescheduledStartDatetime = rescheduledStartDatetime;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getCourseAttendanceId() {
        return courseAttendanceId;
    }

    public void setCourseAttendanceId(Long courseAttendanceId) {
        this.courseAttendanceId = courseAttendanceId;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Builder Class
     * @author z.deguia
     *
     */
    public static class Builder {

        private Long courseId;
        private String mandatory;
        private Long courseScheduleId;
        private Long courseScheduleDetailId;
        private ZonedDateTime scheduledStartDatetime;
        private ZonedDateTime rescheduledStartDatetime;
        private Long employeeId;
        private Long departmentId;
        private Long courseAttendanceId;
        private Long participantId;
        private String status;

        /**
         * Builder Constructor
         * @param name
         */
        public Builder(Long employeeId, Long departmentId) {

            validateEmployeeId(employeeId);
            validateDepartmentId(departmentId);

            this.employeeId = employeeId;
            this.departmentId = departmentId;

        }

        /**
         * Builder Constructor
         * @param id
         * @param name
         */
        public Builder(Long courseAttendanceId, Long courseScheduleId, Long courseScheduleDetailId,
                Long courseId) {

            validateCourseAttendanceId(courseAttendanceId);
            validateCourseScheduleId(courseScheduleId);
            validateCourseScheduleDetailId(courseScheduleDetailId);
            validateCourseId(courseId);

            this.courseAttendanceId = courseAttendanceId;
            this.courseScheduleId = courseScheduleId;
            this.courseScheduleDetailId = courseScheduleDetailId;
            this.courseId = courseId;

        }

        /**
         * Builder Constructor
         * @param name
         * @param detail
         */
      

        public MandatoryCoursesForm build() {

            return new MandatoryCoursesForm(this);

        }
        
        /** Validate id if null or 0
         * @param employeeId
         */
        private void validateEmployeeId(Long employeeId) {

            if (employeeId == null || employeeId == 0) {

                throw new IllegalArgumentException("EmployeeId should not be empty");

            }

        }
        
        /** Validate id if null or 0
         * @param departmentId
         */
        private void validateDepartmentId(Long departmentId) {

            if (departmentId == null || departmentId == 0) {

                throw new IllegalArgumentException("DepartmentID should not be empty");

            }

        }
        
        /** Validate id if null or 0
         * @param courseAttendanceId
         */
        private void validateCourseAttendanceId(Long courseAttendanceId) {

            if (courseAttendanceId == null || courseAttendanceId == 0) {

                throw new IllegalArgumentException("CourseAttendanceID should not be empty");

            }

        }
        
        /** Validate id if null or 0
         * @param courseScheduleId
         */
        private void validateCourseScheduleId(Long courseScheduleId) {

            if (courseScheduleId == null || courseScheduleId == 0) {

                throw new IllegalArgumentException("CourseScheduleID should not be empty");

            }

        }
        
        /** Validate id if null or 0
         * @param courseScheduleDetailId
         */
        private void validateCourseScheduleDetailId(Long courseScheduleDetailId) {

            if (courseScheduleDetailId == null || courseScheduleDetailId == 0) {

                throw new IllegalArgumentException("CourseScheduleDetailID should not be empty");

            }

        }
        
        /** Validate id if null or 0
         * @param courseId
         */
        private void validateCourseId(Long courseId) {

            if (courseId == null || courseId == 0) {

                throw new IllegalArgumentException("CourseID should not be empty");

            }

        }

    }

}
