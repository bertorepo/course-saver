/**
 * 
 */
package com.fujitsu.ph.tsup.authz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.authz.core.model.EmployeeAuth;

/**
 * @author j.macabudbud
 *
 */
public class AuthzRowMapper implements RowMapper<EmployeeAuth>{

	@Override
	public EmployeeAuth mapRow(ResultSet rs, int rowNum) throws SQLException {
		String username = rs.getString("username");
		String auth_name = rs.getString("auth_name");
		Set<String> roles = new HashSet<String>();
		roles.add(auth_name);
		EmployeeAuth employeeAuth = new EmployeeAuth.Builder(username, roles).build();
		return employeeAuth;
	}

}
