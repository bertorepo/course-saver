/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.venue.model;

//=======================================================
//Project Name: Training Sign Up
//Class Name: VenueForm.java
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
public class VenueForm {
	private Long id;
	private String name;

	/**
	 * <pre>
	 * Getter for id
	 * <pre>
	 *
	 * @return venue id
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
	 * Getter for id
	 * <pre>
	 *
	 * @return venue name
	 */
	public String getName() {
		return name;
	}

	/**
	 * <pre>
	 * Setter for name
	 * <pre>
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "VenueForm [Id = " + id + ", name = " + name + "]";
	}
}
