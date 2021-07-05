/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.department.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.department.dao.DepartmentDao;
import com.fujitsu.ph.tsup.department.domain.Department;

//=======================================================
//Project Name: Training Sign Up
//Class Name: DepartmentServiceImpl.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 23/06/2021 | WS) dw.cardenas  | Created
//=======================================================

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentDao departmentDao;
	
	@Override
	public Set<Department> findAllDepartments() {
		return departmentDao.findAllDepartments();
	}

	@Override
	public Set<Department> findDepartmentByName(String departmentName) {
		return departmentDao.findDepartmentByName(departmentName);
	}

	@Override
	public void createDepartment(Department department) {
		try {
			departmentDao.createDepartment(department);
		} catch (Exception ex) {
			String err = String.format("Failed to save new department: [%s]", ex.getMessage());
			throw new IllegalArgumentException(err);
		}
	}

	@Override
	public void updateDepartment(Department updatedDept) {
		try {
			departmentDao.updateDepartment(updatedDept);
		} catch (Exception ex) {
			String err = String.format("Failed to update department: [%s]", ex.getMessage());
			throw new IllegalArgumentException(err);
		}
	}

	@Override
	public void deleteDepartment(Long id) {
		try {
			departmentDao.deleteDepartmentById(id);
		} catch (Exception ex) {
			String err = String.format("Failed to delete department: [%s]", ex.getMessage());
			throw new IllegalArgumentException(err);
		}
	}
}
