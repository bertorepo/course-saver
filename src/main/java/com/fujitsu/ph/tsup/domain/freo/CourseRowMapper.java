package com.fujitsu.ph.tsup.domain.freo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class CourseRowMapper implements RowMapper<Course> {
	
	@Override
	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long Id = rs.getLong("Id");
		String courseName = rs.getString("CourseName");
		
		Course course = new Course.Builder(Id, courseName).builder();
		return course;
		
	}

}
