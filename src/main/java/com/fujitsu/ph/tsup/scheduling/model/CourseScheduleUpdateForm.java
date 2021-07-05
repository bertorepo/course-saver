package com.fujitsu.ph.tsup.scheduling.model;

import java.util.List;
import java.util.Set;

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: CourseScheduleUpdateForm.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/29/2021 | WS) J. Atendido | Added Venue overlap and edited MaxAllowed
//=======================================================


public class CourseScheduleUpdateForm {

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
     * Venue Id
     */
    private Long venueId;
    
    /**
     * Venue Overlap
     */
    private boolean venueOverlap;
    
    /**
     * Minimum Required Participants
     */
    private int minRequired;
    
    /**
     * Maximum Allowed Participants
     */
    private Integer maxAllowed;
    
    
    /**
     * Set of Course Schedule Details
     */
    private Set<CourseScheduleDetailForm> courseScheduleDetails;
    
    /**
     * Set of Instructors
     */
    private Set<InstructorForm> instructors;
    
    /**
     * Set of Venues
     */
    private Set<VenueForm> venues;
    
    /**
     * List of Course Schedule Detail
     */
    private List<CourseScheduleDetailForm> courseScheduleDetailList;

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

	public Long getVenueId() {
		return venueId;
	}

	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}
	
    public boolean isVenueOverlap() {
		return venueOverlap;
	}

	public void setVenueOverlap(boolean venueOverlap) {
		this.venueOverlap = venueOverlap;
	}

	public int getMinRequired() {
		return minRequired;
	}

	public void setMinRequired(int minRequired) {
		this.minRequired = minRequired;
	}

	public Integer getMaxAllowed() {
		return maxAllowed;
	}

	public void setMaxAllowed(Integer maxAllowed) {
		this.maxAllowed = maxAllowed;
	}

	public Set<CourseScheduleDetailForm> getCourseScheduleDetails() {
		return courseScheduleDetails;
	}

	public void setCourseScheduleDetails(Set<CourseScheduleDetailForm> courseScheduleDetails) {
		this.courseScheduleDetails = courseScheduleDetails;
	}

	public Set<InstructorForm> getInstructors() {
		return instructors;
	}

	public void setInstructors(Set<InstructorForm> instructors) {
		this.instructors = instructors;
	}

	public Set<VenueForm> getVenues() {
		return venues;
	}

	public void setVenues(Set<VenueForm> venues) {
		this.venues = venues;
	}

	public List<CourseScheduleDetailForm> getCourseScheduleDetailList() {
		return courseScheduleDetailList;
	}

	public void setCourseScheduleDetailList(List<CourseScheduleDetailForm> courseScheduleDetailList) {
		this.courseScheduleDetailList = courseScheduleDetailList;
	}

	@Override
	public String toString() {
		return "CourseScheduleUpdateForm [id=" + id + ", courseId=" + courseId + ", courseName=" + courseName
				+ ", instructorId=" + instructorId + ", venueId=" + venueId + ", minRequired=" + minRequired
				+ ", maxAllowed=" + maxAllowed + ", courseScheduleDetails=" + courseScheduleDetails + ", instructors="
				+ instructors + ", venues=" + venues + ", courseScheduleDetailList=" + courseScheduleDetailList + "]";
	}
    
}
