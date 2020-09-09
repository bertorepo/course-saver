package com.fujitsu.ph.tsup.scheduling.model;

import java.util.List;

public class ChangeStatusScheduleForm {
	/**
     * Course Schedule Id
     */
    private Long id;
    
    /**
     * Course Id
     */
    private Long courseId;
    
    /**
     * Instructor Id
     */
    private Long instructorId;
    
    /**
     * Instructor Name
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
     * Course Name
     */
    private String courseName;
    
    
    /**
     * List of course schedule details
     */
    private List<CourseScheduleDetailForm> courseScheduleDetailList;

    /**
     * String(Active/Done)
     */
    private String status;
    

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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public List<CourseScheduleDetailForm> getCourseScheduleDetailList() {
		return courseScheduleDetailList;
	}

	public void setCourseScheduleDetailList(List<CourseScheduleDetailForm> courseScheduleDetailList) {
		this.courseScheduleDetailList = courseScheduleDetailList;
	}

	@Override
	public String toString() {
		return "ChangeStatusScheduleForm [id=" + id + ", courseId=" + courseId + ", instructorId=" + instructorId
				+ ", instructorName=" + instructorName + ", venueId=" + venueId + ", venueName=" + venueName
				+ ", courseName=" + courseName + ", courseScheduleDetailList=" + courseScheduleDetailList + ", status="
				+ status + "]";
	}

	
    
}
