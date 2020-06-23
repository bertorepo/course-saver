package com.fujitsu.ph.tsup.employee.management.model;
//========================================================================================================================
//$id:PR03$
//Project Name :Training Sign Up
//System Name  :Course Schedule Details
//Class Name   :CourseScheduleDetails.java
//
//<<Modification History>>
//Version | Date       | Updated By      | Content
//--------+------------+-----------------+--------------------------------------------------------------------------------
//0.01    | 06/19/2020 | WS) T.Oviedo    | New Creation
//========================================================================================================================
/**
 * <pre>
 * JavaBean for CourseScheduleDetails
 * In this class, Instance 
 * <pre>
 * 
 * @version 0.01
 * @author t.oviedo
 * 
 */
import java.time.ZonedDateTime;

public class CourseScheduleDetailsForm {
	private Long id;
	private Long courseScheduleId;
	private ZonedDateTime scheduledStartDateTime;
	private ZonedDateTime scheduledEndDateTime;
	private float duration;
	
	public CourseScheduleDetailsForm(Long id, Long courseScheduleId, ZonedDateTime scheduledStartDateTime,
			ZonedDateTime scheduledEndDateTime, float duration) {
		super();
		this.id = id;
		this.courseScheduleId = courseScheduleId;
		this.scheduledStartDateTime = scheduledStartDateTime;
		this.scheduledEndDateTime = scheduledEndDateTime;
		this.duration = duration;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCourseScheduleId() {
		return courseScheduleId;
	}
	public void setCourseScheduleId(Long courseScheduleId) {
		this.courseScheduleId = courseScheduleId;
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
	public float getDuration() {
		return duration;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	
}
