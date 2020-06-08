package com.fujitsu.ph.tsup.domain.freo;

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
public class EmployeeDaoImp {

	 @Autowired
	    private NamedParameterJdbcTemplate template;
	    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

	    @Override
	    public void save (Employee employee) {
	        String sql = "INSERT INTO employee(employee_number, first_name, last_name, email_address, username)"
	                + "VALUES(:number, :first_name, :last_name, :email_address, :username)";
	        SqlParameterSource namedParams = new MapSqlParameterSource()
	                .addValue("employee_number", employee.getEmployeeNumber())
	                .addValue("first_name", employee.getFirstName())
	                .addValue("last_name", employee.getLastName())
	                .addValue("email_address", employee.getEmailAddress())
	                .addValue("user_name", employee.getUserName());
	        template.update(sql, namedParams, generatedKeyHolder);
	        returnGeneratedKey();
	   
	    }

	    @Override
	    public Set<Employee> findAll() {
	        String query = "SELECT id, number, first_name, last_name, email_address, username FROM employee";
	        List<Employee> vnu = template.query(query, new EmployeeRowMapper());
	        Set<Employee> Employee = new HashSet<Employee>(vnu);
	        
	        return Employee;
	    }

	    @Override
	    public Employee findById(Long id) {
	        String query = "SELECT id, number, first_name, last_name, email_address, username FROM employee WHERE id = :id";
	        SqlParameterSource namedParams = new MapSqlParameterSource().addValue("id", id);
	        return template.queryForObject(query, namedParams, new EmployeeRowMapper());
	    }

	    @Override
	    public Long returnGeneratedKey() {
	        return (Long) generatedKeyHolder.getKeys().get("id");
	    }
	
}
