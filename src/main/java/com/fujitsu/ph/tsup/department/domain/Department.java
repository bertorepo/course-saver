/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.department.domain;

import org.apache.commons.lang3.StringUtils;

//=======================================================
//Project Name: Training Sign Up
//Class Name: Department.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 23/06/2021 | WS) dw.cardenas  | Created
//=======================================================

public class Department {
	private Long id;
	private String deptName;
	private Long jduId;
	private String jduName;
	
	protected Department() {}

	private Department(Builder builder) {
		validateDepartmentName(builder.departmentName);

		this.id = builder.id;
		this.deptName = builder.departmentName;
		this.jduId = builder.jduId;
		this.jduName = builder.jduName;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return deptName;
	}

	public void setDepartmentName(String deptName) {
		this.deptName = deptName;
	}

	private void validateDepartmentName(String departmentName) {
		if (StringUtils.isEmpty(departmentName)) {
			throw new IllegalArgumentException("Department name should not be empty.");
		}
	}

	public Long getJduId() {
		return jduId;
	}

	public void setJduId(Long jduId) {
		this.jduId = jduId;
	}
	
	public String getJduName() {
		return jduName;
	}

	public void setJduName(String jduName) {
		this.jduName = jduName;
	}
	
	@Override
	public String toString() {
		return String.format("Department [%d %s %d %s]", id, deptName, jduId, jduName);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Long id;
		private String departmentName;
		private Long jduId;
		private String jduName;
		
		private Builder() { }

		public Builder addId(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder addDepartmentName(String departmentName) {
			this.departmentName = departmentName;
			return this;
		}
		
		public Builder addJduId(Long jduId) {
			this.jduId = jduId;
			return this;
		}
		
		public Builder addJduName(String jduName) {
			this.jduName = jduName;
			return this;
		}

		public Department build() {
			return new Department(this);
		}
	}
}
