package com.fujitsu.ph.tsup.domain.francisco;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class VenueRowMapper implements RowMapper<Venue>{

    @Override
    public Venue mapRow(ResultSet rs, int rowNumber) throws SQLException {
        Long id = rs.getLong("id");
        String venueName = rs.getString("name");

        Venue venues = new Venue.Builder(id, venueName).build();

        return venues;
    }

}
