package com.fujitsu.tsup.enrollment.model;

//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :CourseScheduleDetailForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/23/2020 | WS) K.Freo            | New Creation
//==================================================================================================
/**
* <pre>
* JavaBean for CourseScheduleDetailForm
* <pre>
* 
* @version 0.01
* @author k.freo                   
*/
import java.time.ZonedDateTime;

public class CourseScheduleDetailForm {
    /* Course ID */
    private Long id;

    /* Course Schedule Start Date */
    private ZonedDateTime scheduledStartDateTime;

    /* Course Schedule End Date */
    private ZonedDateTime scheduledEndDateTime;

    /* Course Duration */
    private float duration;

    /** Course ID Getter */
    public Long getId() {
        return id;
    }

    /** Course ID Setter */
    public void setId(Long id) {
        this.id = id;
    }

    /** Course Schedule Start Date Getter */
    public ZonedDateTime getScheduledStartDateTime() {
        return scheduledStartDateTime;
    }

    /** Course Schedule Start Date Setter */
    public void setScheduledStartDateTime(ZonedDateTime scheduledStartDateTime) {
        this.scheduledStartDateTime = scheduledStartDateTime;
    }

    /** Course Schedule End Date Getter */
    public ZonedDateTime getScheduledEndDateTime() {
        return scheduledEndDateTime;
    }

    /** Course Schedule End Date Setter */
    public void setScheduledEndDateTime(ZonedDateTime scheduledEndDateTime) {
        this.scheduledEndDateTime = scheduledEndDateTime;
    }

    /** Course Duration Getter */
    public float getDuration() {
        return duration;
    }

    /** Course Duration Setter */
    public void setDuration(float duration) {
        this.duration = duration;
    }
    
    public String toString() {
        return "CourseScheduleDetailForm [id=" + id + ", scheduledStartDateTime=" + scheduledStartDateTime
                + ", scheduledEndDateTime=" + scheduledEndDateTime + ", duration=" + duration + "]";
    }
}
