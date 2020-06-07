package com.fujitsu.ph.tsup.domain.oviedo;

import java.util.Set;

public interface VenueService {
	 void save(Venue venue);
	    Set<Venue> findAll();
	    Venue findById(Long id);
}
