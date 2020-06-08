package com.fujitsu.ph.tsup.domain.iwarat;

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

    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    @Override
    public void save(Employee employee) {
        String sql = "INSERT INTO EMPLOYEE(number, last_name,  first_name, email_address, username)"
                + "VALUES(:number, :firstName, :lastName, :emailAddress, :username)";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("number", employee.getNumber())
                .addValue("lastName", employee.getLastName())
                .addValue("firstName", employee.getFirstName())
                .addValue("emailAddress", employee.getEmailAddress())
                .addValue("username", employee.getUsername());

        template.update(sql, namedParameters, generatedKeyHolder);
        GeneratedKeyHolderId();
    }

    @Override
    public Long GeneratedKeyHolderId() {
        return (Long) generatedKeyHolder.getKeys().get("id");
    }

    @Override
    public Set<Employee> findAll() {
        String sql = "SELECT id, number, first_name, last_name, email_address, username FROM EMPLOYEE";
        List<Employee> listEmployee = template.query(sql, new EmployeeRowMapper());
        Set<Employee> setEmployee = new HashSet<Employee>(listEmployee);

        return setEmployee;
    }

    @Override
    public Employee findById(Long id) {
        String sql = "SELECT id, number, first_name, last_name, email_address, username FROM EMPLOYEE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, namedParameters, new EmployeeRowMapper());
    }

}
