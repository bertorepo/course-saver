package com.fujitsu.ph.tsup.attendance.model;

import java.time.ZonedDateTime;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :CourseScheduleDetailForm.java
//
//<<Modification History>>
//Version | Date       | Updated By                             | Content
//--------+------------+----------------------------------------+-----------------------------------
//0.01    | 06/24/2020 |  WS) J. Iwarat                         | New Creation
//0.02    | 06/25/2020 |  WS) J. Iwarat                         | Update
//0.03    | 07/30/2020 |  WS) R. Ramos                          | Update
//0.04    | 08/26/2020 |  WS) K.Abad WS) J.Iwarat WS) R.Ramos   | Update
//==================================================================================================
/**
 * <pre>
 * JavaBean for CourseScheduleDetailForm
 * In this Class,Instances or fields of the List of the data for the initial setting of the data base 
 * 
 * <pre>
 * 
 * @version 0.04
 * @author k.abad
 * @author j.iwarat
 * @author r.ramos
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

    /**
     * Duration
     */
    private float duration;

    /**
     * @return
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return
     */
    public ZonedDateTime getScheduledStartDateTime() {
        return scheduledStartDateTime;
    }

    /**
     * @param scheduledStartDateTime
     */
    public void setScheduledStartDateTime(ZonedDateTime scheduledStartDateTime) {
        this.scheduledStartDateTime = scheduledStartDateTime;
    }

    /**
     * @return
     */
    public ZonedDateTime getScheduledEndDateTime() {
        return scheduledEndDateTime;
    }

    /**
     * @param scheduledEndDateTime
     */
    public void setScheduledEndDateTime(ZonedDateTime scheduledEndDateTime) {
        this.scheduledEndDateTime = scheduledEndDateTime;
    }
    
    /**
     * @return
     */
    public float getDuration() {
        return duration;
    }

    /**
     * @param duration
     */
    public void setDuration(float duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "CourseScheduleDetailForm [id=" + id + ", scheduledStartDateTime" + scheduledStartDateTime
                + ", scheduledEndDateTime=" + scheduledEndDateTime + ", duration=" + duration + "]";
    }
}
