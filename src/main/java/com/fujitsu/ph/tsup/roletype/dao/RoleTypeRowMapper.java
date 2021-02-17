/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */

package com.fujitsu.ph.tsup.roletype.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.roletype.domain.RoleType;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Role Type Management
//Class Name   : RoleTypeRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01   | 2021/02/05 | WS) rl.naval          | Initial Version
//0.02   | 2021/02/16 | WS) s.labador         | Updated
//==================================================================================================

/**
 * <pre>
 * RoleTypeRowMapper class
 * 
 * <pre>
 * 
 * @version 0.01
 * @author rl.naval
 */
public class RoleTypeRowMapper implements RowMapper<RoleType> {

    /**
     * Row Mapper
     * @param rs Result Set
     * @param rowNum
     * @return roletype
     */
    @Override
    public RoleType mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String rolename = rs.getString("role_type");
        String roledesc = rs.getString("role_desc");

        RoleType roletype = new RoleType.Builder(id, rolename).roledesc(roledesc).build();

        return roletype;

    }
}
