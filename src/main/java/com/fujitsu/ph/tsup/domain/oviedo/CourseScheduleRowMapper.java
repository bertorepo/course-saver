package com.fujitsu.ph.tsup.domain.oviedo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CourseScheduleRowMapper implements RowMapper<CourseSchedule> {
	@Override
	public CourseSchedule mapRow(ResultSet rs,int rowNum)throws SQLException {
		Long id = rs.getLong("id");
		Long cId = rs.getLong("CourseId");
		Long vId = rs.getLong("CourseId");
		Long iId = rs.getLong("CourseId");
		int min = rs.getInt("MinRequired");
		int max = rs.getInt("MaxAllowed");
		char status = rs.getString("Status").charAt(0);
		return new CourseSchedule.Builder(id, cId, iId, vId, min, max, status).build();
	}
}