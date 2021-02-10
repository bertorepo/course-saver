package com.fujitsu.ph.tsup.autoregister.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.autoregister.model.AutoRegistration;
import com.fujitsu.ph.tsup.autoregister.model.AutoRegistrationDepartment;

@Repository
public class AutoRegistrationDaoImpl implements AutoRegistrationDao{

	// Call NamedParameterJdbcTemplate
    @Autowired
    private NamedParameterJdbcTemplate template;
	
	@Override
	public void addAutoRegistration(AutoRegistration autoRegistration) {
		
		String query = "INSERT INTO employee"
    			+ " (number, last_name, first_name, email_address, username, department_id, employment_date)"
    			+ " VALUES(:id, :lastName, :firstName, :emailAddress, :userName, :departmentid, :employmentDate)";
    	
    	SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
    			.addValue("id", autoRegistration.getId())
    			.addValue("lastName", autoRegistration.getLastName())
    			.addValue("firstName", autoRegistration.getFirstName())
    			.addValue("emailAddress", autoRegistration.getEmailAddress())
    			.addValue("userName", autoRegistration.getUserName())
    			.addValue("departmentid", autoRegistration.getDepartmentid())
    			.addValue("employmentDate", parseEmploymentDate(autoRegistration.getEmploymentDate()));
    	
    	template.update(query, sqlParameterSource);
    	
    	//add employee auth
    	addEmployeeAuth(autoRegistration.getUserName());
	}

	private void addEmployeeAuth(String username) {
		
    	String query = "INSERT INTO employee_auth"
    			+ " (auth_name, username)"
    			+ " VALUES(:authName, :userName)";
    	SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
    			.addValue("authName", "Member")
    			.addValue("userName", username);
    	
    	template.update(query, sqlParameterSource);
    	
	}
	
	private Date parseEmploymentDate(String value) {
		try {
			Date date = new SimpleDateFormat("yyyy-mm-dd").parse(value);
			return date;
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to parse date.");
		}
	}

	@Override
	public List<AutoRegistrationDepartment> getAllDepartment() {
		String query = "SELECT * FROM tsup.department";
		List<AutoRegistrationDepartment> departmentList = template.query(query, new DepartmentRowMapper());
		return departmentList;
	}
	
}
