package com.fujitsu.ph.tsup.venue.management.model;
/*
 * 	Author: Jimenez, John Carlo, R.
 *  Last Modified: 6/2/2020
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VenueUpdateForm {
    @NotNull
	private Long id;
	@NotBlank
	private String venue;

	private Set<VenueNames> vnset = new HashSet<>();
	
	public String getVenue() {
		return venue;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setVenue(String Venue) {
		this.venue = Venue;
	}
	
	public void setId(Long Id) {
		this.id = Id;
	}
	
	public void setVNLst(Set<VenueNames> list) { 
		this.vnset = list;
	}
	
	public Set<VenueNames> getVNlst(){
	    return vnset;
	}
	
	@Override
	public String toString() {
		return "VenueUpdateForm [Id = "+ id +", Venue = "+ venue +", VenueNames =" + vnset +"]";
	}
}
