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

/**
* <pre>
* Custom RowMapper Class for findAllInstructors()
* <pre>
* @version 0.01
* @author j.macabugao
*
*/
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
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

		Long instructorId = rs.getLong("instructorId");
		String instructorLastName = rs.getString("instructorLastName");
		String instructorFirstName = rs.getNString("instructorFirstName");
		
		instructorForm.setId(instructorId);
		instructorForm.setName(instructorLastName + "," + instructorFirstName);
		
		return instructorForm;
	}

}
