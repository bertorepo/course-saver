//==================================================================================================
// Project Name :  Training Sign Up Project
// System Name  : Training Sign Up Project
// Class Name   : AuthzRowMapper.java
//
// <<Modification History>>
// Version | Date       | Updated By            | Content
// --------+------------+-----------------------+---------------------------------------------------
// 1.0.0   | 2020/07/29 | WS) J.Macabudbud      | Initial Version
//==================================================================================================
package com.fujitsu.ph.tsup.authz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.authz.core.model.EmployeeAuth;

/**
 * <pre>
 * Mapper Class for User Authorization
 * </pre>
 * 
 * @author WS) J.Macabudbud
 * @version 0.01
 * 
 */
public class AuthzRowMapper implements RowMapper<EmployeeAuth> {
	
	@Override
	public EmployeeAuth mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String username = rs.getString("username");
		String auth_name = rs.getString("auth_name");
		Set<String> roles = new HashSet<String>();
		roles.add(auth_name);
		EmployeeAuth employeeAuth = new EmployeeAuth.Builder(id, username, roles).build();
		return employeeAuth;
	}

}
