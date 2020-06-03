/* Created by: Jhaisy Jade Yu */
package com.fujitsu.ph.tsup.venue.management.model;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VenueViewForm {
	
	
	public int id;
	public String venuename;
	@NotBlank(message="Field must not be empty")
	private String search;
	
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getVenueName() {
		return venuename;
	}
	public void setVenueName(String venuename) {
		this.venuename = venuename;
		
	}		
	public String getSearch() {
		return search;
	}  

	public void setSearch(String search) {
		this.search = search;
	}

		@Override
		public String toString() {
			return "VenueViewForm [id=" + id + ", venuename=" + venuename + "]";
}
}