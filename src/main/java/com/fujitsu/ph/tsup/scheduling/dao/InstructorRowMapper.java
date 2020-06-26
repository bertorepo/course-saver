package com.fujitsu.ph.tsup.scheduling.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
public class InstructorRowMapper implements RowMapper<InstructorForm>{

	@Override
	public InstructorForm mapRow(ResultSet rs, int rowNum) throws SQLException {
		InstructorForm instructorForm = new InstructorForm();
		
		Long instructorId = rs.getLong("instructorId");
		String instructorName = rs.getNString("instructorName");
		instructorForm.setId(instructorId);
		instructorForm.setName(instructorName);
		
		
		return instructorForm;
	}

}
