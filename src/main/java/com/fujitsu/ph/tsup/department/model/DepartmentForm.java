/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.department.model;

//=======================================================
//Project Name: Training Sign Up
//Class Name: DepartmentForm.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 23/06/2021 | WS) dw.cardenas  | Created
//=======================================================

public class DepartmentForm {
	private Long id;
	private String departmentName;
	private Long jduId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Long getJduId() {
		return jduId;
	}

	public void setJduId(Long jduId) {
		this.jduId = jduId;
	}

	@Override
	public String toString() {
		return String.format("DepartmentForm [Id = %d, name = %s, jduId = %d]", id, departmentName, jduId);
	}
}
