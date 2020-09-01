package com.fujitsu.ph.tsup.attendance.model;

import java.time.ZonedDateTime;

import org.springframework.format.annotation.DateTimeFormat;
//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign up
//System Name  :Attendance process 
//Class Name   :ChangeStatusParticipant.java
//
//<<Modification History>>
//Version | Date       | Updated By                                   | Content
//--------+------------+----------------------------------------------+----------------------------
//0.01    | 06/22/2020 | WS) h.francisco                              | New Creation
//0.02    | 08/26/2020 | WS) K.Abad WS) J.Iwarat WS) R.Ramos          | Update
//==================================================================================================
/**
 * <pre>
 * It is a JavaBean for Change Status Course
 * In this class, Instances of the List Data required for the Initial setting of the database
 * </pre>
 * 
 * @version 0.02
 * @author k.abad
 * @author h.francisco
 * @author j.iwarat  
 * @author r.ramos  
 */
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
    private ZonedDateTime loginDateTime;
    
    /**
     * Participant's Logout Date and Time
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
    private ZonedDateTime logoutDateTime;
    
    /**
     * Email
     */
    private String email;

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

    /**
     * @return
     */
    public ZonedDateTime getLogoutDateTime() {
        return logoutDateTime;
    }

    /**
     * @param logoutDateTime
     */
    public void setLogoutDateTime(ZonedDateTime logoutDateTime) {
        this.logoutDateTime = logoutDateTime;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ChangeStatusParticipant [courseAttendanceId = " + courseAttendanceId + ", participantId = "
                + participantId + ", name = " + name + ", status = " + status + ", loginDateTime = " + loginDateTime
                + ", logoutDateTime = " + logoutDateTime + ", email = " + email + "]";
    }

}
