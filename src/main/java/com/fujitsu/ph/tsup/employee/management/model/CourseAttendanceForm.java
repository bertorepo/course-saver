package com.fujitsu.ph.tsup.employee.management.model;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :CourseAttendanceForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/19/2020 |  WS) J. Iwarat   | New Creation
//==================================================================================================
/**
 * <pre>
 * JavaBean for CourseAttendanceForm
 * In this Class,Instances or fields of the List of the data for the initial setting of the data base 
 * <pre>
 * 
 * @version 0.01
 * @author j.iwarat
 */
import java.time.ZonedDateTime;

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
     * from the Employee Id
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseScheduleDetailId() {
        return courseScheduleDetailId;
    }

    public void setCourseScheduleDetailId(Long courseScheduleDetailId) {
        this.courseScheduleDetailId = courseScheduleDetailId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
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

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

}
