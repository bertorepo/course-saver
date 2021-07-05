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

/**
 *
 * @author dw.cardenas
 *
 */
public class DepartmentForm {
	private Long id;
	private String departmentName;
	private Long jduId;

	/**
	 * <pre>
	 * Getter for Id
	 * <pre>
	 *
	 * @return Department Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <pre>
	 * Setter for Id
	 * <pre>
	 *
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <pre>
	 * Getter for department name
	 * <pre>
	 *
	 * @return Department Name
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * <pre>
	 * Setter for department name
	 * <pre>
	 *
	 * @param departmentName
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * <pre>
	 * Getter for Jdu Id
	 * <pre>
	 *
	 * @return
	 */
	public Long getJduId() {
		return jduId;
	}

	/**
	 * <pre>
	 * Setter for Jdu Id
	 * <pre>
	 *
	 * @param jduId
	 */
	public void setJduId(Long jduId) {
		this.jduId = jduId;
	}

	@Override
	public String toString() {
		return String.format("DepartmentForm [Id = %d, name = %s, jduId = %d]", id, departmentName, jduId);
	}
}
