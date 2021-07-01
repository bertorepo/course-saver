/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.jdu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.jdu.domain.Jdu;

//=======================================================
//Project Name: Training Sign Up
//Class Name: JduRowMapper.java
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
public class JduRowMapper implements RowMapper<Jdu> {
	@Override
	public Jdu mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String jduName = rs.getString("jdu_name");
		String timezone = rs.getString("timezone");

		return Jdu.builder()
				.addId(id)
				.addJduName(jduName)
				.addTimezone(timezone)
				.build();
	}
}
