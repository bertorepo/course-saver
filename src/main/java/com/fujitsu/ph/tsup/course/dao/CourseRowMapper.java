/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.course.model.Course;

/**
 * CourseManagementDao class
 * 
 * @author c.lepiten (New Creation by: c.Lepiten)
 * @version Revision: 0.01 Date: 2020-08-28
 */
public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        String name = rs.getString("name");
        String detail = rs.getString("detail");
        String isMandatory = rs.getString("mandatory");
        String deadline = rs.getString("deadline");

        Course course = new Course.Builder(id, name).detail(detail).mandatory(isMandatory, deadline).build();

        return course;
    }

}
