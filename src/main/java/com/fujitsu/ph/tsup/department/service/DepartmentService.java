/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.department.service;

import java.util.Set;

import com.fujitsu.ph.tsup.department.domain.Department;

//=======================================================
//Project Name: Training Sign Up
//Class Name: DepartmentService.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 23/06/2021 | WS) dw.cardenas  | Created
//=======================================================

/**
 * 
 * @author dw.cardenas
 *
 */
public interface DepartmentService {

	/**
	 * <pre>
	 * Finds all departments
	 * <pre>
	 *
	 * @return Set of departments
	 */
	Set<Department> findAllDepartments();

	/**
	 * <pre>
	 * Finds departments that contains specific string in name
	 * <pre>
	 *
	 * @param departmentName
	 * @return Set of department/s with specific string in name
	 */
	Set<Department> findDepartmentByName(String departmentName);

	/**
	 * <pre>
	 * Creates a new department
	 * <pre>
	 *
	 * @param department
	 */
	void createDepartment(Department department);

	/**
	 * <pre>
	 * Updates an existing department
	 * <pre>
	 *
	 * @param updatedDept
	 */
	void updateDepartment(Department updatedDept);

	/**
	 * <pre>
	 * Deletes an existing department
	 * <pre>
	 *
	 * @param id
	 */
	void deleteDepartment(Long id);
}
