package com.fujitsu.ph.tsup.domain.deguzman;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public void save(Venue venue) {
        return;
    }

    @Override
    public Set<Venue> findAll() {
        String sql = "SELECT id, name FROM VENUE";
        List<Venue> venue = template.query(sql, new VenueRowMapper());
        Set<Venue> v = new HashSet<Venue>(venue);
        return v;
    }

    @Override
    public Venue findById(Long id) {
        String sql = "SELECT id, name FROM VENUE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, namedParameters, new VenueRowMapper());
    }

    @Override
    public Long saveLong(Venue venue) {
        String sql = "INSERT INTO VENUE(name)"
                + "VALUES(:name)";
        
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name",venue.getVenueName());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        template.update(sql, namedParameters, generatedKeyHolder);
        return (Long) generatedKeyHolder.getKeys().get("id");
    }

}
