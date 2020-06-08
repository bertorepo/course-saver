package com.fujitsu.ph.tsup.domain.angara;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int RowNum) throws SQLException {
        Long id = rs.getLong("emp_id");
        Long employeeNumber = rs.getLong("emp_num");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String emailAddress = rs.getString("email");
        String userName = rs.getString("user_Name");
        
        Employee employee = new Employee.Builder(id, employeeNumber, firstName, lastName, emailAddress, userName).build();
        return employee;
    }

}
