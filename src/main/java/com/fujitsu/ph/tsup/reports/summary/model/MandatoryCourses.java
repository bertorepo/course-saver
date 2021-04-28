/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.reports.summary.model;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * MandatoryCoursesForm class
 * 
 * @author z.deguia (New Creation by: z.deguia)
 * @version Revision: 0.01 Date: 2021-04-21
 */

public class MandatoryCourses {

    /**
     * Course ID
     */
    private Long id;

    /**
     * Course name
     */
    private String name;

    /**
     *  Start Date and Time
     */
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime startDateTime;
    
    /**
     *  End Date and Time
     */
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime endDateTime;
   
    /**
     * Empty Constructor for ConductedCourse class
     */
    public MandatoryCourses() {

    }

    /**
     * MandatoryCourses Constructor
     * @param builder Builder
     */
    private MandatoryCourses(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.startDateTime = builder.startDateTime;
        this.endDateTime = builder.endDateTime;
        
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(ZonedDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(ZonedDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "MandatoryCourses [id=" + id + ", name=" + name + ", startDateTime=" + startDateTime
                + ", endDateTime=" + endDateTime + "]";
    }

    /**
     * Builder Class
     * @author Z.DeGuia
     *
     */
    public static class Builder {

        /**
         * Id
         */
        private Long id;

        /**
         * Course Name
         */
        private String name;
        
        /**
         *  Start Date and Time
         */
        private ZonedDateTime startDateTime;
        
        /**
         *  End Date and Time
         */
        private ZonedDateTime endDateTime;

        
        /**
         * <pre>
         * Creates a new instance of Builder for courses conducted. 
         * It validates and sets the argument into the Builder instance variables.
         * </pre>
         * 
         * @param id
         * @param name
         */
        public Builder(Long id, String name, ZonedDateTime startDateTime, ZonedDateTime endDateTime) {

            validateId(id);
            validateName(name);
            validateStartDateTime(startDateTime);
            validateEndDateTime(endDateTime);

            this.id = id;
            this.name = name;
            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
        }
        
        public Builder(Long id, String name) {

            validateId(id);
            validateName(name);

            this.id = id;
            this.name = name;
        }
        
        public Builder(ZonedDateTime startDateTime, ZonedDateTime endDateTime) {
            
            validateStartDateTime(startDateTime);
            validateEndDateTime(endDateTime);

            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
        }

        public MandatoryCourses build() {
            return new MandatoryCourses(this);
        }
        
        /**
         * <pre>
         * Validate the id based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * </pre>
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
         * Validate the name based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * </pre>
         * 
         * @param name
         */
        private void validateName(String name) {
            
            if (name == null || name.isEmpty()) {
                
                throw new IllegalArgumentException("Name should not be empty");
                
            }
            
        }
        
        /**
        * <pre>
        * Validate the Start Date and Time based on the condition below. 
        * If it is invalid then throw an IllegalArgumentException with the corresponding message.
        * </pre>
        * 
        * @param startDateTime
        */
       private void validateStartDateTime(ZonedDateTime startDateTime) {
           
           if (startDateTime == null || String.valueOf(startDateTime).isEmpty()) {
               
               throw new IllegalArgumentException("Start Date and Time should not be empty");
               
           }
       }
       
       /**
        * <pre>
        * Validate the End Date and Time based on the condition below. 
        * If it is invalid then throw an IllegalArgumentException with the corresponding message.
        * </pre>
        * 
        * @param endDateTime
        */
       private void validateEndDateTime(ZonedDateTime endDateTime) {
           
           if (endDateTime == null || String.valueOf(endDateTime).isEmpty()) {
               
               throw new IllegalArgumentException("End Date and Time should not be empty");
               
           }
       }
        
    }
}
