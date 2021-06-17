package com.fujitsu.ph.tsup.enrollment.model;
//=================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :CourseScheduleForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+--------------------------------------------------
//0.02    | 06/24/2020 | WS) J.Yu              | New Creation
//0.03    | 06/14/2021 | WS) L.Celoso          | Update
//0.03    | 06/16/2021 | WS) M.Taboada         | Update
//0.03    | 06/16/2021 | WS) K.Sevilla         | Updated
//=================================================================================================
/**
* <pre>
* JavaBean for CourseScheduleForm.java
* <pre>
* 
* @version 0.01
* @author j.yu                       
*/

public class CourseScheduleForm {
    /* Course Schedule Id */
    private Long id;

    /* Course Id */
    private Long courseId;

    /* Course Name */
    private String courseName;

    /* Instructor Id */
    private Long instructorId;

    /* Instructor Name(LASTNAME, FIRSTNAME) */
    private String instructorName;
    
    private String mandatory; // added
    
    private String deadline; // added

    /* Venue Id */
    private Long venueId;

    /* Venue Name */
    private String venueName;
    
    private String courseStatus;

    /* Set of course schedule details */
    private CourseScheduleDetailForm courseScheduleDetail;

    /* Minimum number of participants */
    private int minRequired;

    /* Maximum number of participants */
    private int maxAllowed;

    /* Total Number of Participants currently enrolled */
    private int totalParticipants;
    
    private String courseDetails;

    public CourseScheduleDetailForm getCourseScheduleDetail() {
        return courseScheduleDetail;
    }

    public void setCourseScheduleDetail(
            CourseScheduleDetailForm courseScheduleDetail) {
        this.courseScheduleDetail = courseScheduleDetail;
    }

    public String getCourseDetails() {
        return courseDetails;
    }

    public void setCourseDetails(String courseDetails) {
        this.courseDetails = courseDetails;
    }

    /** get Course Schedule id */
    public Long getId() {
        return id;
    }

    /** set id */
    public void setId(Long id) {
        this.id = id;
    }

    /** get Course id */
    public Long getCourseId() {
        return courseId;
    }

    /** set Course id */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /** get Course Name */
    public String getCourseName() {
        return courseName;
    }

    /** set Course Name */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /** get Instructor Id */
    public Long getInstructorId() {
        return instructorId;
    }

    /** set Instructor Id */
    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    /** get Instructor Name(LASTNAME, FIRSTNAME) */
    public String getInstructorName() {
        return instructorName;
    }

    /** set Instructor Name(LASTNAME, FIRSTNAME) */
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    /** get Venue Id */
    public Long getVenueId() {
        return venueId;
    }

    /** set Venue Id */
    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    /** get Venue Name */
    public String getVenueName() {
        return venueName;
    }

    /** set Venue Name */
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
    
    /** setCourseStatus Getter */
    public String getCourseStatus() {
        return courseStatus;
    }

    /** setCourseStatus Setter */
    public void setCourseStatus(String courseStatus) {
    	this.courseStatus = courseStatus;
    }	

    /** get Set of course schedule details */
    public CourseScheduleDetailForm getCourseScheduleDetails() {
        return courseScheduleDetail;
    }

    /** set Set of course schedule details */
    public void setCourseScheduleDetails(CourseScheduleDetailForm courseScheduleDetail) {
        this.courseScheduleDetail = courseScheduleDetail;
    }

    /** get Minimum number of participants */
    public int getMinRequired() {
        return minRequired;
    }

    /** set Minimum number of participants */
    public void setMinRequired(int minRequired) {
        this.minRequired = minRequired;
    }

    /** get Maximum number of participants */
    public int getMaxAllowed() {
        return maxAllowed;
    }

    /** set Maximum number of participants */
    public void setMaxAllowed(int maxAllowed) {
        this.maxAllowed = maxAllowed;
    }

    /** get Total Number of Participants currently enrolled */
    public int getTotalParticipants() {
        return totalParticipants;
    }

    /** set Total Number of Participants currently enrolled */
    public void setTotalParticipants(int totalParticipants) {
        this.totalParticipants = totalParticipants;
    }
    
    
    public String getDeadline() {
		return deadline;
	}
    
    public void setDeadline(String deadline) {
    	this.deadline = deadline;
    	
    }
    
	public String getMandatory() {
		return mandatory;
	}
	
	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}


    @Override
    public String toString() {
        return "CourseScheduleForm [id=" + id + ", courseId=" + courseId
                + ", courseName=" + courseName + ", instructorId="
                + instructorId + ", instructorName=" + instructorName
                + ", deadline= " + deadline + ", mandatory = " +mandatory
                + ", venueId=" + venueId + ", venueName=" + venueName  + ", courseStatus=" + courseStatus
                + ", courseScheduleDetail=" + courseScheduleDetail
                + ", minRequired=" + minRequired + ", maxAllowed=" + maxAllowed
                + ", totalParticipants=" + totalParticipants
                + ", courseDetails=" + courseDetails + "]";
    }

}