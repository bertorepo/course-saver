package com.fujitsu.ph.tsup.venue.management.model;
/*
 * 	Author: Jimenez, John Carlo, R.
 *  Last Modified: 6/2/2020
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VenueUpdateForm {
	@NotNull
	private long Id;
	@NotBlank
	private String venue;
	@Size(min=1, max=50)
	private List<VenueNames> ListVenue = new ArrayList<>();
	
	public String getVenue() {
		return venue;
	}
	
	public long getId() {
		return Id;
	}
	
	public void setVenue(String Venue) {
		this.venue = Venue;
	}
	
	public void setId(long id) {
		this.Id = id;
	}
	
	public void setVNLst(List<VenueNames> list) { 
		this.ListVenue = list;
	}
	
	@Override
	public String toString() {
		return "VenueUpdateForm [Id = "+ Id +", venue = "+ venue +", VenueNames =" + ListVenue +"]";
	}
}
