package com.fujitsu.ph.tsup.jdu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.jdu.domain.Jdu;

public class JduRowMapper implements RowMapper<Jdu> {
	@Override
	public Jdu mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String jduName = rs.getString("jdu_name");

		return Jdu.builder().addId(id).addJduName(jduName).build();
	}
}
