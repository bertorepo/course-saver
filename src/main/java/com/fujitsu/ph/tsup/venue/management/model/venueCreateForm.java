/* @Author j.balanon */

package com.fujitsu.ph.tsup.venue.management.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class venueCreateForm {
	
	private int id;
	@NotBlank
	@NotNull
	private String venueName;
	private String submit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	@Override
	public String toString() {
		return "venueCreateForm [id=" + id + ", venueName=" + venueName+ "]";
	}
}
	
