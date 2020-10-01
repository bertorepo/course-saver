/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.model;

/**
 * CourseForm class
 * 
 * @author c.lepiten (New Creation by: c.Lepiten)
 * @version Revision: 0.01 Date: 2020-08-28
 */
public class Course {

    private Long id;
    private String name;
    private String detail;

    /**
     * Empty Constructor for Course class
     */
    protected Course() {

    }

    /** Course Constructor
     * @param builder Builder
     */
    private Course(Builder builder) {

        this.id = builder.id;
        this.name = builder.name;
        this.detail = builder.detail;
    }

    /**
     * Getter method for Course Id
     * 
     * @return Course Id
     */
    public Long getId() {

        return id;

    }

    /**
     * Setter method for Course Id
     * 
     * @param id Course Id
     */
    public void setId(Long id) {

        this.id = id;

    }

    /**
     * Getter method for Course Name
     * 
     * @return Course name
     */
    public String getName() {

        return name;

    }

    /**
     * Setter method for Course Name
     * 
     * @param name Course Name
     */
    public void setName(String name) {

        this.name = name;

    }

    /**
     * Getter Method for Course Detail
     * 
     * @return Course Detail
     */
    public String getDetail() {

        return detail;

    }

    /**
     * Setter Method for Course Detail
     * 
     * @param detail Course Detail
     */
    public void setDetail(String detail) {

        this.detail = detail;

    }

    @Override
    public String toString() {
        return "Course [ID + " +id +"Name " + name +" Detail " +detail;
    }

    /** Builder Class
     * @author c.lepiten
     *
     */
    public static class Builder {

        private Long id;
        private String name;
        private String detail;

        /** Builder Constructor
         * @param name
         */
        public Builder(String name) {

            validateName(name);

            this.name = name;

        }

        /** Builder Constructor
         * @param id
         * @param name
         */
        public Builder(Long id, String name) {

            validateId(id);
            validateName(name);

            this.id = id;
            this.name = name;

        }
        
        /** Builder Constructor
         * @param name
         * @param detail
         */
        
        public Builder(String name, String detail) {
        	
        	validateName(name);
        	validateDetail(detail);
        	
        	this.name = name;
        	this.detail = detail;
        	
        }
 
        /** Builder Constructor
         * @param detail
         * @return
         */
        public Builder detail(String detail) {

            validateDetail(detail);
            this.detail = detail;

            return this;

        }

        public Course build() {

            return new Course(this);

        }

        /** Validate course name if null or empty
         * @param name Course Name
         */
        private void validateName(String name) {

            if (name.equals(null) || name.isEmpty()) {

                throw new IllegalArgumentException(
                        "Course should not be empty");

            }

        }

        /**
         * Validate Course id if null or 0
         * @param id
         */
        private void validateId(Long id) {

            if (id == null || id == 0) {

                throw new IllegalArgumentException("Id should not be empty");

            }
        }

        /**
         * Validate Course Detail if null or empty
         * @param detail Course
         */
        private void validateDetail(String detail) {

            if (detail.equals(null) || detail.isEmpty()) {

                throw new IllegalArgumentException(
                        "detail should not be empty");

            }

        }

    }

}
