package com.fujitsu.ph.tsup.attendance.model;

import java.util.Set;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :CourseScheduleForm.java
//
//<<Modification History>>
//Version | Date       | Updated By                             | Content
//--------+------------+----------------------------------------+-----------------------------------
//0.01    | 06/23/2020 |  WS) J. Iwarat                         | New Creation
//0.02    | 07/16/2020 |  WS) J. Iwarat                         | Update
//0.03    | 08/26/2020 |  WS) K.Abad WS) J.Iwarat WS) R.Ramos   | Update
//==================================================================================================
/**
 * <pre>
 * JavaBean for CourseScheduleForm
 * In this Class,Instances or fields of the List of the data for the initial setting of the data base 
 * 
 * <pre>
 * 
 * @version 0.03
 * @author k.abad
 * @author j.iwarat
 * @author r.ramos
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
    public Long getCourseId() {
        return courseId;
    }

    /**
     * @param courseId
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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
    public Long getInstructorId() {
        return instructorId;
    }

    /**
     * @param instructorId
     */
    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
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
    public Long getVenueId() {
        return venueId;
    }

    /**
     * @param venueId
     */
    public void setVenueId(Long venueId) {
        this.venueId = venueId;
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
    public Set<CourseScheduleDetailForm> getCourseScheduleDetails() {
        return courseScheduleDetails;
    }

    /**
     * @param courseScheduleDetails
     */
    public void setCourseScheduleDetails(Set<CourseScheduleDetailForm> courseScheduleDetails) {
        this.courseScheduleDetails = courseScheduleDetails;
    }

    /**
     * @return
     */
    public int getMinRequired() {
        return minRequired;
    }

    /**
     * @param minRequired
     */
    public void setMinRequired(int minRequired) {
        this.minRequired = minRequired;
    }

    /**
     * @return
     */
    public int getMaxAllowed() {
        return maxAllowed;
    }

    /**
     * @param maxAllowed
     */
    public void setMaxAllowed(int maxAllowed) {
        this.maxAllowed = maxAllowed;
    }

    /**
     * @return
     */
    public int getTotalParticipants() {
        return totalParticipants;
    }

    /**
     * @param totalParticipants
     */
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
