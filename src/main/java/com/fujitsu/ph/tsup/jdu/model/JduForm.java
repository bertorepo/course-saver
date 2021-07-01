/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.jdu.model;

//=======================================================
//Project Name: Training Sign Up
//Class Name: JduForm.java
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
public class JduForm {
	private Long id;
	private String jduName;
	private String timezone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJduName() {
		return jduName;
	}

	public void setJduName(String jduName) {
		this.jduName = jduName;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	
	@Override
	public String toString() {
		return String.format("JduForm [Id = %d, name = %s, timezone = %s]",
				id, jduName, timezone);
	}
}
