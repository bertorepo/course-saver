package com.fujitsu.ph.tsup.domain.ramos;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CourseScheduleRowMapper implements RowMapper<CourseSchedule> {
	
    @Override
    public CourseSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long courseId = rs.getLong("course_id");
        Long instructorId = rs.getLong("instructor_id");
        Long venueId = rs.getLong("venue_id");
        int minRequired = rs.getInt("min_required");
        int maxAllowed = rs.getInt("max_allowed");
        String status = rs.getString("status");
        
        
        CourseSchedule courseSchedule = new CourseSchedule
                .Builder(courseId, instructorId, venueId, minRequired, maxAllowed, status).build();
        
        return courseSchedule;
    }
}
