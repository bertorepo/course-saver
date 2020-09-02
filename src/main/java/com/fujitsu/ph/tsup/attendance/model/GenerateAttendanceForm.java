package com.fujitsu.ph.tsup.attendance.model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign up
//System Name  :Attendance process 
//Class Name   :GenerateAttendanceForm.java
//
//<<Modification History>>
//Version | Date       | Updated By                             | Content
//--------+------------+----------------------------------------+----------------------------------
//0.01    | 06/22/2020 | WS) K.Abad                             | New Creation
//0.02    | 07/15/2020 | WS) K.Abad                             | Update
//0.03    | 08/26/2020 | WS) K.Abad WS) J.Iwarat WS) R.Ramos    | Update
//==================================================================================================
/**
 * <pre>
 * It is a JavaBean for GenerateAttendanceForm
 * In this class, Instances of the List Data required for the Initial setting of the database
 * </pre>
 * 
 * @version 0.03
 * @author k.abad
 * @author j.iwarat  
 * @author r.ramos  
 *
 */
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
    private List<AttendanceParticipantDetail> participants;

    /**
     * Department Name
     */
    private String department;

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
    public Set<GenerateAttendanceCourse> getCourses() {
        return courses;
    }

    /**
     * @param courses
     */
    public void setCourses(Set<GenerateAttendanceCourse> courses) {
        this.courses = courses;
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
    public List<AttendanceParticipantDetail> getParticipants() {
        return participants;
    }

    /**
     * @param participants
     */
    public void setParticipants(List<AttendanceParticipantDetail> participants) {
        this.participants = participants;
    }

    /**
     * @return
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "GenerateAttendanceForm [id=" + id + ", courses=" + courses + ", courseName=" + courseName
                + ", instructorName=" + instructorName + ", venueName=" + venueName + ", scheduledStartDateTime="
                + scheduledStartDateTime + ", scheduledEndDateTime=" + scheduledEndDateTime + ", duration=" + duration
                + ", participants=" + participants + ", departments=" + department + "]";
    }
}
