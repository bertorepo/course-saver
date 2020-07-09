/**
 * 
 */
package com.fujitsu.ph.tsup.authz.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.fujitsu.ph.tsup.authz.core.model.EmployeeAuth;
import com.fujitsu.ph.tsup.employee.management.model.Employee;

/**
 * @author j.macabudbud
 *
 */
@Component
public class AuthorizationDaoImpl implements AuthorizationDao {
	@Autowired
	private NamedParameterJdbcTemplate template;

	@Override
	public Set<EmployeeAuth> findByUsername(String username) {
		String query = "SELECT ID, USERNAME, AUTH_NAME FROM EMPLOYEE_AUTH WHERE USERNAME = :username";
		SqlParameterSource authzParameters = new MapSqlParameterSource().addValue("username", username);
		List<EmployeeAuth> data = template.query(query, authzParameters, new AuthzRowMapper());
		return new HashSet<>(data);
	}

	@Override
	public Employee findDetailsByUsername(String username) {
		String query = "SELECT ID, NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME "
						+ "FROM EMPLOYEE WHERE USERNAME = :username";
		SqlParameterSource authzParameters = new MapSqlParameterSource().addValue("username", username);
		return template.queryForObject(query, authzParameters, new EmployeeDetailsRowMapper());
	}

}
