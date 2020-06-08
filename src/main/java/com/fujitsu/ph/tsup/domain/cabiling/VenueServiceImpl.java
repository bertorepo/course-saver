package com.fujitsu.ph.tsup.domain.cabiling;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class VenueServiceImpl implements VenueService{
	
	@Autowired
	private VenueDao dao;

	@Override
	public void save(Venue venue) {
		try {
			dao.save(venue);
		} catch (DataAccessException ex) {
			throw new VenueException("Venue not saved", ex);
		}

	}

	@Override
	public Set<Venue> findAll() {
		try {
			return dao.findAll();
		} catch (DataAccessException ex) {
			throw new VenueException("No venue found", ex);
		}
	}

	@Override
	public Venue findById(Long id) {
		try {
			return dao.findById(id);
		} catch (DataAccessException ex) {
			throw new VenueException("No venue found for that id", ex);
		}
	}

}
