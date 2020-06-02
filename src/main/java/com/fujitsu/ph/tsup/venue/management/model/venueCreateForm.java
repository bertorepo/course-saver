package com.fujitsu.ph.tsup.venue.management.model;

import java.util.Set;

public class venueCreateForm {

	private int id;
	private String venueName;
	private Set<VenueNames> VNs;

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