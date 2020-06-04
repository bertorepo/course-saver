package com.fujitsu.ph.tsup.domain.rivera;

public class Venue {
	private Long venueId;
	private String venueName;
	
	protected Venue() {
		
	}
	
	private Venue(Builder builder) {
		this.venueId = builder.venueId;
		this.venueName = builder.venueName;
	}
	
	public Long getVenueId() {
		return venueId;
	}
	
	public String getVenueName() {
		return venueName;
	}
	
	public static class Builder{
		private Long venueId;
		private String venueName;
		
		public Builder(String venueName) {
			validateVenueName(venueName);
			this.venueName = venueName;
		}
		
		public Venue build() {
			return new Venue(this);
		}
		
		private void validateVenueName(String venueName) {
			if(venueName == null || venueName.isEmpty() || venueName.length() < 5 || venueName.length() > 50) {
				throw new IllegalArgumentException("venue name should not be empty.");
			}
		}
	}
}
