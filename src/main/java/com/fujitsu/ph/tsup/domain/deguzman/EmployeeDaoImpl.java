package com.fujitsu.ph.tsup.domain.deguzman;

import java.sql.Timestamp;
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
    public Set<Employee> findAll() {
        String sql = "SELECT id, number, last_name, first_name, email_address, user_name FROM employee";
        Set<Employee> employee = (Set<Employee>) template.query(sql, new EmployeeRowMapper());
        return employee;
    }

    @Override
    public Employee findById(Long id) {
        String sql = "SELECT id, number, last_name, first_name, email_address, user_name FROM employee WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, namedParameters, new EmployeeRowMapper());
    }

    @Override
    public Long saveLong(Employee employee) {
        String sql = "INSERT INTO EMPLOYEE(number, last_name, first_name, email_address, user_name)"
                + "VALUES(:number, :lastName, :firstName, :emailAddress, :userName)";
        
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("number", employee.getNumber())
                .addValue("lastName",employee.getLastName())
                .addValue("firstName", employee.getFirstName())
                .addValue("emailAddress", employee.getEmailAddress())
                .addValue("userName", employee.getUserName());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        template.update(sql, namedParameters, generatedKeyHolder);
        return (Long) generatedKeyHolder.getKeys().get("id");
    }

    @Override
    public void save(Employee employee) {
        // TODO Auto-generated method stub
        return;
    }


}
