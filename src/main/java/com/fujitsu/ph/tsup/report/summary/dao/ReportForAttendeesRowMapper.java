package com.fujitsu.ph.tsup.report.summary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.report.summary.model.Attendee;

public class ReportForAttendeesRowMapper implements RowMapper<Attendee>{
	
	/**
     * <pre>
     *  Maps the Rows returned by ResultSet
     * </pre>
     * 
     * @param ResultSet rs
     * @param int rowNum
     * @throws SQLException
     */
    public Attendee mapRow(ResultSet rs, int rowNum) throws SQLException {

    	Attendee nonAttendee = new Attendee();
        Long courseId = rs.getLong("COURSE_ID");
        String name = rs.getString("COURSE_NAME");
        String employeeName = rs.getString("EMPLOYEE_NAME");
        Long empId = rs.getLong("EMPLOYEE_ID");
        
        nonAttendee.setId(courseId);
        nonAttendee.setCourseName(name);
        nonAttendee.setEmployeeName(employeeName);
        nonAttendee.setEmployeeId(empId);
        
        return nonAttendee;

    }
}
