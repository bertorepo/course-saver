package com.fujitsu.ph.tsup.enrollment.model;

public class SearchForm {
	private Long courseScheduleId;
	
	private String search;
	
	private String employeeNumber;

	public Long getCourseScheduleId() {
		return courseScheduleId;
	}

	public void setCourseScheduleId(Long courseScheduleId) {
		this.courseScheduleId = courseScheduleId;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	
	

}
