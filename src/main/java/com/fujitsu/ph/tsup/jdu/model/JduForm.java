package com.fujitsu.ph.tsup.jdu.model;

public class JduForm {
	private Long id;
	private String jduName;
	private String timezone;

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
	
	@Override
	public String toString() {
		return String.format("JduForm [Id = %d, name = %s, timezone = %s]",
				id, jduName, timezone);
	}
}
