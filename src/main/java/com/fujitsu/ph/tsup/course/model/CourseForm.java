/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.model;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Course Management
//Class Name   : CourseForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2020/08/28 | WS) c.lepiten         | Initial Version
//0.02    | 2021/04/19 | WS) st.diaz           | Updated
//0.03    | 2021/05/10 | WS) D.Escala          | Updated
//0.04    | 2021/05/28 | WS) mi.aguinaldo      | Updated
//==================================================================================================

public class CourseForm {

    private Long id;
    private String name;
    private String detail;
    private String isMandatory = "No";
    private String deadline;
    private Long courseCategoryId;

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

    @Override
    public String toString() {
	return "Course [id=" + id + ", name=" + name + ", detail=" + detail + ", isMandatory=" + isMandatory
		+ ", deadline=" + deadline + ", courseCategoryId= " + courseCategoryId + "]";
    }
}
