package com.fujitsu.ph.tsup.domain.angara;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class CourseScheduleRowMapper implements RowMapper<CourseSchedule> {

    @Override
    public CourseSchedule mapRow(ResultSet rs, int RowNum) throws SQLException {
        Long courseId = rs.getLong("course_id");
        Long instructorId = rs.getLong("instructor_id");
        Long venueId = rs.getLong("venue_id");
        int maxRequired = rs.getInt("max_required");
        int minRequired = rs.getInt("min_required");
        String status = rs.getString("status");

        CourseSchedule courseSchedule = new CourseSchedule.Builder(courseId, instructorId, venueId, maxRequired,
                minRequired, status).build();
        return courseSchedule;
    }

}
