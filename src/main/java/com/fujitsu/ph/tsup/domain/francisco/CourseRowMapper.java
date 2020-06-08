package com.fujitsu.ph.tsup.domain.francisco;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class CourseRowMapper implements RowMapper<Course>{
    @Override
    public Course mapRow(ResultSet rs, int rowNumber) throws SQLException {
        Long id = rs.getLong("id");
        String courseName = rs.getString("name");

        Course courses = new Course.Builder(id, courseName).build();
        return courses;
    }
}
