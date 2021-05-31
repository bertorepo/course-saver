/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.model;

import java.util.Objects;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Course Management
//Class Name   : Course.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2020/08/28 | WS) c.lepiten         | Initial Version
//0.02    | 2021/04/19 | WS) st.diaz           | Updated
//0.03    | 2021/05/10 | WS) D.Escala          | Updated
//0.04	  | 2021/05/25 | WS) mi.aguinaldo      | Update builder and re-factor
//==================================================================================================

public class Course {

    private Long id;
    private String name;
    private String detail;
    private String isMandatory;
    private String deadline;
    private Long courseCategoryId;

    private CourseCategory courseCategory;

    private Course(Builder builder) {
	validateName(builder.name);
	validateDetail(builder.detail);

	this.id = builder.id;
	this.name = builder.name;
	this.detail = builder.detail;
	this.isMandatory = builder.isMandatory;
	this.deadline = builder.deadline;
	this.courseCategoryId = builder.courseCategoryId;
	this.courseCategory = builder.courseCategory;
    }

    /**
     * Empty Constructor for Course class
     */
    protected Course() {

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

    /**
     * Getter Method for Course Category ID
     * 
     * @return Course Category ID
     */

    public Long getCourseCategoryId() {
	return courseCategoryId;
    }

    /**
     * Setter Method for Course Category ID
     * 
     * @param Course Category ID
     */
    public void setCourseCategoryId(Long courseCategoryId) {
	this.courseCategoryId = courseCategoryId;
    }

    /**
     * @return the courseCategory
     */
    public CourseCategory getCourseCategory() {
	return courseCategory;
    }

    /**
     * @param courseCategory the courseCategory to set
     */
    public void setCourseCategory(CourseCategory courseCategory) {
	this.courseCategory = courseCategory;
    }

    @Override
    public String toString() {
	return "Course [id=" + id + ", name=" + name + ", detail=" + detail + ", isMandatory=" + isMandatory
		+ ", deadline=" + deadline + ", courseCategoryId= " + courseCategoryId + "]";
    }

    /**
     * Validate course name if null or empty
     * 
     * @param name Course Name
     */
    private void validateName(String name) {

	if (Objects.isNull(name) || name.trim()
					.isEmpty()) {

	    throw new IllegalArgumentException("Course should not be empty");

	}

    }

    /**
     * Validate Course Detail if null or empty
     * 
     * @param detail Course
     */
    private void validateDetail(String detail) {

	if (Objects.isNull(detail) || detail.trim()
					    .isEmpty()) {

	    throw new IllegalArgumentException("detail should not be empty");

	}

    }

    /**
     * Validate Course id if null or 0 Reason of deprecation: Cannot validate new
     * creation of course
     * 
     * @param id
     */
    @Deprecated
    private void validateId(Long id) {

	if (Objects.isNull(id) || Objects.equals(id, 0L)) {
	    throw new IllegalArgumentException("Id should not be empty");

	}
    }

    /**
     * Creates builder to build {@link Course}.
     * 
     * @return created builder
     */
    public static Builder builder() {
	return new Builder();
    }

    /**
     * Builder Class
     * 
     * @author c.lepiten
     * @author mi.aguinaldo
     */
    public static final class Builder {
	private Long id;
	private String name;
	private String detail;
	private String isMandatory;
	private String deadline;
	private Long courseCategoryId;
	private CourseCategory courseCategory;

	private Builder() {
	}

	public Builder withId(Long id) {
	    this.id = id;
	    return this;
	}

	public Builder withName(String name) {
	    this.name = name;
	    return this;
	}

	public Builder withDetail(String detail) {
	    this.detail = detail;
	    return this;
	}

	public Builder withIsMandatory(String isMandatory) {
	    this.isMandatory = isMandatory;
	    return this;
	}

	public Builder withDeadline(String deadline) {
	    this.deadline = deadline;
	    return this;
	}

	public Builder withCourseCategoryId(Long courseCategoryId) {
	    this.courseCategoryId = courseCategoryId;
	    return this;
	}

	public Builder withCourseCategory(CourseCategory courseCategory) {
	    this.courseCategory = courseCategory;
	    return this;
	}

	public Course build() {
	    return new Course(this);
	}
    }

}
