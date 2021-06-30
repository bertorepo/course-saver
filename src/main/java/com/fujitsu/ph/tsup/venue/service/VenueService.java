/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.venue.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fujitsu.ph.tsup.venue.domain.Venue;

//============================================================================================
//Project Name: Training Sign Up
//Class Name: VenueService.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 03/06/2021 | WS) dw.cardenas  | Created
//0.02    | 30/06/2021 | WS) mi.aguinaldo | Overload the findAllVenues
//============================================================================================

/**
 *
 * @version 0.01
 * @author dw.cardenas
 *
 */
public interface VenueService {

	/**
	 * <pre>
	 * Gets all venues.
	 * <pre>
	 *
	 * @return all venues
	 */
	Set<Venue> findAllVenues();

	
	/**
	 * <pre>
	 * Gets all venues.
	 * <pre>
	 *
	 * @return all venues
	 */
	Page<Venue> findAllVenues(Pageable pagable);
	
	
	/**
	 * <pre>
	 * Find venues that has specific name.
	 * <pre>
	 *
	 * @param name
	 * @return venues with matching name
	 */
	Set<Venue> findVenueByName(String name);

	/**
	 * <pre>
	 * Add a new venue.
	 * <pre>
	 *
	 * @param venue
	 */
	void createVenue(Venue venue);

	/**
	 * <pre>
	 * Update an existing venue.
	 * <pre>
	 *
	 * @param venue
	 */
	void updateVenue(Venue venue);

	/**
	 * <pre>
	 * Delete an existing venue.
	 * <pre>
	 *
	 * @param id
	 */
	void deleteVenue(Long id);
}
