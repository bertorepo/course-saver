package com.fujitsu.ph.tsup.dashboard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardPmoForm;
//==================================================================================================
//$Id:$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardPmoRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/25/2020 |  WS) Jm.Deguzman   | New Creation
//==================================================================================================
/**
* <pre>
* The row mapper for the dashboard of the PMO
* <pre>
* 
* @version 0.01
* @author Jm.Deguzman
*/
public class DashboardPmoRowMapper implements RowMapper<DashboardPmoForm> {
    /**
     * Row Mapper
     * @param rs
     * @param rowNum
     * @return DashboardPmoForm
     */
    @Override
    public DashboardPmoForm mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        String courseName = rs.getString("c.name");
        String instructorName = rs.getString("full_name");
        ZonedDateTime startDateTime = ZonedDateTime.ofInstant(rs.getTimestamp("csd.scheduled_start_datetime").toInstant(), ZoneId.of("Asia/Manila"));
        ZonedDateTime endDateTime = ZonedDateTime.ofInstant(rs.getTimestamp("csd.scheduled_end_datetime").toInstant(), ZoneId.of("Asia/Manila"));
        int minRequired = rs.getInt("cs.min_required");
        int maxAllowed = rs.getInt("cs.max_allowed");
        int enrolled = rs.getInt("enrolled");
        String status = rs.getString("cs.status");
        
        return new DashboardPmoForm.Builder(courseName,  instructorName, startDateTime, endDateTime, minRequired, maxAllowed, enrolled, status).build();
    }

}
