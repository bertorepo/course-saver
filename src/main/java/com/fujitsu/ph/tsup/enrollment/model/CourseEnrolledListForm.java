package com.fujitsu.ph.tsup.enrollment.model;

//==================================================================================================
//$Id:PR01$
//Project Name :  Training Sign Up
//System Name  : Enrollment process
//Class Name   : CourseEnrolledListForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/22/2020 | WS) G.Cabiling        | New Creation
//0.01    | 07/30/2020 | WS) M.Lumontad        | Updated
//==================================================================================================

/** 
*<pre>  
* It is a JavaBean for CourseEnrolledListForm       
*</pre> 
*   
* @version 0.01
* @author m.lumontad 
* 
*/
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

public class CourseEnrolledListForm {

    /* From Date and Time */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
    private ZonedDateTime fromDateTime;

    /* To Date and Time */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
    private ZonedDateTime toDateTime;

    /* Set of Course Enrollment Form */
    private List<CourseEnrollmentForm> courseSchedules;

    /** From Date and Time Getter */
    public ZonedDateTime getFromDateTime() {
        return fromDateTime;
    }

    /** From Date and Time Setter */
    public void setFromDateTime(ZonedDateTime fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    /** To Date and Time Getter */
    public ZonedDateTime getToDateTime() {
        return toDateTime;
    }

    /** To Date and Time Setter */
    public void setToDateTime(ZonedDateTime toDateTime) {
        this.toDateTime = toDateTime;
    }

    /** Set of Course Enrollment Form Getter */
    public List<CourseEnrollmentForm> getCourseSchedules() {
        return courseSchedules;
    }

    /** Set of Course Enrollment Form Setter */
    public void setCourseScheduleDetailForm(List<CourseEnrollmentForm> courseSchedules) {
        this.courseSchedules = courseSchedules;
    }
    
    @Override
	public String toString() {
		return "CourseEnrolledListForm [FromDateTime = " + fromDateTime + ", ToDateTime = " + toDateTime + ","
				+ " CourseSchedules =" + courseSchedules + "]";
	}
}
