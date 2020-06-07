package com.fujitsu.ph.tsup.domain.abad;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class VenueDaoImpl implements VenueDao {

    @Autowired
    private NamedParameterJdbcTemplate template;
    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    @Override
    public void save(Venue venue) {
        String sql = "INSERT INTO VENUE(venue_name)"
                + "VALUES(:venueName)";      
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("venueName", venue.getVenueName());
        template.update(sql, namedParameters, generatedKeyHolder);
        saveVenue();
    }

    @Override
    public Set<Venue> findAll() {
        String sql = "SELECT id, venue_name FROM VENUE";
        List<Venue> venue = template.query(sql, new VenueRowMapper());
        Set<Venue> vn = new HashSet<Venue>(venue);
        return vn;
    }

    @Override
    public Venue findById(Long id) {
        String sql = "SELECT id, venue_name FROM VENUE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, namedParameters, new VenueRowMapper());
    }

    @Override
    public Long saveVenue() {
        return (Long) generatedKeyHolder.getKeys().get("id");
    }

}

