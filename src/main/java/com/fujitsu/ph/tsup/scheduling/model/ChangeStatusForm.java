package com.fujitsu.ph.tsup.scheduling.model;

import java.util.List;
import java.util.Set;

public class ChangeStatusForm {
	
	/**
     * Course Id
     */
	private long id;
	
	/**
     * List of courses
     */
	private Set<CourseForm> courses;
	
	/**
     * List of course schedules
     */
	private List<CourseScheduleViewForm> courseSchedules;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<CourseForm> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseForm> courses) {
		this.courses = courses;
	}

	public List<CourseScheduleViewForm> getCourseSchedules() {
		return courseSchedules;
	}

	public void setCourseSchedules(List<CourseScheduleViewForm> courseSchedules) {
		this.courseSchedules = courseSchedules;
	}

	@Override
	public String toString() {
		return "ChangeStatusForm [id=" + id + ", courses=" + courses + ", courseSchedules=" + courseSchedules + "]";
	}
	
}
