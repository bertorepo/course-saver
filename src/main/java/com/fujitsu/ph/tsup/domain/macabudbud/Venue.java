package com.fujitsu.ph.tsup.domain.macabudbud;

public class Venue {
	private Long id;
	private String venueName;
	
	protected Venue() {	
	}
	
	private Venue(Builder builder) {
		this.id = builder.id;
		this.venueName = builder.venueName;
	}
	
	public static class Builder {
		private Long id;
		private String venueName;
		
		public Builder(Long id, String venueName) {
			validateVenueName(venueName);
			
			this.id = id;
			this.venueName = venueName;
		}
		
		public Venue build() {
			return new Venue(this);
		}
		
		public void validateVenueName(String venueName) {
			if(venueName == null || venueName.isEmpty()) {
				throw new IllegalArgumentException("Field required. Venue name should notn be empty");
			}else if(venueName.length() > 100){
				throw new IllegalArgumentException("Characters exceeds to 100.");
			}
		}
	}
	
	public Long getId() {
		return id;
	}
	public String getVenueName() {
		return venueName;
	}	
	
}
