package com.fujitsu.ph.tsup.employee.management.model;
//========================================================================================================================
//$id:PR03$
//Project Name :Training Sign Up
//System Name  :Cancel Course Schedule
//Class Name   :CancelCourseSchedule.java
//
//<<Modification History>>
//Version | Date       | Updated By      | Content
//--------+------------+-----------------+--------------------------------------------------------------------------------
//0.01    | 06/19/2020 | WS) T.Oviedo    | New Creation
//========================================================================================================================
/**
* <pre>
* JavaBean for CancelCourseSchedule
* In this class, Instance 
* <pre>
* 
* @version 0.01
* @author t.oviedo
* 
*/
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CancelCourseScheduleForm {
	
	private Long id;
	private Long courseId;
	private String courseName;
	private Long instructorId;
	private String instructorName;
	private Long venueId;
	private String venueName;
	private CourseScheduleDetailsForm courseScheduleDetails;
	private int minRequired;
	private int maxAllowed;
	private int totalParticipants;
	
	public CancelCourseScheduleForm() {
		
	}
	
	public CancelCourseScheduleForm(Long id, Long courseId, String courseName, Long instructorId, String instructorName,
			Long venueId, String venueName, CourseScheduleDetailsForm courseScheduleDetails, int minRequired,
			int maxAllowed, int totalParticipants) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.courseName = courseName;
		this.instructorId = instructorId;
		this.instructorName = instructorName;
		this.venueId = venueId;
		this.venueName = venueName;
		this.courseScheduleDetails = courseScheduleDetails;
		this.minRequired = minRequired;
		this.maxAllowed = maxAllowed;
		this.totalParticipants = totalParticipants;
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

	public CourseScheduleDetailsForm getCourseScheduleDetails() {
		return courseScheduleDetails;
	}

	public void setCourseScheduleDetails(CourseScheduleDetailsForm courseScheduleDetails) {
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
		
				
		String toReturn = "CancelCourseSchedule [Course ID="+courseId+", Course Name="
				+ courseName + ", Instructor ID=" + instructorId + ", Instructor Name=" + instructorName + ", Venue ID="
				+ venueId + ", Venue Name=" + venueName + ", Course Schedule Details=" + courseScheduleDetails + ", Minimum Required" + minRequired + ", Maximum Allowed="
				+ maxAllowed + ", Total Participants=" + totalParticipants + "]";
		
		return toReturn;
	}
	
}
