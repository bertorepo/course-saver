package com.fujitsu.ph.tsup.domain.macabugao;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class VenueServiceImpl implements VenueService {

	@Autowired
	private VenueDao venueDao;

	public void save(Venue venue) {
		try {
			venueDao.save(venue);
		} catch (IllegalArgumentException ex) {
			throw new IllegalApplicationException("Venue not save", ex);
		}

	}

	public Set<Venue> findAll() {
		try {
			return venueDao.findAll();
		} catch (IllegalArgumentException ex) {
			throw new IllegalApplicationException("Cannot find venue", ex);
		}
	}


	public Venue findById(Long id) {
		try {
			return venueDao.findById(id);
		} catch (IllegalArgumentException ex) {
			throw new IllegalApplicationException("Id not found", ex);
		}
	}

	}

