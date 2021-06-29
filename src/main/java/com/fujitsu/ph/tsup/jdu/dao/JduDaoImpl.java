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
		String query = "SELECT * FROM jdu_type ORDER BY jdu_name";
		List<Jdu> jduList = template.query(query, new JduRowMapper());
		
		return new LinkedHashSet<>(jduList);
	}

	@Override
	public void createJdu(Jdu newJdu) {
		String query = "INSERT INTO JDU_TYPE(jdu_name) VALUES(:jdu_name)";
		SqlParameterSource sqlParamSource = new MapSqlParameterSource()
				.addValue("jdu_name", newJdu.getJduName());

		template.update(query, sqlParamSource);
	}

	@Override
	public void updateJdu(Jdu updatedJdu) {
		String query = "UPDATE JDU_TYPE SET jdu_name = :jdu_name WHERE id = :id";
		SqlParameterSource sqlParamSource = new MapSqlParameterSource()
				.addValue("id", updatedJdu.getId())
				.addValue("jdu_name", updatedJdu.getJduName());

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
