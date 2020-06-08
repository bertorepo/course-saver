package com.fujitsu.ph.tsup.domain.yu;

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

public class VenueDaoImpl implements VenueDao {
    
    @Autowired
    private NamedParameterJdbcTemplate template;

    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    @Override
    public void save(Venue id) {
        String query = "INSERT INTO VENUE(name)" + "VALUES(:name)";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", id.getName());
        template.update(query, namedParameters, generatedKeyHolder);
        generatedKey();
    }
    
    @Override
    public Long generatedKey() {
        return (Long) generatedKeyHolder.getKeys().get("id");
    }

    @Override
    public Set<Venue> findAll() {
        String query = "SELECT id, name FROM VENUE";
        List<Venue> venueList = template.query(query, new VenueRowMapper());
        Set<Venue> venueSet = new HashSet<Venue>(venueList);
        return venueSet;
    }

    @Override
    public Venue findById(Long id) {
        String query = "SELECT id, name FROM VENUE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(query, namedParameters, new VenueRowMapper());
    }

}
