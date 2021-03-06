package com.fujitsu.ph.tsup.scheduling.dao;
//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: CourseRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/26/2020 | WS) J.Macabugao  | New Creation
//
//
//=======================================================

import java.sql.ResultSet;

import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;

/**
* <pre>
* Custom RowMapper Class for findAllInstructors()
* <pre>
* @version 0.01
* @author j.macabugao
*
*/
public class InstructorRowMapper implements RowMapper<InstructorForm>{
	
	/**
     * <pre>
     * Maps the Rows returned by ResultSet
     * <pre>
     * @param ResultSet rs
     * @param int rowNum
     * @throws SQLException
     */
	@Override
	public InstructorForm mapRow(ResultSet rs, int rowNum) throws SQLException {
		InstructorForm instructorForm = new InstructorForm();

		Long instructorId = rs.getLong("ID");
		String instructorLastName = rs.getString("LAST_NAME");
		String instructorFirstName = rs.getString("FIRST_NAME");
		
		instructorForm.setId(instructorId);
		instructorForm.setName(instructorLastName + "," + instructorFirstName);
		
		return instructorForm;
	}

}
