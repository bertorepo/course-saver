package com.fujitsu.ph.tsup.domain.oviedo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class VenueRowMapper implements RowMapper<Venue> {
	@Override
	public Venue mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stubs
		Long id = rs.getLong("id");
		String venueName = rs.getString("VenueName");

		return new Venue.Builder(id, venueName).build();
	}
}
