package com.fujitsu.ph.tsup.domain.jimenez;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

public class CourseRowMapper implements RowMapper<Course>{

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        
        Course Course = new Course.Builder(id, name).builder();
        
        return Course;
    }
    
}

