package com.fujitsu.ph.tsup.venue.management.model;
/*
 * 	Author: Jimenez, John Carlo, R.
 *  Last Modified: 6/2/2020
 * 
 */

import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VenueUpdateForm {
	
	private Set<VenueNames> VNs;
	
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