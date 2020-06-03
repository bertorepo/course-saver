package com.fujitsu.ph.tsup.venue.management.model;
/* @Author Kenneth Abad */


import javax.validation.constraints.NotBlank;

public class VenueDeleteForm {
	private int id;
	private String vname;
	@NotBlank
	private String delete;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}
	
	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	@Override
	public String toString() {
		return "VenueNames [vname=" + vname +"]";
	}

}
