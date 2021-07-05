/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.department.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.department.domain.Department;

//=======================================================
//Project Name: Training Sign Up
//Class Name: DepartmentDao.java
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
public interface DepartmentDao {

	/**
	 * <pre>
	 * Finds all departments in the database
	 * <pre>
	 *
	 * @return Set of Departments
	 */
	Set<Department> findAllDepartments();

	/**
	 * <pre>
	 * Finds specific department/s in the database that contains a specific string in the name
	 * <pre>
	 *
	 * @param departmentName
	 * @return Set of Departments
	 */
	Set<Department> findDepartmentByName(String departmentName);

	/**
	 * <pre>
	 * Inserts a new department in the database
	 * <pre>
	 *
	 * @param department
	 */
	void createDepartment(Department department);

	/**
	 * <pre>
	 * Updates an existing department in the database
	 * <pre>
	 *
	 * @param updatedDept
	 */
	void updateDepartment(Department updatedDept);

	/**
	 * <pre>
	 * Deletes an existing department with specific id in the database
	 * <pre>
	 *
	 * @param id
	 */
	void deleteDepartmentById(Long id);

}
