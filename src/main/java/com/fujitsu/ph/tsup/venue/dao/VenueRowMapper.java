/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.venue.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.venue.domain.Venue;

//=======================================================
//Project Name: Training Sign Up
//Class Name: VenueRowMapper.java
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
public class VenueRowMapper implements RowMapper<Venue> {

	@Override
	public Venue mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String name = rs.getString("name");

		Venue venue = Venue.builder()
				.addId(id)
				.addVenueName(name)
				.build();

		return venue;
	}
}
