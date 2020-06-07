package com.fujitsu.ph.tsup.domain.jimenez;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;


public class CourseScheduleRowMapper implements RowMapper<CourseSchedule>{

    @Override
    public CourseSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        Long course_id = rs.getLong("course_id");
        Long instructor_id = rs.getLong("instructor_id");
        Long venue_id = rs.getLong("venue_id");
        int min_required = rs.getInt("min_required");
        int max_allowed = rs.getInt("max_allowed");
        char status = rs.getString("status").charAt(0);
        
        CourseSchedule CourseSchedule = new CourseSchedule
                .Builder(id, course_id, instructor_id, venue_id, min_required, max_allowed, status).builder();
        
        return CourseSchedule;
    }
    
}


