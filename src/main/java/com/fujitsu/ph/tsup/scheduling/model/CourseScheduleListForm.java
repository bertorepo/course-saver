package com.fujitsu.ph.tsup.scheduling.model;

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: CourseScheduleListForm.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/22/2020 | WS) J.Macabugao | New Creation
//
//=======================================================

/**
* <pre>
* It is a JavaBean for CourseScheduleListForm
* <pre>
* @version 0.01
* @author j.macabugao
*
*/
import java.time.ZonedDateTime;
import java.util.Set;

public class CourseScheduleListForm {
	
	/**
     *	From Date and Time
     */
	private ZonedDateTime fromDateTime;
	
	/**
     *	To Date and Time
     */
	private ZonedDateTime toDateTime;
	
	/**
     *	Set of Course Schedule Form
     */
	private Set<CourseScheduleViewForm> courseSchedules;

	public ZonedDateTime getFromDateTime() {
		return fromDateTime;
	}
	public void setFromDateTime(ZonedDateTime fromDateTime) {
		this.fromDateTime = fromDateTime;
	}
	public ZonedDateTime getToDateTime() {
		return toDateTime;
	}

	public void setToDateTime(ZonedDateTime toDateTime) {
		this.toDateTime = toDateTime;
	}
	public Set<CourseScheduleViewForm> getCourseSchedules() {
		return courseSchedules;
	}
	public void setCourseSchedules(Set<CourseScheduleViewForm> courseSchedules) {
		this.courseSchedules = courseSchedules;
	}
	
	@Override
	public String toString() {
		return "CourseScheduleListForm [fromDateTime=" + fromDateTime + ", toDateTime=" + toDateTime
				+ ", courseSchedules=" + courseSchedules + "]";
	}
}
