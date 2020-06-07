package com.fujitsu.ph.tsup.domain.oviedo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub

	}

	@Override
	public Long saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO EMPLOYEE(id, EmployeeNumber, Lastname, Firstname, Email, Username)"
				+ "VALUES(:id, :empNumber,:lname,:fname,:email,:username)";

		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("id", employee.getId())
				.addValue("empNumber", employee.getEmpNumber())
				.addValue("lname", employee.getLname())
				.addValue("fname", employee.getFname())
				.addValue("email", employee.getEmail())
				.addValue("username", employee.getUsername());


		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		template.update(sql, namedParameters, generatedKeyHolder);
		return (Long) generatedKeyHolder.getKeys().get("id");
	}

	@Override
	public Set<Employee> findAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT id, EmployeeNumber, Lastname, Firstname, Email, Username FROM EMPLOYEE";
		List<Employee> employee = template.query(sql, new EmployeeRowMapper());
		Set<Employee> result = new HashSet<Employee>(employee);
		return result;
	}

	@Override
	public Employee findById(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT id, EmployeeNumber, Lastname, Firstname, Email, Username FROM EMPLOYEE WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, namedParameters, new EmployeeRowMapper());
	}
}
