package com.fujitsu.ph.tsup.course.management.model;

//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :CourseEnrolledListForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/19/2020 | WS) M. Lumontad       | New Creation
//==================================================================================================
/**
* <pre>
* JavaBean for CourseEnrolledListForm
* <pre>
* 
* @version 0.01
* @author m.lumontad                          
*/
import java.time.ZonedDateTime;
import java.util.Set;

public class CourseEnrolledListForm {

    /* From Date and Time */
    private ZonedDateTime fromDateTime;

    /* To Date and Time */
    private ZonedDateTime toDateTime;

    /* Set of Course Schedule Form */
    private Set<CourseEnrollmentForm> courseSchedules;

    /* From Date and Time Getter */
    public ZonedDateTime fromDateTime() {
        return fromDateTime;
    }

    /* From Date and Time Setter */
    public void setFromDateTime(ZonedDateTime fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    /* To Date and Time Getter */
    public ZonedDateTime toDateTime() {
        return toDateTime;
    }

    /* To Date and Time Setter */
    public void setToDateTime(ZonedDateTime toDateTime) {
        this.toDateTime = toDateTime;
    }

    /* Set of Course Schedule Form Getter */
    public Set<CourseEnrollmentForm> getCourseSchedules() {
        return courseSchedules;
    }

    /* Set of Course Schedule Form Setter */
    public void setCourseSchedules(Set<CourseEnrollmentForm> courseSchedules) {
        this.courseSchedules = courseSchedules;
    }

    @Override
    public String toString() {
        return "CourseEnrolledListForm [fromDateTime=" + fromDateTime + ", toDateTime=" + toDateTime
                + ", courseSchedules=" + courseSchedules + "]";
    }

}
