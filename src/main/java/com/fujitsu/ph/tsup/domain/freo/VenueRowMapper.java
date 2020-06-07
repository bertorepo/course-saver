package com.fujitsu.ph.tsup.domain.freo;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;




public class VenueRowMapper implements RowMapper<Venue> {

	@Override
	public Venue mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("Id");
		String name = rs.getString("venueName");
		
		Course course = new Course.Builder(Id, VenueName).builder();
		return Venue;
	}

	

	}

	



