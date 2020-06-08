package com.fujitsu.ph.tsup.domain.angara;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class VenueRowMapper implements RowMapper<Venue> {

    @Override
    public Venue mapRow(ResultSet rs, int RowNum) throws SQLException {
        Long id = rs.getLong("id");
        String venueName = rs.getString("venue_name");
        
        Venue venue = new Venue.Builder(venueName, id).build();
        return venue;

    }

}
