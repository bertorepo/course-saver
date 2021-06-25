/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.department.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.department.domain.Department;

//=======================================================
//Project Name: Training Sign Up
//Class Name: DepartmentRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 23/06/2021 | WS) dw.cardenas  | Created
//=======================================================

public class DepartmentRowMapper implements RowMapper<Department> {

	@Override
	public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String deptName = rs.getString("department_name");
		String jduName = rs.getString("jdu_name");
		
		Department department = Department.builder().
				addId(id).
				addDepartmentName(deptName).
				addJduName(jduName)
				.build();
		
		return department;
	}

}
