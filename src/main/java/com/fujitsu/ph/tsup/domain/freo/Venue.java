package com.fujitsu.ph.tsup.domain.freo;

public class Venue {

	private Long Id;
	private String venueName;

	protected Venue() {
		
	}
	private Venue (Builder builder) {
		this.Id = builder.Id;
		this.venueName = builder.venueName;	
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
		
		this.Id = Id;
		this.venueName = venueName;
	}
	
	public Builder(String venueName) {
		validatevenueName(venueName);
		
		this.venueName=venueName;
	}
	
	public Venue builder() {
		return new Venue (this);
	}
	
	private void validateId(Long Id) {
		if (Id == null) {
			throw new IllegalArgumentException("ID Should not be empty");
		}
	}
	
	private void validatevenueName(String venueName) {
		if (venueName== null ) {
			throw new IllegalArgumentException("The Venue Name Should not be empty");	
			}else if(venueName.length() < 8) {
                throw new IllegalArgumentException("The Venue Name should not be less than 8 characters");
            } else if(venueName.length() > 50) {
                throw new IllegalArgumentException("The Venue Name should not be more than 50 characters");
            }
		}
	}
}
