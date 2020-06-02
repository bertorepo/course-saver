package com.fujitsu.ph.tsup.venue.management.model;


import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class VenueDeleteForm {
	
	private Set<VenueNames> VNs;
	private String delete;
	
	
	public Set<VenueNames> getVNs() {
		return VNs;
	}
	public void setVNs(Set<VenueNames> VNs) {
		this.VNs = VNs;
	}
	
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	@Override
	public String toString() {
		return "VenueListForm [VNs=" + VNs +", delete=" + delete + "]";
	}

}
