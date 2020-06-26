package com.fujitsu.ph.tsup.scheduling.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.scheduling.model.VenueForm;

public class VenueRowMapper implements RowMapper<VenueForm>{

	@Override
	public VenueForm mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		VenueForm venueForm = new VenueForm();
		
		Long venueId = rs.getLong("venueId");
		String venueName = rs.getString("venueName");
		venueForm.setId(venueId);
		venueForm.setName(venueName);
		
		
		return venueForm;
	}

}
