/* Created by: Jeamel de Guzman */
package com.fujitsu.ph.tsup.venue.management.model;

import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class VenueListForm {
	
	private Set<VenueNames> VNs;
	@Size(min = 1, max = 50)
	private String search;
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
	public Set<VenueNames> getVNs() {
		return VNs;
	}
	public void setVNs(Set<VenueNames> VNs) {
		this.VNs = VNs;
	}
	@Override
	public String toString() {
		return "VenueListForm [VNs=" + VNs +"]";
	}

}
