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

public interface DepartmentDao {

	Set<Department> findAllDepartments();

	Set<Department> findDepartmentByName(String departmentName);

	void createDepartment(Department department);

	void updateDepartment(Department updatedDept);

	void deleteDepartmentById(Long id);

}
