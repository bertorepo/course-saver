/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.venue.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.venue.dao.VenueDao;
import com.fujitsu.ph.tsup.venue.domain.Venue;

//=======================================================
//Project Name: Training Sign Up
//Class Name: VenueServiceImpl.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 03/06/2021 | WS) dw.cardenas  | Created
//=======================================================

/**
 *
 * @version 0.01
 * @author dw.cardenas
 *
 */
@Service
public class VenueServiceImpl implements VenueService {

	@Autowired
	VenueDao venueDao;

	@Override
	public Set<Venue> findAllVenues() {
		return venueDao.findAllVenues();
	}

	@Override
	public Set<Venue> findVenueByName(String name) {
		return venueDao.findVenueByName(name);
	}

	@Override
	public void createVenue(Venue venue) {
		try {
			venueDao.createVenue(venue);
		} catch (Exception ex) {
			throw new IllegalArgumentException("Failed to save new venue");
		}
	}

	@Override
	public void updateVenue(Venue venue) {
		try {
			venueDao.updateVenue(venue);
		} catch (Exception ex) {
			throw new IllegalArgumentException("Failed to save new venue");
		}
	}

	@Override
	public void deleteVenue(Long id) {
		try {
			venueDao.deleteVenueById(id);
		} catch (Exception ex) {
			throw new IllegalArgumentException("Failed to save new venue");
		}
	}
}
