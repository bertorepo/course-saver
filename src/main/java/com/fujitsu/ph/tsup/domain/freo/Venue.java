package com.fujitsu.ph.tsup.domain.freo;

public class Venue {
	private Long Id;
	private String venueName;

	protected Venue() {
		
	}
	private Venue (Builder builder) {
		this.Id = builder.Id;
		this.venueName=builder.venueName;	
	}
	public Long getId() {
		return Id;
	}
	public String getVenueName() {
		return venueName;
	}
	
	public static class Builder{
		private Long Id;
		private String venueName;
		
	public Builder(Long Id, String venueName) {
		validateId(Id);
		validatevenueName(venueName);
	}

	public Venue builder() {
		return new Venue (this);
	}
	
	private void validateId(Long id2) {
		if (Id == null) {
			throw new IllegalArgumentException("ID Should not be empty");
		}
	}
	private void validatevenueName(String venueName2) {
		if (venueName.isEmpty() || venueName== null) {
			throw new IllegalArgumentException("Course Name Should not be empty");	
			}
		}
	}
}
