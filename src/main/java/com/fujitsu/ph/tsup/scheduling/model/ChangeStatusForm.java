package com.fujitsu.ph.tsup.scheduling.model;

import java.util.List;
import java.util.Set;

public class ChangeStatusForm {
	
	/**
     * Course Id
     */
	private Long id;
	
	/**
     * List of courses
     */
	private Set<CourseForm> courses;
	
	/**
     * List of course schedules
     */
	private List<ChangeStatusScheduleForm> courseSchedules;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<CourseForm> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseForm> courses) {
		this.courses = courses;
	}

	
	public List<ChangeStatusScheduleForm> getCourseSchedules() {
		return courseSchedules;
	}

	public void setCourseSchedules(List<ChangeStatusScheduleForm> courseSchedules) {
		this.courseSchedules = courseSchedules;
	}

	@Override
	public String toString() {
		return "ChangeStatusForm [id=" + id + ", courses=" + courses + ", courseSchedules=" + courseSchedules + "]";
	}
	
}
