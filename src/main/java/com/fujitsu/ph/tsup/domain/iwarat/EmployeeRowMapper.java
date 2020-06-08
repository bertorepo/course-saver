package com.fujitsu.ph.tsup.domain.iwarat;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String number = rs.getString("number");
        String lastName = rs.getString("last_name");
        String firstName = rs.getString("first_name");
        String emailAddress = rs.getString("email_address");
        String username = rs.getString("username");

        Employee employees = new Employee.Builder(id, number, lastName, firstName, emailAddress, username).build();

        return employees;
    }
}
