package com.fujitsu.ph.tsup.domain.oviedo;

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
		// TODO Auto-generated method stub

	}

	@Override
	public Long saveVenue(Venue venue) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO VENUE(id,Venuename)" + "VALUES(:id, :vName)";

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", venue.getId())
				.addValue("empNumber", venue.getVenueName());

		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		template.update(sql, namedParameters, generatedKeyHolder);
		return (Long) generatedKeyHolder.getKeys().get("id");
	}

	@Override
	public Set<Venue> findAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT id, Venuename FROM VENUE";
		List<Venue> venue = template.query(sql, new VenueRowMapper());
		Set<Venue> result = new HashSet<Venue>(venue);
		return result;
	}

	@Override
	public Venue findById(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT id, Venuename FROM VENUE WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, namedParameters, new VenueRowMapper());
	}
}
