package com.fujitsu.ph.tsup.domain.jimenez;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee>{

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String emp_num = rs.getString("number");
        String first_name = rs.getString("first_name");
        String last_name = rs.getString("last_name");
        String email_address = rs.getString("email_address");
        String username = rs.getString("username");
        
        Employee Employee = new Employee
                .Builder(id, emp_num, first_name, last_name, email_address, username).builder();
        
        return Employee;
    }

}
