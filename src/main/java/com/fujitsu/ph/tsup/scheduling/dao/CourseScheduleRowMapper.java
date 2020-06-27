package com.fujitsu.ph.tsup.scheduling.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;

public class CourseScheduleRowMapper implements RowMapper<CourseSchedule> {
    
    @Override
    public CourseSchedule mapRow(ResultSet cs, int rowNum) throws SQLException {
        
        
        
        Long id = cs.getLong("id");
        Long courseId = cs.getLong("courseId");
        String courseName = cs.getString("courseName");
        Long instructorId = cs.getLong("instructorId");
        String instructorLastName = cs.getString("instructorLastName");
        String instructorFirstName = cs.getString("instructorFirstName");
        Long venueId = cs.getLong("venueId");
        String venueName = cs.getString("venueName");
        int minRequired = cs.getInt("minRequired");
        int maxAllowed = cs.getInt("maxAllowed");
        char status = cs.getString("status").charAt(0);
       
        CourseSchedule courseSchedule = new CourseSchedule.Builder(id, courseId, courseName, instructorId, 
                instructorLastName, instructorFirstName, venueId, venueName, 
                minRequired, maxAllowed, status).build();
     
        return courseSchedule;
    }

}
