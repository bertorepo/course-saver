//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Attendee Form
//Class Name   : AttendeeForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2021/07/02 | WS) L.Celoso          | Initial Version
//==================================================================================================
package com.fujitsu.ph.tsup.report.summary.model;

import java.util.List;

public class AttendeeForm {
	
	 /**
     * Course ID
     */
    private Long id;

    /**
     * Employee Name
     */
    private String employeeName;
    
    /**
     * Course name
     */
    private List<String> courseName;
    
    /**
     * Attendance
     */
    private List<String> attendance;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    public List<String> getCourseName() {
        return courseName;
    }

    public void setCourseName(List<String> courseName) {
        this.courseName = courseName;
    }
    
    public List<String> getAttendanceStatus() {
        return attendance;
    }

    public void setAttendanceStatus(List<String> attendance) {
    	this.attendance = attendance;
    }
    
}
