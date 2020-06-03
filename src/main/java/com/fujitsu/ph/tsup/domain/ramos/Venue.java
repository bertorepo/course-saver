package com.fujitsu.ph.tsup.domain.ramos;

import java.util.regex.Pattern;
import ch.qos.logback.core.boolex.Matcher;

public class Venue {
	private Long id;
	private String venueName;

	protected Venue() {
	}

	private Venue(Builder builder) {
		this.id = builder.id;
		this.venueName = builder.venueName;
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
		private Pattern pattern;

		public Builder(String venueName) {
			validateVenueName(venueName);
			this.venueName = venueName;
		}

		public Venue build() {
			return new Venue(this);
		}

		private void validateVenueName(String venueName) {
			Pattern.compile("[a-zA-Z0-9]*");
			java.util.regex.Matcher matcher = pattern.matcher(venueName);

			if (venueName == null || venueName.isEmpty()) {
				throw new IllegalArgumentException("Venue Name is empty");
			} else if (!matcher.matches()) {
				throw new IllegalArgumentException("Venue Name contains special characters");
			}
		}
	}
}
