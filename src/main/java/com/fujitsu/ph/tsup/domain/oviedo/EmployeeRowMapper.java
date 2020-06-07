package com.fujitsu.ph.tsup.domain.oviedo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee> {
	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Long id = rs.getLong("id");
		String empNumber = rs.getString("EmployeeId");
		String fname = rs.getString("Firstname");
		String lname = rs.getString("Lastname");
		String email = rs.getString("Email");
		String username = rs.getString("Username");
		return new Employee.Builder(id, empNumber, lname, fname, email, username).build();
	}
}
