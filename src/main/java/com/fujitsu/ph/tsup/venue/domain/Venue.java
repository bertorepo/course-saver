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
		validateVenueName(builder.venueName);
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
	 * <pre>
	 * Validation for venue id
	 * <pre>
	 *
	 * @param id
	 */
	@Deprecated
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
	
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder Class
	 */
	public static final class Builder {
		private Long id;
		private String venueName;

		public Builder addId(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder addVenueName(String venueName) {
			this.venueName = venueName;
			return this;
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
	}
}
