package com.fujitsu.ph.tsup.jdu.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.jdu.domain.Jdu;

@Repository
public class JduDaoImpl implements JduDao {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Override
	public Set<Jdu> findAllJdus() {
		String query = "SELECT * FROM jdu_type ORDER BY jdu_name";
		List<Jdu> jduList = template.query(query, new JduRowMapper());
		
		return new LinkedHashSet<>(jduList);
	}

}
