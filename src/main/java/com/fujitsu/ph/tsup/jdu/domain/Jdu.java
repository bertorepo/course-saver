/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.jdu.domain;

//=======================================================
//Project Name: Training Sign Up
//Class Name: Jdu.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 28/06/2021 | WS) dw.cardenas  | Created
//=======================================================

/**
 *
 * @author dw.cardenas
 *
 */
public class Jdu {
	private Long id;
	private String jduName;
	private String timezone;

	/**
	 * <pre>
	 * Default Constructor for JDU
	 * <pre>
	 */
	protected Jdu() {}

	/**
	 * <pre>
	 * Constructor for JDU from a builder
	 * <pre>
	 *
	 * @param builder
	 */
	private Jdu(Builder builder) {
		validateJduName(builder.jduName);
		validateTimezone(builder.timezone);
		this.id = builder.id;
		this.jduName = builder.jduName;
		this.timezone = builder.timezone;
	}

	/**
	 * <pre>
	 * Getter for JDU Id
	 * <pre>
	 *
	 * @return JDU Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <pre>
	 * Setter for JDU Id
	 * <pre>
	 *
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <pre>
	 * Getter for JDU Name
	 * <pre>
	 *
	 * @return JDU Name
	 */
	public String getJduName() {
		return jduName;
	}

	/**
	 * <pre>
	 * Setter for JDU Name
	 * <pre>
	 *
	 * @param jduName
	 */
	public void setJduName(String jduName) {
		this.jduName = jduName;
	}

	/**
	 * <pre>
	 * Getter for timezone
	 * <pre>
	 *
	 * @return Timezone
	 */
	public String getTimezone() {
		return timezone;
	}

	/**
	 * <pre>
	 * Setter for timezone
	 * <pre>
	 *
	 * @param timezone
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	/**
	 * <pre>
	 * Validator for JDU Name.
	 * Checks if provided name was empty or null
	 * <pre>
	 *
	 * @param jduName
	 */
	private void validateJduName(String jduName) {
		if (jduName == null || jduName.trim().isEmpty()) {
			throw new IllegalArgumentException("Jdu name should not be empty.");
		}
	}

	/**
	 * <pre>
	 * Validator for Timezone.
	 * Checks if provided name was empty or null
	 * <pre>
	 *
	 * @param timezone
	 */
	private void validateTimezone(String timezone) {
		if (timezone == null || timezone.trim().isEmpty()) {
			throw new IllegalArgumentException("Timezone should not be empty.");
		}
	}

	/**
	 * <pre>
	 * Creates a new instance of a builder for JDU
	 * <pre>
	 *
	 * @return Builder instance
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * <pre>
	 * Builder class for JDU
	 * <pre>
	 *
	 * @author dw.cardenas
	 *
	 */
	public static final class Builder {
		private Long id;
		private String jduName;
		private String timezone;

		/**
		 * <pre>
		 * Default constructor for builder
		 * <pre>
		 */
		private Builder() {}

		/**
		 * <pre>
		 * Adds JDU Id
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
		 * Adds JDU name
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
		 * Adds timezone
		 * <pre>
		 *
		 * @param timezone
		 * @return
		 */
		public Builder addTimezone(String timezone) {
			this.timezone = timezone;
			return this;
		}

		/**
		 * <pre>
		 * Creates a new instance of JDU based from current builder
		 * <pre>
		 *
		 * @return JDU
		 */
		public Jdu build() {
			return new Jdu(this);
		}
	}
}
