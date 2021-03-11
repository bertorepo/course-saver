/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.autoregister.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fujitsu.ph.tsup.authz.dao.EmployeeDetailsRowMapper;
import com.fujitsu.ph.tsup.autoregister.model.AutoRegistration;
import com.fujitsu.ph.tsup.autoregister.model.AutoRegistrationDepartment;
import com.fujitsu.ph.tsup.common.domain.Employee;

/**
 * AutoRegistrationDaoImpl class
 * 
 * @author k.sala (New Creation by: k.sala)
 * @version 0.01
 */
@Repository
@CrossOrigin(origins = "*")
public class AutoRegistrationDaoImpl implements AutoRegistrationDao {

    // Call NamedParameterJdbcTemplate
    @Autowired
    private NamedParameterJdbcTemplate template;

    /*
     * Method for adding new Member to employee table
     */
    @Override
    public void addAutoRegistration(AutoRegistration autoRegistration) {

        String query = "INSERT INTO employee"
                + " (number, last_name, first_name, email_address, username, department_id, employment_date)"
                + " VALUES(:employeeNumber, :lastName, :firstName, :emailAddress, :userName, :departmentid, :employmentDate)";

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("employeeNumber", autoRegistration.getEmployeeNumber())
                .addValue("lastName", autoRegistration.getLastName())
                .addValue("firstName", autoRegistration.getFirstName())
                .addValue("emailAddress", autoRegistration.getEmailAddress())
                .addValue("userName", autoRegistration.getUserName())
                .addValue("departmentid", autoRegistration.getDepartmentid())
                .addValue("employmentDate", parseEmploymentDate(autoRegistration.getEmploymentDate()));

        template.update(query, sqlParameterSource);

        // add employee auth
        addEmployeeAuth(autoRegistration.getUserName());
    }

    /*
     * Method for adding new Member to employee_auth table
     */
    private void addEmployeeAuth(String username) {
    	try {
	        String query = "INSERT INTO employee_auth" + " (auth_name, username)"
	                + " VALUES(:authName, :userName)";
	        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("authName", "Member")
	                .addValue("userName", username);
	
	        template.update(query, sqlParameterSource);
    	}catch(Exception a) {
        	throw new IllegalArgumentException("Error Employee Authentication");
        }
    }

    /*
     * Method for formatting string to date
     */
    private Date parseEmploymentDate(String value) {
        try {
            Date date = new SimpleDateFormat("yy-M-dd").parse(value);
            return date;
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to parse date.");
        }
    }

    /*
     * Method for loading all Departments
     */
    @Override
    public List<AutoRegistrationDepartment> getAllDepartments() {
        String query = "SELECT * FROM tsup.department";
        List<AutoRegistrationDepartment> departmentList = template.query(query, new DepartmentRowMapper());
        return departmentList;
    }

    @Override
    public Employee findDetailsByEmployeeNumber(String employeeNumber) {
        String query = "SELECT ID, NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME "
                + "FROM EMPLOYEE WHERE NUMBER = :employeeNumber";
        SqlParameterSource employeeNumberParameters = new MapSqlParameterSource().addValue("employeeNumber", employeeNumber);
        return template.queryForObject(query, employeeNumberParameters, new EmployeeDetailsRowMapper());
    }

}
