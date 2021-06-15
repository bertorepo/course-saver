/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.venue.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.venue.domain.Venue;

//=======================================================
//Project Name: Training Sign Up
//Class Name: VenueDaoImpl.java
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
@Repository
public class VenueDaoImpl implements VenueDao {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Override
	public void createVenue(Venue venue) {
		String query = "INSERT INTO VENUE" + "(name)" + "VALUES (:name)";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("name", venue.getName());

		template.update(query, sqlParameterSource);
	}

	@Override
	public Venue findVenueById(Long id) {
		String query = "SELECT * FROM VENUE WHERE id = :id";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("id", id);

		return template.queryForObject(query, sqlParameterSource, new VenueRowMapper());
	}

	@Override
	public Set<Venue> findVenueByName(String name) {
		String query = "SELECT * FROM VENUE WHERE LOWER(name) LIKE LOWER('%" + name + "%') ORDER BY name";
		List<Venue> venueList = template.query(query, new VenueRowMapper());
		Set<Venue> venueSet = new LinkedHashSet<>(venueList);

		return venueSet;
	}

	@Override
	public Set<Venue> findAllVenues() {
		String query = "SELECT * FROM VENUE ORDER BY name";
		List<Venue> venueList = template.query(query, new VenueRowMapper());
		Set<Venue> venueSet = new LinkedHashSet<>(venueList);

		return venueSet;
	}

	@Override
	public void updateVenue(Venue venue) {
		String query = "UPDATE VENUE SET name = :name WHERE id = :id";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("name", venue.getName())
				.addValue("id", venue.getId());

		template.update(query, sqlParameterSource);
	}

	@Override
	public void deleteVenueById(Long id) {
		String query = "DELETE FROM VENUE WHERE id = :id";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("id", id);

		template.update(query, sqlParameterSource);
	}

}
