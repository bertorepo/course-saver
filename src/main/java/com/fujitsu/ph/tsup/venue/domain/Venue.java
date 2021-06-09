/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.venue.domain;

import org.springframework.util.StringUtils;

//=======================================================
//Project Name: Training Sign Up
//Class Name: Venue.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 03/06/2021 | WS) dw.cardenas  | Created
//=======================================================

/**
 *
 * @version 0.01
 * @author dw.cardenas
 *
 */
public class Venue {
	private Long id;
	private String venuName;

	/**
	 * <pre>
	 * Default empty constructor for Venue
	 * <pre>
	 *
	 */
	protected Venue() { }

	/**
	 * <pre>
	 * Constructor for Venue using a Builder class.
	 * <pre>
	 *
	 * @param builder
	 */
	public Venue(Builder builder) {
		this.id = builder.id;
		this.venuName = builder.venueName;
	}

	/**
	 * <pre>
	 * Getter for id
	 * <pre>
	 *
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <pre>
	 * Setter for id
	 * <pre>
	 *
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <pre>
	 * Getter for name
	 * <pre>
	 *
	 * @return name
	 */
	public String getName() {
		return venuName;
	}

	/**
	 * <pre>
	 * Setter for name
	 * <pre>
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.venuName = name;
	}

	/**
	 * Builder Class
	 */
	public static class Builder {
		private Long id;
		private String venueName;

		/**
		 * <pre>
		 * Constructor with parameter using name
		 * <pre>
		 *
		 * @param name
		 */
		public Builder(String name) {
			validateVenueName(name);
			this.venueName = name;
		}

		/**
		 * <pre>
		 * Constructor with parameter using id and name
		 * <pre>
		 *
		 * @param id
		 * @param name
		 */
		public Builder(Long id, String name) {
			validateId(id);
			validateVenueName(name);
			this.id = id;
			this.venueName = name;
		}

		/**
		 * <pre>
		 * Creates and returns instance of venue from builder.
		 * <pre>
		 *
		 * @return new venue instance
		 */
		public Venue build() {
			return new Venue(this);
		}

		/**
		 * <pre>
		 * Validation for venue id
		 * <pre>
		 *
		 * @param id
		 */
		private void validateId(Long id) {
			if (id == null || id == 0) {
				throw new IllegalArgumentException("Id should not be empty.");
			}
		}

		/**
		 * <pre>
		 * Validation for venue name
		 * <pre>
		 *
		 * @param venueName
		 */
		private void validateVenueName(String venueName) {
			if (StringUtils.isEmpty(venueName)) {
				throw new IllegalArgumentException("Venue name should not be empty.");
			}
		}
	}
}
