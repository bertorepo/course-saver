/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.department.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.department.domain.Department;

//=======================================================
//Project Name: Training Sign Up
//Class Name: DepartmentDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 23/06/2021 | WS) dw.cardenas  | Created
//=======================================================

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Override
	public Set<Department> findAllDepartments() {
		String query = "SELECT * FROM department d INNER JOIN jdu_type j ON d.jdu_id = j.id ORDER BY jdu_name";
		List<Department> departmentList = template.query(query, new DepartmentRowMapper());
		
		return new LinkedHashSet<>(departmentList);
	}

	@Override
	public Set<Department> findDepartmentByName(String departmentName) {
		String query = "SELECT * FROM DEPARTMENT WHERE LOWER(name) LIKE LOWER('%" + departmentName + "%') ORDER BY name";
		List<Department> departmentList = template.query(query, new DepartmentRowMapper());
		
		return new LinkedHashSet<>(departmentList);
	}

	@Override
	public void createDepartment(Department department) {
		String query = "INSERT INTO DEPARTMENT(name) VALUES(:name)";
		SqlParameterSource sqlParamSource = new MapSqlParameterSource()
				.addValue("name", department.getName());
		
		template.update(query, sqlParamSource);
	}

	@Override
	public void updateDepartment(Department updatedDept) {
		String query = "UPDATE DEPARTMENT SET name = :name WHERE id = :id";
		SqlParameterSource sqlParamSource = new MapSqlParameterSource()
				.addValue("id", updatedDept.getId())
				.addValue("name", updatedDept.getName());
		
		template.update(query, sqlParamSource);
	}

	@Override
	public void deleteDepartmentById(Long id) {
		String query = "DELETE FROM DEPARTMENT WHERE id = :id";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("id", id);

		template.update(query, sqlParameterSource);
	}

}
