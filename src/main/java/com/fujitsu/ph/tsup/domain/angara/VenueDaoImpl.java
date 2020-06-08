package com.fujitsu.ph.tsup.domain.angara;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class VenueDaoImpl implements VenueDao {
    
    @Autowired
    private JdbcTemplate template;
    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
    
    @Override
    public void save(Venue venue) {
        String sql = "INSERT INTO VENUE(id, venue_name)" + "VALUES(:id, :venueName)";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", venue.getId());
        template.update(sql, namedParameters, generatedKeyHolder);
        return;
    }
    
    @Override
    public Set<Venue> findAll() {
        String sql = "SELECT id, venue_name FROM VENUE";
        List<Venue> venue = template.query(sql, new VenueRowMapper());
        Set<Venue> v = new HashSet<Venue>(venue);
        return v;
    }
    @Override
    public Venue findById(Long id) {
        String sql = "SELECT id, venue_name FROM VENUE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return (Venue) template.queryForMap(sql, namedParameters, new VenueRowMapper());
    }
  
}
