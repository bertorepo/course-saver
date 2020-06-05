package com.fujitsu.ph.tsup.domain.macabugao;

import java.util.Set;

public interface VenueService {
	void save(Venue venue);

	Set<Venue> findAll();

	Venue findById(Long id);

}
