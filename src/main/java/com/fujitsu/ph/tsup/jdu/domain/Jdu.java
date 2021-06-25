package com.fujitsu.ph.tsup.jdu.domain;

public class Jdu {
	private Long id;
	private String jduName;
	
	private Jdu(Builder builder) {
		validateJduName(builder.jduName);
		this.id = builder.id;
		this.jduName = builder.jduName;
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
	
	private void validateJduName(String jduName) {
		if (jduName == null || jduName.trim().isEmpty()) {
			throw new IllegalArgumentException("Jdu name should not be empty.");
		}
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private Long id;
		private String jduName;
		
		private Builder() {}
		
		public Builder addId(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder addJduName(String jduName) {
			this.jduName = jduName;
			return this;
		}
		
		public Jdu build() {
			return new Jdu(this);
		}
	}
}
