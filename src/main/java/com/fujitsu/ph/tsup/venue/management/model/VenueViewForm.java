package com.fujitsu.ph.tsup.venue.management.model;

public class VenueViewForm {

	private String name;
	private int id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "VN [id=" + id + ", name=" + name + "]";
	}

}