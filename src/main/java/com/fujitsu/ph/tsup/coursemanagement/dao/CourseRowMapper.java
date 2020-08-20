package com.fujitsu.ph.tsup.coursemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.coursemanagement.model.Course;

public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        String name = rs.getString("name");

        Course course = new Course.Builder(id, name).build();

        return course;
    }

}
