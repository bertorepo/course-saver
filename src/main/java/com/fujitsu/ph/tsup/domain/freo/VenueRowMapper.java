package com.fujitsu.ph.tsup.domain.freo;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;



public class VenueRowMapper implements RowMapper<Venue> {

	@Override
	public Venue mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String name = rs.getString("venue_name");
		
		Course course = new Course.Builder(id, venuename).builder();
		return Venue;
	}

	

	}

	



