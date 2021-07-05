/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.venue.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	private VenueDao venueDao;

	@Override
	public Set<Venue> findAllVenues() {
		return venueDao.findAllVenues();
	}
	
	

	@Override
	public Page<Venue> findAllVenues(Pageable pagable) {
	    List<Venue> venues = venueDao.findAllVenues(pagable)
					  .stream()
					  .collect(Collectors.toList());
	    
	    int countVenue = venueDao.countVenue();
	    
	    return new PageImpl<>(venues,pagable,countVenue);
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
			throw new IllegalArgumentException("Failed to update venue");
		}
	}

	@Override
	public void deleteVenue(Long id) {
		try {
			venueDao.deleteVenueById(id);
		} catch (Exception ex) {
			throw new IllegalArgumentException("Failed to delete venue");
		}
	}
}
