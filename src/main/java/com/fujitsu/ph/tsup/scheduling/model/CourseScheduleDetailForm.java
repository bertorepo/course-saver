package com.fujitsu.ph.tsup.scheduling.model;
//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: CourseScheduleDetailForm.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/22/2020 | WS) J.Macabugao | New Creation
//
//=======================================================

/**
* <pre>
* It is a JavaBean for CourseScheduleDetailForm
* <pre>
* @version 0.01
* @author j.macabugao
*
*/
import java.time.ZonedDateTime;

public class CourseScheduleDetailForm {

	/**
     *	Id
     */
	private Long id;
	
	/**
     *	Start Date and Time
     */
	private ZonedDateTime scheduledStartDateTime;
	
	/**
     *	End Date and Time
     */
	private ZonedDateTime scheduledEndDateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return "CourseScheduleDetailForm [id=" + id + ", scheduledStartDateTime=" + scheduledStartDateTime
				+ ", scheduledEndDateTime=" + scheduledEndDateTime + "]";
	}

}
