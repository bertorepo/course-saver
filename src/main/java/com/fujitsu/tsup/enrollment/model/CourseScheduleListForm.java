package com.fujitsu.tsup.enrollment.model;

//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :CourseScheduleListForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/23/2020 | WS) M. Lumontad       | New Creation
//==================================================================================================
/**
* <pre>
* JavaBean for CourseScheduleListForm.java
* <pre>
* 
* @version 0.01
* @author m.lumontad                          
*/
import java.time.ZonedDateTime;
import java.util.Set;

public class CourseScheduleListForm {
    /* From Date and Time */
    private ZonedDateTime fromDateTime;

    /* To Date and Time */
    private ZonedDateTime toDateTime;

    /* Set of Course Schedule Form */
    private Set<CourseScheduleDetailForm> courseSchedules;

    /** Course From Date Getter */
    public ZonedDateTime getFromDateTime() {
        return fromDateTime;
    }

    /** Course From Date Setter */
    public void setFromDateTime(ZonedDateTime fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    /** Course To Date Getter */
    public ZonedDateTime getToDateTime() {
        return toDateTime;
    }

    /** Course To Date Setter */
    public void setToDateTime(ZonedDateTime toDateTime) {
        this.toDateTime = toDateTime;
    }

    /** Course Schedule Set Getter */
    public Set<CourseScheduleDetailForm> getCourseSchedules() {
        return courseSchedules;
    }

    /** Course Schedule Set Setter */
    public void setCourseSchedules(Set<CourseScheduleDetailForm> courseSchedules) {
        this.courseSchedules = courseSchedules;
    }

    @Override
    public String toString() {
        return "CourseScheduleListForm [fromDateTime=" + fromDateTime + ", toDateTime=" + toDateTime
                + ", courseSchedules=" + courseSchedules + "]";
    }
}