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
    private String isMandatory;
    private String deadline;

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
        this.isMandatory = builder.isMandatory;
        this.deadline = builder.deadline;
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
    
    /**
     * Getter Method for Course is Mandatory
     * 
     * @return Course is Mandatory
     */
    public String getIsMandatory() {
		return isMandatory;
		
	}

    /**
     * Setter Method for Course is Mandatory
     * 
     * @param isMandatory Course is Mandatory
     */
	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
		
	}

	/**
     * Getter Method for Course Deadline
     * 
     * @return Course Deadline
     */
	public String getDeadline() {
		return deadline;
		
	}

	/**
     * Setter Method for Course Deadline
     * 
     * @param deadline Course Deadline
     */
	public void setDeadline(String deadline) {
		this.deadline = deadline;
		
	}


    @Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", detail=" + detail + ", isMandatory=" + isMandatory
				+ ", deadline=" + deadline + "]";
	}



	/** Builder Class
     * @author c.lepiten
     *
     */
    public static class Builder {

        private Long id;
        private String name;
        private String detail;
        private String isMandatory;
        private String deadline;

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
         * @param isMandatory
         * @param deadline
         */
        
        public Builder(String name, String detail, String isMandatory, String deadline) {
        	
        	validateName(name);
        	validateDetail(detail);
        	
        	this.name = name;
        	this.detail = detail;
        	this.isMandatory = isMandatory;
        	this.deadline = deadline;
        	
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
        
        /** Builder Constructor
         * @param isMandatory
         * @param deadline
         * @return
         */
        public Builder mandatory(String isMandatory, String deadline) {
        	this.isMandatory = isMandatory;
        	this.deadline = deadline;
        	
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
