package com.fujitsu.ph.tsup.domain.rivera;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
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
        String sql = "INSERT INTO EMPLOYEE(employee_number, last_name, first_name, email_address, user_name)"
                + "VALUES(:employeeNumber, :lastName, :firstName, :emailAddress, :userName)";      
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("employeeNumber", employee.getEmployeeNumber())
                .addValue("lastName", employee.getLastName())
                .addValue("firstName", employee.getFirstName())
                .addValue("emailAddress", employee.getEmailAddress())
                .addValue("userName", employee.getUserName());
        template.update(sql, namedParameters, generatedKeyHolder);
        saveEmployee();
    }

    @Override
    public Set<Employee> findAll() {
        String sql = "SELECT id, employee_number, last_name, first_name, email_address, user_name FROM EMPLOYEE";
        List<Employee> employee = template.query(sql, new EmployeeRowMapper());
        Set<Employee> emp = new HashSet<Employee>(employee);
        return emp;
    }

    @Override
    public Employee findById(Long id) {
        String sql = "SELECT id, employee_number, last_name, first_name, email_address, user_name FROM EMPLOYEE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, namedParameters, new EmployeeRowMapper());
    }

    @Override
    public Long saveEmployee() {
        
        return (Long) generatedKeyHolder.getKeys().get("id");
    }

}
