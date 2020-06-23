package com.fujitsu.tsup.enrollment.model;

//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :CourseEnrollmentForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/23/2020 | WS) M. Rivera         | New Creation
//==================================================================================================
/**
* <pre>
* JavaBean for CourseEnrollmentForm
* <pre>
* 
* @version 0.01
* @author m.rivera                          
*/

import java.time.ZonedDateTime;
import java.util.Set;

public class CourseEnrollmentForm {
    /* Course Participant ID */
    private Long id;

    /* Course Schedule ID */
    private Long courseScheduleId;

    /* Course Name */
    private String courseName;

    /* Instructor Name (LASTNAME, FIRSTNAME) */
    private String instructorName;

    /* Venue Name */
    private String venueName;

    /* Course Participant ID */
    private Set<CourseScheduleDetailForm> courseScheduleDetails;

    /* Course Participant ID */
    private ZonedDateTime registrationDate;

    /** Course Participant ID Getter */
    public Long getId() {
        return id;
    }

    /** Schedule ID Getter */
    public Long getCourseScheduleId() {
        return courseScheduleId;
    }

    /** Course Name Getter */
    public String getCourseName() {
        return courseName;
    }

    /** Instructor Name Getter */
    public String getInstructorName() {
        return instructorName;
    }

    /** Venue Name Getter */
    public String getVenueName() {
        return venueName;
    }

    /** Course Schedule Details Getter */
    public Set<CourseScheduleDetailForm> getCourseScheduleDetails() {
        return courseScheduleDetails;
    }

    /** Registration Date Getter */
    public ZonedDateTime getRegistrationDate() {
        return registrationDate;
    }

    /** Course Participant ID Setter */
    public void setId(Long id) {
        this.id = id;
    }

    /** Course Schedule ID Setter */
    public void setCourseScheduleId(Long courseScheduleId) {
        this.courseScheduleId = courseScheduleId;
    }

    /** Course Name Setter */
    public void setCourseName(String courseName) {
        this.courseName = courseName;

    }

    /** Instructor ID Setter */
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;

    }

    /** Venue Name Setter */
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    /** CourseScheduleDetails Setter */
    public void setCourseScheduleDetails(Set<CourseScheduleDetailForm> courseScheduleDetails) {
        this.courseScheduleDetails = courseScheduleDetails;
    }

    /** Registration Date Setter */
    public void setRegistrationDate(ZonedDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
