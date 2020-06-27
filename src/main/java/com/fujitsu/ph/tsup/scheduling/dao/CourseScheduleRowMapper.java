package com.fujitsu.ph.tsup.scheduling.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;

public class CourseScheduleRowMapper implements RowMapper<CourseSchedule> {
    
    @Override
    public CourseSchedule mapRow(ResultSet cs, int rowNum) throws SQLException {
        
        CourseSchedule courseSchedule = new CourseSchedule();
        
        Long id = cs.getLong("id");
        Long courseId = cs.getLong("courseId");
        Long instructorId = cs.getLong("instructorId");
        Long venueId = cs.getLong("venueId");
        int minRequired = cs.getInt("minRequired");
        int maxAllowed = cs.getInt("maxAllowed");
        String status = cs.getString("status");
        
        courseSchedule.setId(id);
        courseSchedule.setCourseId(courseId);
        courseSchedule.setInstructorId(instructorId);
        courseSchedule.setVenueId(venueId);
        courseSchedule.setMinRequired(minRequired);
        courseSchedule.setMaxAllowed(maxAllowed);
        courseSchedule.setStatus(status);
     
        return courseSchedule;
    }

}
