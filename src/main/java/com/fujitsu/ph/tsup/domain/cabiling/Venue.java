package com.fujitsu.ph.tsup.domain.cabiling;

public class Venue {
	private Long id;
	private String venueName;

	protected Venue() {

	}

	private Venue(Builder venue) {
		this.id = venue.id;
		this.venueName = venue.venueName;
	}

	public Long getId() {
		return id;
	}

	public String getVenueName() {
		return venueName;
	}

	public static class Builder {
		private Long id;
		private String venueName;

		public Builder(Long id, String venueName) {
			validateId(id);
			validateVenueName(venueName);

			this.id = id;
			this.venueName = venueName;
		}

		public Venue venue() {
			return new Venue(this);
		}

		private void validateId(Long id) {
			if (id == null) {
				throw new IllegalArgumentException("Id should not be null");
			}

		}

		private void validateVenueName(String venueName) {
			if (venueName == null || venueName.isEmpty() || venueName.length() < 1 || venueName.length() > 100) {
				throw new IllegalArgumentException("Venue Name should not be null or empty");
			}

		}
	}

}
