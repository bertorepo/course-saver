package com.fujitsu.ph.tsup.attendance.model;

import java.util.Set;

/**
 * <pre>
 * JavaBean for Course Participants Form
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
//Class Name   :CourseParticipantsForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/23/2020 | WS) h.francisco       | New Creation
public class CourseParticipantsForm {

    /**
     * Course Schedule ID
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
     * Instructor Name (LASTNAME, FIRSTNAME)
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
     * Set of Course Schedule Details
     */
    private Set<CourseScheduleDetailForm> courseScheduleDetails;

    /**
     * Minimum Number of Participants
     */
    private int minRequired;

    /**
     * Maximum Number of Participants
     */
    private int maxAllowed;

    /**
     * Name of All Participants (LASTNAME, FIRSTNAME)
     */
    private Set<AttendanceParticipantDetail> participants;

    /**
     * Set of Course Schedule Form
     */
    private Set<CourseScheduleForm> courseSchedules;

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
    public Set<AttendanceParticipantDetail> getParticipants() {
        return participants;
    }

    /**
     * @param participants
     */
    public void setParticipants(Set<AttendanceParticipantDetail> participants) {
        this.participants = participants;
    }

    /**
     * @return
     */
    public Set<CourseScheduleForm> getCourseSchedules() {
        return courseSchedules;
    }

    /**
     * @param courseSchedules
     */
    public void setCourseSchedules(Set<CourseScheduleForm> courseSchedules) {
        this.courseSchedules = courseSchedules;
    }

}
