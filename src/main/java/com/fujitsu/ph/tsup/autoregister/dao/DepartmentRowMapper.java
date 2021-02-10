package com.fujitsu.ph.tsup.autoregister.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.autoregister.model.AutoRegistrationDepartment;


public class DepartmentRowMapper implements RowMapper<AutoRegistrationDepartment> {

	@Override
	public AutoRegistrationDepartment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
        String departmentName = rs.getString("department_name");
		AutoRegistrationDepartment department = new AutoRegistrationDepartment.Builder(id,departmentName).build();
		return department;
	}
	
}

