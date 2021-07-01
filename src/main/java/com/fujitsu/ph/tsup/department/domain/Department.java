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

/**
 *
 * @author dw.cardenas
 *
 */
public class Department {
	private Long id;
	private String deptName;
	private Long jduId;
	private String jduName;

	/**
	 * <pre>
	 * Default empty constructor
	 * <pre>
	 */
	protected Department() {}

	/**
	 * <pre>
	 * Constructor from a given builder
	 * <pre>
	 *
	 * @param builder
	 */
	private Department(Builder builder) {
		validateDepartmentName(builder.departmentName);

		this.id = builder.id;
		this.deptName = builder.departmentName;
		this.jduId = builder.jduId;
		this.jduName = builder.jduName;
	}

	/**
	 * <pre>
	 * Getter for Department Id
	 * <pre>
	 *
	 * @return Department Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <pre>
	 * Setter for Department Id
	 * <pre>
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <pre>
	 * Getter for Department Name
	 * <pre>
	 *
	 * @return Department Name
	 */
	public String getName() {
		return deptName;
	}

	/**
	 * <pre>
	 * Setter for Department Name
	 * <pre>
	 *
	 * @param deptName
	 */
	public void setDepartmentName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * <pre>
	 * Getter for Jdu Id
	 * <pre>
	 *
	 * @return Jdu Id
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

	/**
	 * <pre>
	 * Getter for Jdu Name
	 * <pre>
	 *
	 * @return Jdu Name
	 */
	public String getJduName() {
		return jduName;
	}

	/**
	 * <pre>
	 * Setter for Jdu Name
	 * <pre>
	 *
	 * @param jduName
	 */
	public void setJduName(String jduName) {
		this.jduName = jduName;
	}

	/**
	 * <pre>
	 * Validator for Department Name.
	 * Checks if provided department name is null or empty.
	 * <pre>
	 *
	 * @param departmentName
	 */
	private void validateDepartmentName(String departmentName) {
		if (StringUtils.isEmpty(departmentName)) {
			throw new IllegalArgumentException("Department name should not be empty.");
		}
	}

	@Override
	public String toString() {
		return String.format("Department [%d %s %d %s]", id, deptName, jduId, jduName);
	}

	/**
	 * <pre>
	 * Creates an instance of a builder for Department
	 * <pre>
	 *
	 * @return Builder instance
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * <pre>
	 * Builder class for Department
	 * <pre>
	 *
	 * @author dw.cardenas
	 *
	 */
	public static final class Builder {
		private Long id;
		private String departmentName;
		private Long jduId;
		private String jduName;
		
		/**
		 * <pre>
		 * Default Constructor for Builder
		 * <pre>
		 */
		private Builder() { }

		/**
		 * <pre>
		 * Adds Department Id
		 * <pre>
		 *
		 * @param id
		 * @return
		 */
		public Builder addId(Long id) {
			this.id = id;
			return this;
		}

		/**
		 * <pre>
		 * Adds Department Name
		 * <pre>
		 *
		 * @param departmentName
		 * @return
		 */
		public Builder addDepartmentName(String departmentName) {
			this.departmentName = departmentName;
			return this;
		}

		/**
		 * <pre>
		 * Adds Jdu Id
		 * <pre>
		 *
		 * @param jduId
		 * @return
		 */
		public Builder addJduId(Long jduId) {
			this.jduId = jduId;
			return this;
		}

		/**
		 * <pre>
		 * Adds Jdu Name
		 * <pre>
		 *
		 * @param jduName
		 * @return
		 */
		public Builder addJduName(String jduName) {
			this.jduName = jduName;
			return this;
		}

		/**
		 * <pre>
		 * Creates a new instance of department from this instance of the builder
		 * <pre>
		 *
		 * @return Department
		 */
		public Department build() {
			return new Department(this);
		}
	}
}
