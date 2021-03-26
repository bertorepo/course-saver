//==================================================================================================																																											
// Project Name : Training Sign Up
// System Name  : CoursesConductedListForm																																								
// Class Name   : CoursesConductedListForm.java 																																											
//																																											
// <<Modification History>> 																																											
// Version | Date       | Updated By            | Content																																											
//---------+------------+-----------------------+--------------------------------------------------- 																																											
// 1.0.0   | 2021/02/17 | WS)M.Rivera           | New Creation																																											
//==================================================================================================
package com.fujitsu.ph.tsup.course.model;

import java.time.ZonedDateTime;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * <pre>
 * The Form for courses conducted list
 * </pre>
 * 
 * @author m.rivera
 * @version 1.0.0
 */

public class CoursesConductedListForm {
	
	
	/**
     * Course Schedule Id
     */
    private Long id;
    
    /**
     * Course Name
     */
    private String courseName;
    
    /**
     *	Scheduled Start Date and Time
     */
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime scheduledStartDateTime;
	
	/**
     *	Scheduled End Date and Time
     */
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime scheduledEndDateTime;
	
    /**
     *	Rescheduled Start Date and Time
     */
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime rescheduledStartDateTime;
	
	/**
     *	Rescheduled End Date and Time
     */
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime rescheduledEndDateTime;
	
	private Set<CoursesConductedForm> coursesConductedSet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public ZonedDateTime getScheduledStartDateTime() {
		return scheduledStartDateTime;
	}

	public void setScheduledStartDateTime(ZonedDateTime scheduledStartDateTime) {
		this.scheduledStartDateTime = scheduledStartDateTime;
	}

	public ZonedDateTime getScheduledEndDateTime() {
		return scheduledEndDateTime;
	}

	public void setScheduledEndDateTime(ZonedDateTime scheduledEndDateTime) {
		this.scheduledEndDateTime = scheduledEndDateTime;
	}

	public ZonedDateTime getRescheduledStartDateTime() {
		return rescheduledStartDateTime;
	}

	public void setRescheduledStartDateTime(ZonedDateTime rescheduledStartDateTime) {
		this.rescheduledStartDateTime = rescheduledStartDateTime;
	}

	public ZonedDateTime getRescheduledEndDateTime() {
		return rescheduledEndDateTime;
	}

	public void setRescheduledEndDateTime(ZonedDateTime rescheduledEndDateTime) {
		this.rescheduledEndDateTime = rescheduledEndDateTime;
	}


	public Set<CoursesConductedForm> getCoursesConductedSet() {
		return coursesConductedSet;
	}

	public void setCoursesConductedSet(Set<CoursesConductedForm> coursesConductedSet) {
		this.coursesConductedSet = coursesConductedSet;
	}

	@Override
	public String toString() {
		return "CoursesConductedListForm [id=" + id + ", courseName=" + courseName 
				+ ", scheduledStartDateTime=" + scheduledStartDateTime + ", scheduledEndDateTime=" 
				+ scheduledEndDateTime + ", rescheduledStartDateTime=" + rescheduledStartDateTime 
				+ ", rescheduledEndDateTime=" + rescheduledEndDateTime + ", coursesConductedSet=" 
				+ coursesConductedSet + "]";
	}
	
	
	
}
