package com.fujitsu.ph.tsup.domain.freo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		 	Long id = rs.getLong("id");
	        String employee_number = rs.getString("employee_number");
	        String first_name = rs.getString("first_name");
	        String last_name = rs.getString("last_name");
	        String email_address = rs.getString("email_address");
	        String user_name = rs.getString("user_name");
	        
	        Employee Employee = new Employee
	                .Builder(id, employee_number, first_name, last_name, email_address, user_name).builder();
	        
	        return Employee;
	}

}




