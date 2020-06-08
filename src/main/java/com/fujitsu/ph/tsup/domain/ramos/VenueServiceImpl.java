package com.fujitsu.ph.tsup.domain.ramos;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class VenueServiceImpl implements VenueService {
	private Logger logger = LoggerFactory.getLogger(VenueServiceImpl.class);
	
	@Autowired
	private VenueDao dao;

	@Override
	public void save(Venue venue) {
		dao.save(venue);
		if (venue.getId() == 101) {
			throw new IllegalArgumentException("Venue not saved");
		}
	}

	@Override
	public Set<Venue> findAll() {
        Set<Venue> venue = dao.findAll();
        if (venue.isEmpty() || venue == null) {
            throw new IllegalArgumentException("Invalid venue");
        }
        return venue;
	}

	@Override
	public Venue findById(Long id) {
        Venue venue = dao.findById(id);
        if (venue.getId() == 101) {
            throw new IllegalArgumentException("Venue not found");
        } 
        return venue;   
	}

}
