//==================================================================================================
// Project Name :  Training Sign Up Project
// System Name  : Training Sign Up Project
// Class Name   : EmployeeDetailsRowMapper.java
//
// <<Modification History>>
// Version | Date       | Updated By            | Content
// --------+------------+-----------------------+---------------------------------------------------
// 1.0.0   | 2020/07/29 | WS) J.Macabudbud      | Initial Version
//==================================================================================================
package com.fujitsu.ph.tsup.authz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.employee.management.model.Employee;

/**
 * <pre>
 * Mapper Class for User Details
 * </pre>
 * 
 * @author WS) J.Macabudbud
 * @version 0.01
 * 
 */
public class EmployeeDetailsRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String number = rs.getString("number");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		String emailAddress = rs.getString("email_address");
		String username = rs.getString("username");
		Employee employee = new Employee.Builder(id, number, firstName, lastName, emailAddress, username).build();
		return employee;
	}

}
