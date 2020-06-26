package com.fujitsu.ph.tsup.dashboard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardMemberForm;
//==================================================================================================
//$Id:$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardMemberRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/23/2020 |  WS) Jm.Deguzman   | New Creation
//==================================================================================================
/**
* <pre>
* The row mapper for the dashboard of the member
* <pre>
* 
* @version 0.01
* @author Jm.Deguzman
*/
public class DashboardMemberRowMapper implements RowMapper<DashboardMemberForm> {
    /**
     * Row Mapper
     * @param rs
     * @param rowNum
     * @return DashboardMemberForm
     */
    @Override
    public DashboardMemberForm mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        String courseName = rs.getString("c.name");
        String instructorName = rs.getString("full_name");
        ZonedDateTime startDateTime = ZonedDateTime.ofInstant(rs.getTimestamp("csd.scheduled_start_datetime").toInstant(), ZoneId.of("Asia/Manila"));
        ZonedDateTime endDateTime = ZonedDateTime.ofInstant(rs.getTimestamp("csd.scheduled_end_datetime").toInstant(), ZoneId.of("Asia/Manila"));
        String venueName = rs.getString("v.name");
        Long employeeId = rs.getLong("e.id");
        String status = rs.getString("cs.status");
        
        return new DashboardMemberForm.Builder(courseName,  instructorName, startDateTime, endDateTime, venueName, employeeId, status).build();
    }

}
