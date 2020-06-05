package com.fujitsu.ph.tsup.domain.macabudbud;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenueServiceImpl implements VenueService {
	@Autowired
	private VenueDao dao;

	public Venue getEmployee(Long id) {
		Venue venue = dao.findById(id);
		if (venue.getId() != id) {
			throw new IllegalArgumentException("Venue not found.");
		}
		return venue;
	}

	@Override
	public void save(Venue venue) {
		dao.save(venue);
		if (venue == null) {
			throw new IllegalArgumentException("Unable to save venue");
		}
	}

	@Override
	public Set<Venue> findAll() {
		Set<Venue> venue = dao.findAll();
		if (venue == null || venue.isEmpty()) {
			throw new IllegalArgumentException("No venue found");
		}
		return venue;
	}

	@Override
	public Venue findById(Long venueId) {
		Venue venue = dao.findById(venueId);
		if (venue == null) {
			throw new IllegalArgumentException("No venue found");
		}
		return venue;
	}

}
