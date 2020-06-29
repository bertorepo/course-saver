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
	/* Course Schedule Detail Id */
	private Long id;
	
	/* Start Date and Time */
	private ZonedDateTime scheduledStartDateTime;
	
	/* End Date and Time */
	private ZonedDateTime scheduledEndDateTime;
	
	/* duration */
	private float duration;
	
	/**
	 * JavaBean for CourseScheduleDetailForm
	 * @param id
	 * @param scheduledStartDateTime
	 * @param scheduledEndDateTime
	 * @param duration
	 */
	public CourseScheduleDetailsForm(Long id, ZonedDateTime scheduledStartDateTime, ZonedDateTime scheduledEndDateTime,
			float duration) {
		this.id = id;
		this.scheduledStartDateTime = scheduledStartDateTime;
		this.scheduledEndDateTime = scheduledEndDateTime;
		this.duration = duration;
	}

	/**
	 * get id
	 * @return
	 */
	public Long getId() {
		return id;
	}
	/**
	 * set id
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * get ScheduledStartDateTime
	 * @return
	 */
	public ZonedDateTime getScheduledStartDateTime() {
		return scheduledStartDateTime;
	}
	
	/**
	 * set ScheduledStartDateTime
	 * @param scheduledStartDateTime
	 */
	public void setScheduledStartDateTime(ZonedDateTime scheduledStartDateTime) {
		this.scheduledStartDateTime = scheduledStartDateTime;
	}
	
	/**
	 * get ScheduledEndDateTime
	 * @return
	 */
	public ZonedDateTime getScheduledEndDateTime() {
		return scheduledEndDateTime;
	}
	
	/**
	 * set ScheduledEndDateTime
	 * @param scheduledEndDateTime
	 */
	public void setScheduledEndDateTime(ZonedDateTime scheduledEndDateTime) {
		this.scheduledEndDateTime = scheduledEndDateTime;
	}
	
	/**
	 * get Duration
	 * @return
	 */
	public float getDuration() {
		return duration;
	}
	
	/**
	 * set Duration
	 * @param duration
	 */
	public void setDuration(float duration) {
		this.duration = duration;
	}
}
