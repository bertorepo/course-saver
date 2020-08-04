package com.fujitsu.ph.tsup.scheduling.domain;

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: CourseScheduleDetail.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/22/2020 | WS) JC. Jimenez | New Creation
//
//
//=======================================================

/**
* <pre>
* The Course Schedule Detail Model.
* This uses a Builder Pattern
* <pre>
* @version 0.01
* @author jc.jimenez
*
*/

import java.time.ZonedDateTime;
import java.time.Duration;

public class CourseScheduleDetail {
    
    /**
     * Id
     */
    private Long id;
    
    /**
     * from the Course Schedule id
     */
    private Long courseScheduleId;
    
    /**
     * Start Date and Time
     */
    private ZonedDateTime scheduledStartDateTime;
    
    /**
     * End Date and Time
     */
    private ZonedDateTime scheduledEndDateTime;
    
    /**
     * duration
     */
    private float duration;
    
    protected CourseScheduleDetail() { 
    }
    
    /**
     * <pre>
     * Creates an instance of the CourseScheduleDetail using the given builder class.
     * <pre>
     * 
     * @param build
     */
    
    private CourseScheduleDetail(Builder build) {
        this.id = build.id;
        this.courseScheduleId = build.courseScheduleId;
        this.scheduledStartDateTime = build.scheduledStartDateTime;
        this.scheduledEndDateTime = build.scheduledEndDateTime;
        this.duration = build.duration;
    }
    
    public Long getId() {
        return id;
    }
    
    public Long getCourseScheduleId() {
        return courseScheduleId;
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
    
    /**
     * <pre>
     * The builder class of the course schedule detail. 
     * The builder is a public static member class of CourseScheduleDetail
     * <pre>
     * 
     * @author jc.jimenez
     *
     */
    public static class Builder{
        
        /**
         * Id
         */
        private Long id;
        
        /**
         * from the Course Schedule id
         */
        private Long courseScheduleId;
        
        /**
         * Start Date and Time
         */
        private ZonedDateTime scheduledStartDateTime;
        
        /**
         * End Date and Time
         */
        private ZonedDateTime scheduledEndDateTime;
        
        /**
         * duration
         */
        private float duration;
        
        
        /**
         * <pre>
         * Creates a new instance of Builder for creating a course schedule details. 
         * It validates and sets the argument into the Builder instance variables. 
         * <pre>
         * 
         * @param id
         * @param courseScheduleId
         * @param scheduledStartDateTime
         * @param scheduledEndDateTime
         */
        public Builder(Long id, Long courseScheduleId, ZonedDateTime scheduledStartDateTime, 
                ZonedDateTime scheduledEndDateTime) {
            validateId(id);
            validateCourseScheduleId(courseScheduleId);
            validateScheduledStartDateTime(scheduledStartDateTime);
            validateScheduledEndDateTime(scheduledEndDateTime, scheduledStartDateTime);
            
            this.id = id;
            this.courseScheduleId = courseScheduleId;
            this.scheduledStartDateTime = scheduledStartDateTime;
            this.scheduledEndDateTime = scheduledEndDateTime;
            this.duration = computeDuration(scheduledStartDateTime, scheduledEndDateTime);
        }
        
        /**
         * <pre>
         * Creates a new instance of course schedule detail Builder. 
         * Validates and sets the argument into the Builder instance variables. 
         * This method is used for setting the data from the database
         * <pre>
         * 
         * @param courseScheduleId
         * @param scheduledStartDateTime
         * @param scheduledEndDateTime
         */
        public Builder(Long courseScheduleId, ZonedDateTime scheduledStartDateTime, 
                ZonedDateTime scheduledEndDateTime, float duration) {
            validateCourseScheduleId(courseScheduleId);
            validateScheduledStartDateTime(scheduledStartDateTime);
            validateScheduledEndDateTime(scheduledEndDateTime, scheduledStartDateTime);
            
            this.courseScheduleId = courseScheduleId;
            this.scheduledStartDateTime = scheduledStartDateTime;
            this.scheduledEndDateTime = scheduledEndDateTime;
            this.duration = duration;
        }
        
        /**
         * Creates a new instance of the course schedule detail
         * 
         * @return new CourseScheduleDetail(this)
         */
        public CourseScheduleDetail build() {
            return new CourseScheduleDetail(this);
        }
        
        /**
         * <pre>
         * Validate the id based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
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
         * Validate the Course Schedule Id based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
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
         * Validate the Scheduled Start Date and Time based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * <pre>
         * 
         * @param scheduledStartDateTime
         */
        private void validateScheduledStartDateTime(ZonedDateTime scheduledStartDateTime) {
            if (scheduledStartDateTime == null || String.valueOf(scheduledStartDateTime).isEmpty()) {
                throw new IllegalArgumentException("Scheduled Start Date and Time should not be empty");
            }
        }

        /**
         * <pre>
         * Validate the Scheduled End Date and Time based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * <pre>
         * 
         * @param scheduledEndDateTime
         * @param scheduledStartDateTime
         */
        private void validateScheduledEndDateTime(ZonedDateTime scheduledEndDateTime, 
                ZonedDateTime scheduledStartDateTime) {
            if (scheduledEndDateTime == null || String.valueOf(scheduledEndDateTime).isEmpty()) {
                throw new IllegalArgumentException("Scheduled End Date and Time should not be empty");
            } else if (scheduledStartDateTime.isAfter(scheduledEndDateTime)) {
                throw new IllegalArgumentException("Scheduled end date and time should be greater than or "
                        + "equal to the the scheduled start date and time");
                
            }
        }
        
        /**
         * <pre>
         * Compute the duration as follows:
         * duration = scheduledEndDateTime - scheduledStartDateTime
         * <pre>
         * 
         * @param scheduledStartDateTime
         * @param scheduledEndDateTime
         * @return Duration.between(scheduledStartDateTime, scheduledEndDateTime).toHours()
         */
        private float computeDuration(ZonedDateTime scheduledStartDateTime, 
                ZonedDateTime scheduledEndDateTime) {
            float duration;
            Long durationToHours = Duration.between(scheduledStartDateTime, scheduledEndDateTime).toHours();
            Long durationToMinutes = Duration.between(scheduledStartDateTime, scheduledEndDateTime).toMinutes();
            
            float minutePercentage = durationToMinutes.floatValue() % 60 / 60;
            duration = durationToHours + minutePercentage;
            return duration;
            }
        }
        
        @Override
        public String toString() {
            return "CourseScheduleDetail = [id="+id +" courseScheduleId="+ courseScheduleId + 
                    " courseScheduleId="+ courseScheduleId+" scheduledStartDateTime="+ scheduledStartDateTime 
                    + " scheduledEndDateTime="+ scheduledEndDateTime + " duration="+ duration +"]";
        }
}
