package com.fujitsu.ph.tsup.domain.freo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


public class VenueDaoImp implements VenueDao {

	 
    @Autowired
    private NamedParameterJdbcTemplate template;
    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

	@Override
	public void save(Venue venue) {
		 String sql = "INSERT INTO venue(venue_name)"
                 + "VALUES(:name)";
    SqlParameterSource namedParams = new MapSqlParameterSource()
            .addValue("name", venue.getVenueName());
    template.update(sql, namedParams, generatedKeyHolder);
    returnGeneratedKey();
	
	}

	@Override
	public Set<Venue> findAll() {
		String query = "SELECT id, name FROM venue";
        List<Venue> vnu = template.query(query, new VenueRowMapper());
        Set<Venue> venue = new HashSet<Venue>(vnu);
        
        return venue;
	}

	@Override
	public Venue findById(Long Id) {
		
	        String query = "SELECT id, name FROM venue WHERE id = :id";
	        SqlParameterSource namedParams = new MapSqlParameterSource().addValue("Id", Id);
	        return template.queryForObject(query, namedParams, new VenueRowMapper());
	}
		  @Override
		    public Long returnGeneratedKey() {
		        return (Long) generatedKeyHolder.getKeys().get("id");
		    }
}
