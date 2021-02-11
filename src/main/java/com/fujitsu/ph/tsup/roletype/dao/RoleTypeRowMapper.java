/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */

package com.fujitsu.ph.tsup.roletype.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.roletype.domain.RoleType;

/**
 * RoleTypeRowMapper class
 * 
 * @author rl.naval (New Creation by: rl.naval)
 * @version Revision: 0.01 Date: 2021-02-05
 */
public class RoleTypeRowMapper implements RowMapper<RoleType> {

    @Override
    public RoleType mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String rolename = rs.getString("role_type");
        String roledesc = rs.getString("role_desc");

        RoleType roletype = new RoleType.Builder(id, rolename).roledesc(roledesc).build();

        return roletype;

    }
}
