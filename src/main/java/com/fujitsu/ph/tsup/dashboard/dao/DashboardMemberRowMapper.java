package com.fujitsu.ph.tsup.dashboard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardMember;
//==================================================================================================
//$Id:PR06$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardMemberRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/23/2020 |  WS) Jm.Deguzman   | New Creation
//0.02 | 08/24/2020 |  WS) Jm.Deguzman   | Updated
//==================================================================================================
/**
* <pre>
* The row mapper for the dashboard of the member
* <pre>
* 
* @version 0.01
* @author Jm.Deguzman
*/
public class DashboardMemberRowMapper implements RowMapper<DashboardMember> {
    /**
     * Row Mapper
     * @param rs
     * @param rowNum
     * @return DashboardMemberForm
     */
    @Override
    public DashboardMember mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        String courseName = rs.getString("C.NAME");
        String instructorName = rs.getString("FULL_NAME");
        ZonedDateTime startDateTime = ZonedDateTime.ofInstant(rs.getTimestamp("CSD.SCHEDULED_START_DATETIME").toInstant(), ZoneId.of("Asia/Manila"));
        ZonedDateTime endDateTime = ZonedDateTime.ofInstant(rs.getTimestamp("CSD.SCHEDULED_END_DATETIME").toInstant(), ZoneId.of("Asia/Manila"));
        String venueName = rs.getString("V.NAME");
        Long employeeId = rs.getLong("E.ID");
        String status = rs.getString("CS.STATUS");
        
        return new DashboardMember.Builder(courseName,  instructorName, startDateTime, endDateTime, venueName, employeeId, status).build();
    }

}
