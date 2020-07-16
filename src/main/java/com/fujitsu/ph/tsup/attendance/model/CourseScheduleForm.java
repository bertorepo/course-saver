package com.fujitsu.ph.tsup.attendance.model;

import java.util.Set;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :CourseScheduleForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/23/2020 |  WS) J. Iwarat           | New Creation
//0.01 | 06/23/2020 |  WS) J. Iwarat           | Update
//==================================================================================================
/**
 * <pre>
 * JavaBean for CourseScheduleForm
 * 
 * <pre>
 * 
 * @version 0.02
 * @author j.iwarat
 */
public class CourseScheduleForm {

    /**
     * Course Schedule Id
     */
    private Long id;

    /**
     * Course Id
     */
    private Long courseId;

    /**
     * Course Name
     */
    private String courseName;

    /**
     * Instructor Id
     */
    private Long instructorId;

    /**
     * Instructor Name(LASTNAME, FIRSTNAME)
     */
    private String instructorName;

    /**
     * Venue Id
     */
    private Long venueId;

    /**
     * Venue Name
     */
    private String venueName;

    /**
     * Set of course schedule details
     */
    private Set<CourseScheduleDetailForm> courseScheduleDetails;

    /**
     * Minimum number of participants
     */
    private int minRequired;

    /**
     * Maximum number of participants
     */
    private int maxAllowed;

    /**
     * Total Number of Participants currently enrolled
     */
    private int totalParticipants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public Set<CourseScheduleDetailForm> getCourseScheduleDetails() {
        return courseScheduleDetails;
    }

    public void setCourseScheduleDetails(Set<CourseScheduleDetailForm> courseScheduleDetails) {
        this.courseScheduleDetails = courseScheduleDetails;
    }

    public int getMinRequired() {
        return minRequired;
    }

    public void setMinRequired(int minRequired) {
        this.minRequired = minRequired;
    }

    public int getMaxAllowed() {
        return maxAllowed;
    }

    public void setMaxAllowed(int maxAllowed) {
        this.maxAllowed = maxAllowed;
    }

    public int getTotalParticipants() {
        return totalParticipants;
    }

    public void setTotalParticipants(int totalParticipants) {
        this.totalParticipants = totalParticipants;
    }

    @Override
    public String toString() {
        return "CourseScheduleForm [id = " + id + ", courseId = " + courseId + ", courseName = " + courseName
                + ", instructorId = " + instructorId + ", instructorName = " + instructorName + ", venueId = " + venueId
                + ", venueName = " + venueName + ", courseScheduleDetails = " + courseScheduleDetails
                + ", minRequired = " + minRequired + ", maxAllowed = " + maxAllowed + ", totalParticipants = "
                + totalParticipants + "]";
    }

}
