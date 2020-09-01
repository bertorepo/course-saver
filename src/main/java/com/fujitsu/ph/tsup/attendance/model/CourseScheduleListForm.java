package com.fujitsu.ph.tsup.attendance.model;

import java.time.ZonedDateTime;
import java.util.List;
//import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :CourseScheduleListForm.java
//
//<<Modification History>>
//Version | Date       | Updated By                             | Content
//--------+------------+----------------------------------------+---------------------------------------------------
//0.01    | 06/23/2020 |  WS) J. Iwarat                         | New Creation
//0.02    | 06/25/2020 |  WS) J. Iwarat                         | Update
//0.03    | 08/26/2020 |  WS) K.Abad WS) J.Iwarat WS) R.Ramos   | Update
//==================================================================================================
/**
 * <pre>
 * JavaBean for CourseScheduleListForm
 * In this Class,Instances or fields of the List of the data for the initial setting of the data base 
 * 
 * <pre>
 * 
 * @version 0.03
 * @author k.abad
 * @author j.iwarat
 * @author r.ramos
 */
public class CourseScheduleListForm {
    /**
     * From Date and Time
     */
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
    private ZonedDateTime fromDateTime;

    /**
     * To Date and Time
     */
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
    private ZonedDateTime toDateTime;

    /**
     * List of Course Schedule Form
     */
    private List<CourseScheduleForm> courseSchedules;

    /**
     * @return
     */
    public List<CourseScheduleForm> getCourseSchedules() {
        return courseSchedules;
    }

    /**
     * @param courseSchedules
     */
    public void setCourseSchedules(List<CourseScheduleForm> courseSchedules) {
        this.courseSchedules = courseSchedules;
    }

    /**
     * @return
     */
    public ZonedDateTime getFromDateTime() {
        return fromDateTime;
    }

    /**
     * @param fromDateTime
     */
    public void setFromDateTime(ZonedDateTime fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    /**
     * @return
     */
    public ZonedDateTime getToDateTime() {
        return toDateTime;
    }

    /**
     * @param toDateTime
     */
    public void setToDateTime(ZonedDateTime toDateTime) {
        this.toDateTime = toDateTime;
    }

    @Override
    public String toString() {
        return "CourseScheduleListForm [fromDateTime=" + fromDateTime + ", toDateTime" + toDateTime
                + ", courseSchedules=" + courseSchedules + "]";
    }

}
