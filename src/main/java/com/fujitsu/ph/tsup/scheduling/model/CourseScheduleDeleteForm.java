package com.fujitsu.ph.tsup.scheduling.model;

import java.util.List;
import java.util.Set;

public class CourseScheduleDeleteForm {
	
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
     * Course Details
     */
    private String courseDetails;

    /**
     * Set of course schedule details
     */
    private Set<CourseScheduleDetailForm> courseScheduleDetails;
    
    /**
     * List of Course Schedule Detail
     */
    private List<CourseScheduleDetailForm> courseScheduleDetailList;

	public List<CourseScheduleDetailForm> getCourseScheduleDetailList() {
		return courseScheduleDetailList;
	}

	public void setCourseScheduleDetailList(List<CourseScheduleDetailForm> courseScheduleDetailList) {
		this.courseScheduleDetailList = courseScheduleDetailList;
	}

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

	public String getCourseDetails() {
		return courseDetails;
	}

	public void setCourseDetails(String courseDetails) {
		this.courseDetails = courseDetails;
	}

	public Set<CourseScheduleDetailForm> getCourseScheduleDetails() {
		return courseScheduleDetails;
	}

	public void setCourseScheduleDetails(Set<CourseScheduleDetailForm> courseScheduleDetails) {
		this.courseScheduleDetails = courseScheduleDetails;
	}

	@Override
	public String toString() {
		return "CourseScheduleDeleteForm [id=" + id + ", courseId=" + courseId + ", courseName=" + courseName
				+ ", instructorName=" + instructorName + ", venueId=" + venueId + ", venueName=" + venueName
				+ ", courseDetails=" + courseDetails + ", courseScheduleDetails=" + courseScheduleDetails
				+ ", courseScheduleDetailList=" + courseScheduleDetailList + "]";
	}

	
    
}
