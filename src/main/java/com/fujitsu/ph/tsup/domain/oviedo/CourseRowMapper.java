package com.fujitsu.ph.tsup.domain.oviedo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CourseRowMapper implements RowMapper<Course> {

	@Override
	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Long id = rs.getLong("id");
		String course = rs.getString("CourseName");
		return new Course.Builder(id, course).build();

	}

}
