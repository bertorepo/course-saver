package com.fujitsu.ph.tsup.scheduling.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: CourseScheduleNewForm.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/22/2020 | WS) JC. Jimenez | New Creation
//0.02    | 06/29/2021 | WS) J. Atendido | Added Venue overlap and edited MaxAllowed
//=======================================================

/**
 * <pre>
 * It is a JavaBean for CourseScheduleNewForm.
 * <pre>
 * @version 0.01
 * @author jc.jimenez
 *
 */

import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class CourseScheduleNewForm{

    /**
     * Course Schedule Id
     */
    private Long id;
    
    /**
     * Course Id
     */
    @NotNull(message = "This field is required.")
    private Long courseId;
    
    /**
     * Instructor Id
     */
    @NotNull(message = "This field is required.")
    private Long instructorId;
    
    /**
     * Venue Id
     */
    @NotNull(message = "This field is required.")
    private Long venueId;
    
    /**
     * Venue Overlap
     */
    private boolean venueOverlap;
        
    /**
     * Minimum Required Participants
     */
    @Min(value = 1, message = "This field is required.")
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
     * Set of Courses
     */
    private Set<CourseForm> courses;
    
    /**
     * Set of Instructors
     */
    private Set<InstructorForm> instructors;
    
    /**
     * Set of Venues
     */
    private Set<VenueForm> venues;
    
    /**
     * List used to Bind Course Schedule Detail Inputs
     */
    private List<CourseScheduleDetailForm> courseScheduleDetailsAsList = 
            new ArrayList<>(Arrays.asList(new CourseScheduleDetailForm[1]));
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    
    public Long getCourseId() {
        return courseId;
    }
    
    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }
    
    public Long getInstructorId() {
        return instructorId;
    }
    
    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }
    
    public Long getVenueId() {
        return venueId;
    }
    
    public boolean isVenueOverlap() {
		return venueOverlap;
	}

	public void setVenueOverlap(boolean venueOverlap) {
		this.venueOverlap = venueOverlap;
	}

	public void setMinRequired(int minRequired) {
        this.minRequired = minRequired;
    }
    
    public int getMinRequired() {
        return minRequired;
    }
    
    public void setMaxAllowed(Integer maxAllowed) {
        this.maxAllowed = maxAllowed;
    }
    
    public Integer getMaxAllowed() {
        return maxAllowed;
    }
    
    public void setCourseScheduleDetails(Set<CourseScheduleDetailForm> courseScheduleDetails) {
        this.courseScheduleDetails = courseScheduleDetails;
    }
    
    public Set<CourseScheduleDetailForm> getCourseScheduleDetails() {
        return courseScheduleDetails;
    }
    
    public void setCourses(Set<CourseForm> courses) {
        this.courses = courses;
    }
    
    public Set<CourseForm> getCourses() {
        return courses;
    }
    
    public void setInstructors(Set<InstructorForm> instructors) {
        this.instructors = instructors;
    }
    
    public Set<InstructorForm> getInstructors() {
        return instructors;
    }
    
    public void setVenues(Set<VenueForm> venues) {
        this.venues = venues;
    }
    
    public Set<VenueForm> getVenues() {
        return venues;
    }
    
    /**
     * List to be bound in HTML
     * 
     * @return courseScheduleDetailsAsList
     */
    
    public void setCourseScheduleDetailsAsList(List<CourseScheduleDetailForm> courseScheduleDetailsAsList) {
        this.courseScheduleDetailsAsList = courseScheduleDetailsAsList;
    }
    
    public List<CourseScheduleDetailForm> getCourseScheduleDetailsAsList() {
        return courseScheduleDetailsAsList;
    }
    
    @Override
    public String toString() {
        return "CourseScheduleNewForm [id = " + id + ", courseId = " + courseId + ", instructorId = " 
    + instructorId + ", venueId = " + venueId + ", minRequired = " + minRequired + ", maxAllowed = " 
    + maxAllowed + ", courseScheduleDetails = "+ courseScheduleDetails + ", courseScheduleDetailsAsList = " 
    + courseScheduleDetailsAsList + ", courses = " + courses + ", instructors = " + instructors + 
    ", venues = " + venues +"]";
    }
}