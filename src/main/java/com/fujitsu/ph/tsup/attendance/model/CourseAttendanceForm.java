package com.fujitsu.ph.tsup.attendance.model;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :CourseAttendanceForm.java
//
//<<Modification History>>
//Version | Date       | Updated By                             | Content
//--------+------------+----------------------------------------+-----------------------------------
//0.01    | 06/19/2020 |  WS) J.Iwarat                          | New Creation
//0.02    | 06/25/2020 |  WS) J.Iwarat                          | Update
//0.03    | 08/26/2020 |  WS) K.Abad WS) J.Iwarat WS) R.Ramos   | Update
//==================================================================================================
/**
 * <pre>
 * JavaBean for CourseAttendanceForm
 * In this Class,Instances or fields of the List of the data for the initial setting of the data base 
 * <pre>
 * 
 * @version 0.03
 * @author k.abad
 * @author j.iwarat  
 * @author r.ramos  
 */
import java.time.ZonedDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class CourseAttendanceForm {
    /**
     * Course Attendance Id
     */
    private Long id;

    /**
     * from the Course Schedule detail id
     */
    private Long courseScheduleDetailId;

    /**
     * Course Name
     */
    private String courseName;

    /**
     * Instructor Name (LASTNAME, FIRSTNAME)
     */
    private String instructorName;

    /**
     * Venue Name
     */
    private String venueName;

    /**
     * from the participant Id
     */
    private Long participantId;

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
     * Email address
     */
    private String email;

    /**
     * Course Description
     */
    private String courseDescription;

    /**
     * Login Date Time
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime loginDateTime;
    
    /**
     * Logout Date Time
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime logoutDateTime;

    /**
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return
     */
    public Long getCourseScheduleDetailId() {
        return courseScheduleDetailId;
    }

    /**
     * @param courseScheduleDetailId
     */
    public void setCourseScheduleDetailId(Long courseScheduleDetailId) {
        this.courseScheduleDetailId = courseScheduleDetailId;
    }

    /**
     * @return
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return
     */
    public String getInstructorName() {
        return instructorName;
    }

    /**
     * @param instructorName
     */
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    /**
     * @return
     */
    public String getVenueName() {
        return venueName;
    }

    /**
     * @param venueName
     */
    public void setVenueName(String venueName) {
        this.venueName = venueName;
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

    /**
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return
     */
    public String getCourseDescription() {
        return courseDescription;
    }

    /**
     * @param courseDescription
     */
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
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

    public String toString() {

        return "CourseAttendanceForm [id=" + id + ", courseScheduleDetailId=" + courseScheduleDetailId + ", courseName="
                + courseName + ", instructorName=" + instructorName + ", venueName=" + venueName + ", participantId="
                + participantId + ", scheduledStartDateTime=" + scheduledStartDateTime + ", scheduledEndDateTime="
                + scheduledEndDateTime + ", duration=" + duration + ", logoutDateTime=" + logoutDateTime
                + ", loginDateTime=" + loginDateTime + ", email=" + email + ", courseDescription=" + courseDescription + "]";     
    }
}
