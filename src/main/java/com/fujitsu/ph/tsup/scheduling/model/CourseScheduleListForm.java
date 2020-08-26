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
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

public class CourseScheduleListForm {
	
	/**
     *	From Date and Time
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
	private ZonedDateTime fromDateTime;
	
	/**
     *	To Date and Time
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
	private ZonedDateTime toDateTime;
    
    /**
     *	Total count of trainigs today
     */
    private int totalTrainings;
    
    /**
     *	Monthly top learners
     */
    private List<TopLearnersForm> monthlyTopLearners;
    
    /**
     *	Quarterly top learners
     */
    private List<TopLearnersForm> quarterlyTopLearners;
    
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
	
	public int getTotalTrainings() {
		return totalTrainings;
	}
	public void setTotalTrainings(int totalTrainings) {
		this.totalTrainings = totalTrainings;
	}
	public List<TopLearnersForm> getMonthlyTopLearners() {
		return monthlyTopLearners;
	}
	public void setMonthlyTopLearners(List<TopLearnersForm> monthlyTopLearners) {
		this.monthlyTopLearners = monthlyTopLearners;
	}
	public List<TopLearnersForm> getQuarterlyTopLearners() {
		return quarterlyTopLearners;
	}
	public void setQuarterlyTopLearners(List<TopLearnersForm> quarterlyTopLearners) {
		this.quarterlyTopLearners = quarterlyTopLearners;
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
				+ ", totalTrainings=" + totalTrainings + ", monthlyTopLearners=" + monthlyTopLearners
				+ ", quarterlyTopLearners=" + quarterlyTopLearners + ", courseSchedules=" + courseSchedules + "]";
	}
}
