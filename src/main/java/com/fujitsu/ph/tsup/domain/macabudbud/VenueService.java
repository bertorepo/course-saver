package com.fujitsu.ph.tsup.domain.macabudbud;

import java.util.Set;

public interface VenueService {
	void save(Venue venue);

	Set<Venue> findAll();

	Venue findById(Long id);
}
