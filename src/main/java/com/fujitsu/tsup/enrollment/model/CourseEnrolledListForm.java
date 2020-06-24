package com.fujitsu.tsup.enrollment.model;

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
//==================================================================================================

/** 
*<pre>  
* It is a JavaBean for CourseEnrolledListForm       
*</pre> 
*   
* @version 0.01
* @author g.cabiling    
* 
*/
import java.time.ZonedDateTime;
import java.util.Set;

public class CourseEnrolledListForm {

    /* From Date and Time */
    private ZonedDateTime fromDateTime;

    /* To Date and Time */
    private ZonedDateTime toDateTime;

    /* Set of Course Enrollment Form */
    private Set<CourseEnrollmentForm> courseSchedules;

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
    public Set<CourseEnrollmentForm> getCourseSchedules() {
        return courseSchedules;
    }

    /** Set of Course Enrollment Form Setter */
    public void setCourseScheduleDetailForm(Set<CourseEnrollmentForm> courseSchedules) {
        this.courseSchedules = courseSchedules;
    }
}
