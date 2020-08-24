package com.fujitsu.ph.tsup.scheduling.model;

public class TopLearnersForm {

	/**
     *	Participant id
     */
	private long id;
	
	/**
     *	Place in ranking
     */
	private int place;
	
	/**
     *	Participant Name(LASTNAME, FIRSTNAME)
     */
	private String participantName;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	@Override
	public String toString() {
		return "TopLearnersForm [id=" + id + ", place=" + place + ", participantName=" + participantName + "]";
	}
	
}
