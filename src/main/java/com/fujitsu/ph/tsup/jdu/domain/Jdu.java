package com.fujitsu.ph.tsup.jdu.domain;

public class Jdu {
	private Long id;
	private String jduName;
	private String timezone;
	
	private Jdu(Builder builder) {
		validateJduName(builder.jduName);
		validateTimezone(builder.timezone);
		this.id = builder.id;
		this.jduName = builder.jduName;
		this.timezone = builder.timezone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJduName() {
		return jduName;
	}

	public void setJduName(String jduName) {
		this.jduName = jduName;
	}
	
	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	private void validateJduName(String jduName) {
		if (jduName == null || jduName.trim().isEmpty()) {
			throw new IllegalArgumentException("Jdu name should not be empty.");
		}
	}

	private void validateTimezone(String timezone) {
		if (timezone == null || timezone.trim().isEmpty()) {
			throw new IllegalArgumentException("Timezone should not be empty.");
		}
	}
	
	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Long id;
		private String jduName;
		private String timezone;
		
		private Builder() {}
		
		public Builder addId(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder addJduName(String jduName) {
			this.jduName = jduName;
			return this;
		}
		
		public Builder addTimezone(String timezone) {
			this.timezone = timezone;
			return this;
		}
		
		public Jdu build() {
			return new Jdu(this);
		}
	}
}
