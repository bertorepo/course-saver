package com.fujitsu.ph.tsup.domain.iwarat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class VenueDaoImpl implements VenueDao {
    @Autowired
    private NamedParameterJdbcTemplate template;

    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    @Override
    public void save(Venue venue) {
        String sql = "INSERT INTO VENUE(name)" + "VALUES(:name)";

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("name", venue.getName());
        template.update(sql, namedParameters, generatedKeyHolder);
        GeneratedKeyHolderId();
    }
    
    @Override
    public Long GeneratedKeyHolderId() {
        return (Long) generatedKeyHolder.getKeys().get("id");
    }

    @Override
    public Set<Venue> findAll() {
        String sql = "SELECT id, name FROM venue";
        List<Venue> listVenue = template.query(sql, new VenueRowMapper());
        Set<Venue> setVenue = new HashSet<Venue>(listVenue);

        return setVenue;
    }

    @Override
    public Venue findById(Long id) {
        String sql = "SELECT id, name FROM VENUE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, namedParameters, new VenueRowMapper());
    }

}
