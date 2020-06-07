package com.fujitsu.ph.tsup.domain.oviedo;

import java.util.Set;

public interface VenueDao {
	void save(Venue venue);
	  Long saveVenue(Venue venue);
	  Set<Venue> findAll();
	  Venue findById(Long id);
}
