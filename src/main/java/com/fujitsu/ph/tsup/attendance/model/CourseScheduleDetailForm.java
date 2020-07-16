package com.fujitsu.ph.tsup.attendance.model;

import java.time.ZonedDateTime;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :CourseScheduleDetailForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/24/2020 |  WS) J. Iwarat   | New Creation
//0.01 | 06/25/2020 |  WS) J. Iwarat   | Update
//==================================================================================================
/**
 * <pre>
 * JavaBean for CourseScheduleDetailForm
 * 
 * <pre>
 * 
 * @version 0.01
 * @author j.iwarat
 */
public class CourseScheduleDetailForm {

    /**
     * Id
     */
    private Long id;

    /**
     * Start Date and Time
     */
    private ZonedDateTime scheduledStartDateTime;

    /**
     * End Date and Time
     */
    private ZonedDateTime scheduledEndDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getScheduledStartDateTime() {
        return scheduledStartDateTime;
    }

    public void setScheduledStartDateTime(ZonedDateTime scheduledStartDateTime) {
        this.scheduledStartDateTime = scheduledStartDateTime;
    }

    public ZonedDateTime getScheduledEndDateTime() {
        return scheduledEndDateTime;
    }

    public void setScheduledEndDateTime(ZonedDateTime scheduledEndDateTime) {
        this.scheduledEndDateTime = scheduledEndDateTime;
    }

    @Override
    public String toString() {
        return "CourseScheduleDetailForm [id=" + id + ", scheduledStartDateTime" + scheduledStartDateTime
                + ", scheduledEndDateTime=" + scheduledEndDateTime + "]";
    }
}
