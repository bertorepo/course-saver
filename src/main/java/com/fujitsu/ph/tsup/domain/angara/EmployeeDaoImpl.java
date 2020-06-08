package com.fujitsu.ph.tsup.domain.angara;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private JdbcTemplate template;
    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    @Override
    public Long saveLong(Employee employee) {
        String sql = "INSERT INTO EMPLOYEE(emp_id, emp_num, first_name, last_name, email, user_name)"
                + "VALUES(:id, :employeeNumber, :firstName, :lastName, :emailAddress, :userName)";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", employee.getId())
        .addValue("employeeNumber", employee.getEmployeeNumber())
        .addValue("firstName", employee.getFirstName())
        .addValue("lastName", employee.getLastName())
        .addValue("emailAddress", employee.getEmailAddress())
        .addValue("userName", employee.getUserName());
        
        template.update(sql, namedParameters, generatedKeyHolder);
        return null;
    }
    
    @Override
    public Set<Employee> findAll() {
       String sql = "SELECT emp_id, emp_num, first_name, last_name, email, user_name)";
       List<Employee> employee = template.query(sql, new EmployeeRowMapper());
       Set<Employee> e = new HashSet<Employee>(employee);
        return e;
    }

    @Override
    public Employee findById(Long id) {
       String sql = "SELECT emp_id, emp_num, first_name, last_name, email, user_name)";
       SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("emp_id", id);
        return (Employee) template.queryForMap(sql, namedParameters, new EmployeeRowMapper());
    }

    @Override
    public void save(Employee id) {
        // TODO Auto-generated method stub
        
    }


}
