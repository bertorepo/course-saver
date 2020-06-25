package com.fujitsu.ph.tsup.attendance.model;

import java.time.ZonedDateTime;
import java.util.Set;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :CourseScheduleListForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/23/2020 |  WS) J. Iwarat   | New Creation
//0.01 | 06/25/2020 |  WS) J. Iwarat   | Update
//==================================================================================================
/**
 * <pre>
 * JavaBean for CourseScheduleListForm
 * 
 * <pre>
 * 
 * @version 0.01
 * @author j.iwarat
 */
public class CourseScheduleListForm {
    /**
     * From Date and Time
     */
    private ZonedDateTime fromDateTime;

    /**
     * To Date and Time
     */
    private ZonedDateTime toDateTime;

    /**
     * Set of Course Schedule Form
     */
    private Set<CourseScheduleForm> courseSchedules;

    public ZonedDateTime getFromDateTime() {
        return fromDateTime;
    }

    public void setFromDateTime(ZonedDateTime fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    public ZonedDateTime getToDateTime() {
        return toDateTime;
    }

    public void setToDateTime(ZonedDateTime toDateTime) {
        this.toDateTime = toDateTime;
    }

    public Set<CourseScheduleForm> getCourseSchedules() {
        return courseSchedules;
    }

    public void setCourseSchedules(Set<CourseScheduleForm> courseSchedules) {
        this.courseSchedules = courseSchedules;
    }

    @Override
    public String toString() {
        return "CourseScheduleListForm [fromDateTime=" + fromDateTime + ", toDateTime" + toDateTime
                + ", courseSchedules=" + courseSchedules + "]";
    }

}
