package com.fujitsu.ph.tsup.attendance.management.model;

import java.time.ZonedDateTime;

/**
 * <pre>
 * It is a JavaBean for Change Status Course
 * In this class, Instances of the List Data required for the Initial setting of the database
 * </pre>
 * 
 * @version 0.01
 * @author h.francisco
 *
 */

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign up
//System Name  :Attendance process 
//Class Name   :ChangeStatusParticipant.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/22/2020 | WS) h.francisco       | New Creation
public class ChangeStatusParticipant {

    /**
     * Course Attendance Id
     */
    private Long courseAttendanceId;

    /**
     * Participant Id
     */
    private Long participantId;

    /**
     * Participant's Name
     */
    private String name;

    /**
     * Participant's Status
     */
    private char status;

    /**
     * Participant's Login Date and Time
     */
    private ZonedDateTime loginDateTime;

    /**
     * @return
     */
    public Long getCourseAttendanceId() {
        return courseAttendanceId;
    }

    /**
     * @param courseAttendanceId
     */
    public void setCourseAttendanceId(Long courseAttendanceId) {
        this.courseAttendanceId = courseAttendanceId;
    }

    /**
     * @return
     */
    public Long getParticipantId() {
        return participantId;
    }

    /**
     * @param participantId
     */
    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public char getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(char status) {
        this.status = status;
    }

    /**
     * @return
     */
    public ZonedDateTime getLoginDateTime() {
        return loginDateTime;
    }

    /**
     * @param loginDateTime
     */
    public void setLoginDateTime(ZonedDateTime loginDateTime) {
        this.loginDateTime = loginDateTime;
    }

    @Override
    public String toString() {
        return "ChangeStatusParticipant [courseAttendanceId = " + courseAttendanceId + ", participantId = "
                + participantId + ", name = " + name + ", status = " + status + ", loginDateTime = " + loginDateTime
                + "]";
    }

}
