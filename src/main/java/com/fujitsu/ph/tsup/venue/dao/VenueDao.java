/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.venue.dao;

import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.fujitsu.ph.tsup.venue.domain.Venue;

//===============================================================================================
//Project Name: Training Sign Up
//Class Name: VenueDao.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 03/06/2021 | WS) dw.cardenas  | Created
//0.02    | 30/06/2021 | WS) mi.aguinaldo | added count venue and overload the findAllVenues
//===============================================================================================
/**
 *
 * @version 0.01
 * @author dw.cardenas
 *
 */
public interface VenueDao {

	/**
	 * <pre>
	 * Adds a new venue to the database.
	 * <pre>
	 *
	 * @param venue
	 */
	void createVenue(Venue venue);

	/**
	 * <pre>
	 * Finds a specific venue in the database with specific id.
	 * <pre>
	 *
	 * @param id
	 * @return venue with specific id
	 */
	Venue findVenueById(Long id);

	/**
	 * <pre>
	 * Finds a specific venue in the database with specified name.
	 * <pre>
	 *
	 * @param name
	 * @return venue with specific name
	 */
	Set<Venue> findVenueByName(String name);

	/**
	 * <pre>
	 * Finds all venues in the database.
	 * <pre>
	 *
	 * @return all venues
	 */
	Set<Venue> findAllVenues();
	
	/**
	 * <pre>
	 * Finds all venues in the database.
	 * <pre>
	 *
	 * @return all venues
	 */
	Set<Venue> findAllVenues(Pageable pageable);
	
	/**
	 * <pre>
	 * Counts all the venue in the database.
	 * <pre>
	 *
	 * @return Total number of venue
	 */
	 int countVenue();

	/**
	 * <pre>
	 * Updates an existing venue in the database
	 * <pre>
	 *
	 * @param venue
	 */
	void updateVenue(Venue venue);

	/**
	 * <pre>
	 * Deletes an existing venue in the database using the id.
	 * <pre>
	 *
	 * @param id
	 */
	void deleteVenueById(Long id);
}
