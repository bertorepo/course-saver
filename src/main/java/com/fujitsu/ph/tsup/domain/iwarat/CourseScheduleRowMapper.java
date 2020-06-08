package com.fujitsu.ph.tsup.domain.iwarat;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CourseScheduleRowMapper implements RowMapper<CourseSchedule> {

    @Override
    public CourseSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        Long courseId = rs.getLong("course_id");
        Long instructorId = rs.getLong("instructor_id");
        Long venueId = rs.getLong("venue_id");
        int minRecquired = rs.getInt("min_required");
        int maxRecquired = rs.getInt("max_allowed");
        String status = rs.getString("status");

        CourseSchedule courseSchedule = new CourseSchedule.Builder(id, courseId, instructorId, venueId, minRecquired,
                maxRecquired, status).build();

        return courseSchedule;
    }
}
