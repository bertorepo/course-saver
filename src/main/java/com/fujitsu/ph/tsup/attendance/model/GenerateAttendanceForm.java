package com.fujitsu.ph.tsup.attendance.model;

import java.util.Set;
import java.time.ZonedDateTime;

/**
 * <pre>
 * It is a JavaBean for GenerateAttendanceForm
 * In this class, Instances of the List Data required for the Initial setting of the database
 * </pre>
 * 
 * @version 0.01
 * @author k.abad
 *
 */

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign up
//System Name  :Attendance process 
//Class Name   :GenerateAttendanceForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/22/2020 | WS) K.Abad            | New Creation

public class GenerateAttendanceForm {
    /**
     * Course Schedule Detail Id
     */
    private Long id;
    
    /**
     * List of courses
     */
    private Set<GenerateAttendanceCourse> courses;
    
    /**
     * Course Name
     */
    private String courseName;
    
    /**
     * Instructor Name(LASTNAME, FIRSTNAME)
     */
    private String instructorName;
    
    /**
     * Venue Name
     */
    private String venueName;
    
    /**
     * Start Date and Time
     */
    private ZonedDateTime scheduledStartDateTime;
    
    /**
     * End Date and Time
     */
    private ZonedDateTime scheduledEndDateTime;
    
    /**
     * duration
     */
    private float duration;
    
    /**
     * Participants of the courses
     */
    private Set<AttendanceParticipantDetail> participants;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Set<GenerateAttendanceCourse> getCourses() {
        return courses;
    }
    public void setCourses(Set<GenerateAttendanceCourse> courses) {
        this.courses = courses;
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
    public Set<AttendanceParticipantDetail> getParticipants() {
        return participants;
    }
    public void setParticipants(Set<AttendanceParticipantDetail> participants) {
        this.participants = participants;
    }  
    
    @Override
    public String toString() {
        return "GenerateAttendanceForm [id=" + id + ", courses=" + courses + ", courseName=" + courseName + ", instructorName="
                + instructorName + ", venueName=" + venueName + ", scheduledStartDateTime=" + scheduledStartDateTime + ", scheduledEndDateTime=" + scheduledEndDateTime + ", duration=" + duration + ", participants=" + participants + ", ]";
    }
}
