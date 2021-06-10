/**
 * Copyright (C) 2019 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.search;

import org.apache.commons.lang3.StringUtils;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Course Management
//Class Name   : CourseSearchFilter.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2021/06/09 | WS) m.aguinaldo       | Initial Version
//==================================================================================================

public class CourseSearchFilter {
    private String courseName;
    private String courseCategory;
    private String mandatory;
    private String deadline;
    
    public CourseSearchFilter() {
	super();
    }

    
    /**
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }



    /**
     * @return the courseCategory
     */
    public String getCourseCategory() {
        return courseCategory;
    }


    /**
     * @return the mandatory
     */
    public String getMandatory() {
        return mandatory;
    }


    
    /**
     * @return the deadline
     */
    public String getDeadline() {
        return deadline;
    }


    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

  
    /**
     * @param courseCategory the courseCategory to set
     */
    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    /**
     * @param mandatory the mandatory to set
     */
    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }


    /**
     * @param deadline the deadline to set
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    // Method for checking if the search is empty
    public boolean isSearchEmpty() {
	return StringUtils.isAllBlank(getCourseName(),getCourseCategory(),getMandatory(),getDeadline());
    }


    @Override
    public String toString() {
	return "CourseSearchFilter [courseName=" + courseName + ", courseCategory=" + courseCategory + ", mandatory="
		+ mandatory + ", deadline=" + deadline + "]";
    }

}
