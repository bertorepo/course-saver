/* @Author j.balanon */

package com.fujitsu.ph.tsup.venue.management.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class venueCreateForm {
	
	private int Id;
	@NotBlank
	@NotNull
	private String VenueName;
	private String Submit;

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public String getVenueName() {
		return VenueName;
	}

	public void setVenueName(String VenueName) {
		this.VenueName = VenueName;
	}
	
	public String getSubmit() {
		return Submit;
	}
	public void setSubmit(String submit) {
		this.Submit = submit;
	}
	@Override
	public String toString() {
		return "venueCreateForm [id=" + Id + ", venueName=" + VenueName+ "]";
	}
}
	
