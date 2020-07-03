/**
 * 
 */
package com.fujitsu.ph.tsup.authz.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.fujitsu.ph.tsup.authz.core.model.EmployeeAuth;

/**
 * @author j.macabudbud
 *
 */
public class AuthorizationDaoImpl implements AuthorizationDao {
//	@Autowired
//	private EmployeeAuthRepository employeeAuthRepository;
	@Autowired
	private NamedParameterJdbcTemplate template;

	@Override
	public EmployeeAuth findByUsername(String username) {
		String query = "SELECT ID, USERNAME, AUTH_NAME FROM EMPLOYEE_AUTH WHERE USERNAME = :username";
		SqlParameterSource authzParameters = new MapSqlParameterSource().addValue("username", username);
		return template.queryForObject(query, authzParameters, new AuthzRowMapper());
	}

}
