package com.fujitsu.ph.tsup.authz.autoregister.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.authz.autoregister.model.AutoRegistrationMemberRole;

//=======================================================
//$Id: 
//Project Name: Training Sign Up
//Class Name: MemberRoleMapper.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 2021/06/07 | WS) R.Gaquit	  | Created
//=======================================================

/**
 * MemberRoleMapper class
 * 
 * @author r.gaquit (New Creation by: r.gaquit)
 * @version 0.01
 */

public class MemberRoleMapper implements RowMapper<AutoRegistrationMemberRole>{

	@Override
	public AutoRegistrationMemberRole mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
        String roleType = rs.getString("role_type");
        AutoRegistrationMemberRole role = new AutoRegistrationMemberRole.Builder(id, roleType)
                .build();
        return role;
	}

}
