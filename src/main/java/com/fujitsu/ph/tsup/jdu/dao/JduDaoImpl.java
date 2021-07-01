/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.jdu.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.jdu.domain.Jdu;

//=======================================================
//Project Name: Training Sign Up
//Class Name: JduDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 28/06/2021 | WS) dw.cardenas  | Created
//=======================================================

/**
 *
 * @author dw.cardenas
 *
 */
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

	@Override
	public Set<Jdu> findJduByName(String jduName) {
		String query = "SELECT * FROM jdu_type WHERE LOWER(jdu_name) LIKE LOWER('%" + jduName + "%') ORDER BY jdu_name";
		List<Jdu> jduList = template.query(query, new JduRowMapper());
		
		return new LinkedHashSet<>(jduList);
	}

	@Override
	public void createJdu(Jdu newJdu) {
		String query = "INSERT INTO JDU_TYPE(jdu_name, timezone) VALUES(:jdu_name, :timezone)";
		SqlParameterSource sqlParamSource = new MapSqlParameterSource()
				.addValue("jdu_name", newJdu.getJduName())
				.addValue("timezone", newJdu.getTimezone());

		template.update(query, sqlParamSource);
	}

	@Override
	public void updateJdu(Jdu updatedJdu) {
		String query = "UPDATE JDU_TYPE SET jdu_name = :jdu_name, timezone = :timezone WHERE id = :id";
		SqlParameterSource sqlParamSource = new MapSqlParameterSource()
				.addValue("id", updatedJdu.getId())
				.addValue("jdu_name", updatedJdu.getJduName())
				.addValue("timezone", updatedJdu.getTimezone());

		template.update(query, sqlParamSource);
	}

	@Override
	public void deleteJdu(Long id) {
		String query = "DELETE FROM JDU_TYPE WHERE id = :id";
		SqlParameterSource sqlParamSource = new MapSqlParameterSource()
				.addValue("id", id);

		template.update(query, sqlParamSource);
	}

}
