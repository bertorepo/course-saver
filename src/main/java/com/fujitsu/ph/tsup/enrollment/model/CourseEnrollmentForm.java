package com.fujitsu.ph.tsup.enrollment.model;

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

public class CourseEnrollmentForm {
    /* Course ID */
    private Long id;
    
    /* Course Id*/
    private Long courseId;

	/* Course Schedule ID */
    private Long courseScheduleId;

    /* Course Name */
    private String courseName;
    
    /* Course participant ID */
    private Long participantId;

    public Long getParticipantId() {
        return participantId;
    }
    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }
    

    /* Instructor Name (LASTNAME, FIRSTNAME) */
    private String instructorName;

    /* Venue Name */
    private String venueName;
    
    private String courseStatus;
    
    /* Email Address */
    //NEW
    private String emailAddress;

    /* Course Participant ID */
    private CourseScheduleDetailForm courseScheduleDetails;

    /* Course Participant ID */
    private ZonedDateTime registrationDate;
    
    private String attendanceStatus;
    
    private String courseDetails;


    public String getCourseDetails() {
        return courseDetails;
    }
    public void setCourseDetails(String courseDetails) {
        this.courseDetails = courseDetails;
    }
    /** Course Participant ID Getter */
    public Long getId() {
        return id;
    }
    /* Course Id Getter*/
	public Long getCourseId() {
		return courseId;
	}
	
	/* Course Id Setter*/
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
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
    
    /** setCourseStatus Getter */
    public String getCourseStatus() {
        return courseStatus;
    }
    
    public String getEmailAddress() {
		return emailAddress;
	}
    
    public String getAttendanceStatus() {
        return attendanceStatus;
    }

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

    /** Course Schedule Details Getter */
    public CourseScheduleDetailForm getCourseScheduleDetails() {
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
    
    /** setCourseStatus Setter */
    public void setCourseStatus(String courseStatus) {
    	this.courseStatus = courseStatus;
	}

    /** CourseScheduleDetails Setter */
    public void setCourseScheduleDetails(CourseScheduleDetailForm courseScheduleDetails) {
        this.courseScheduleDetails = courseScheduleDetails;
    }

    /** Registration Date Setter */
    public void setRegistrationDate(ZonedDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
    
    @Override
    public String toString() {
        return "CourseEnrollmentForm [id=" + id + ", courseId=" + courseId
                + ", courseScheduleId=" + courseScheduleId + ", courseName="
                + courseName + ", participantId=" + participantId
                + ", instructorName=" + instructorName + ", venueName="
                + venueName + ", courseStatus=" + courseStatus
                + ", emailAddress=" + emailAddress
                + ", courseScheduleDetails=" + courseScheduleDetails
                + ", registrationDate=" + registrationDate
                + ", attendanceStatus=" + attendanceStatus + ", courseDetails="
                + courseDetails + "]";
    }
	
}
