package com.fujitsu.ph.tsup.report.summary.model;

public class Attendee {
	
	 /**
     * Course ID
     */
    private Long courseId;

    /**
     * Course name
     */
    private String courseName;
    
	 /**
     * Employee ID
     */
    private Long employeeId;
    
    /**
     * Employee Name
     */
    private String employeeName;
    
    public Long getId() {
        return courseId;
    }

    public void setId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    @Override
    public String toString() {
        return "NonAttendee [courseId=" + courseId 
        					+ ", courseName=" + courseName 
        					+ ", employeeId=" + employeeId 
        					+ ", employeeName=" + employeeName + "]";
    }
}
