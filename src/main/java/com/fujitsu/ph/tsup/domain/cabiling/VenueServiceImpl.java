package com.fujitsu.ph.tsup.domain.cabiling;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenueServiceImpl implements VenueService{
	
	@Autowired
	private VenueDao dao;

	@Override
	public void save(Venue venue) {
		try {
			dao.save(venue);
		} catch (IllegalArgumentException ex) {
			throw new NewException("Venue not saved", ex);
		}

	}

	@Override
	public Set<Venue> findAll() {
		try {
			return dao.findAll();
		} catch (IllegalArgumentException ex) {
			throw new NewException("No venue found", ex);
		}
	}

	@Override
	public Venue findById(Long id) {
		try {
			return dao.findById(id);
		} catch (IllegalArgumentException ex) {
			throw new NewException("No venue found for that id", ex);
		}
	}

}
