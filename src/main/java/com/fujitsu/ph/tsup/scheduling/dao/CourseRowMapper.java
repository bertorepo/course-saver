package com.fujitsu.ph.tsup.scheduling.dao;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.scheduling.model.CourseForm;

public class CourseRowMapper implements RowMapper<CourseForm>{

    @Override
    public CourseForm mapRow(ResultSet rs, int rowNum) throws SQLException {
        CourseForm courseForm = new CourseForm();
        
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        courseForm.setId(id);
        courseForm.setName(name);
        
        return courseForm;
    }

}
