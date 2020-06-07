package com.fujitsu.ph.tsup.domain.abad;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CourseRowMapper implements RowMapper<Course>{

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String courseName = rs.getString("course_name");
        
        Course course = new Course.Builder(id, courseName).build();
        
        return course;
    }
}
