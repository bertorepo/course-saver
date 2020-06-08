package com.fujitsu.ph.tsup.domain.yu;

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

public class EmployeeDaoImpl implements EmployeeDao {
    
    @Autowired
    private NamedParameterJdbcTemplate template;

    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    @Override
    public void save(Employee id) {
        String query = "INSERT INTO EMPLOYEE(number, last_name,  first_name, email_address, username)"
                + "VALUES(:number, :firstName, :lastName, :emailAddress, :userName)";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("number", id.getNumber())
                .addValue("lastName", id.getLastName())
                .addValue("firstName", id.getFirstName())
                .addValue("emailAddress", id.getEmailAddress())
                .addValue("userName", id.getUserName());
        template.update(query, namedParameters, generatedKeyHolder);
        generatedKey();  
    }
    
    public Long generatedKey() {
        return (Long) generatedKeyHolder.getKeys().get("id");
    }

    @Override
    public Set<Employee> findAll() {
        String query = "SELECT id, number, first_name, last_name, email_address, username FROM EMPLOYEE";
        List<Employee> employeeList = template.query(query, new EmployeeRowMapper());
        Set<Employee> employeeSet = new HashSet<Employee>(employeeList);
        return employeeSet;
    }

    @Override
    public Employee findById(Long id) {
        String query = "SELECT id, number, first_name, last_name, email_address, username FROM EMPLOYEE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(query, namedParameters, new EmployeeRowMapper());
    }

}
