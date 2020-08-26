package com.fujitsu.ph.tsup.dashboard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardPmo;
//==================================================================================================
//$Id:PR06$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardPmoRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/25/2020 |  WS) Jm.Deguzman   | New Creation
//0.02 | 08/24/2020 |  WS) Jm.Deguzman   | Update
//==================================================================================================
/**
* <pre>
* The row mapper for the dashboard of the PMO
* <pre>
* 
* @version 0.01
* @author Jm.Deguzman
*/
public class DashboardPmoRowMapper implements RowMapper<DashboardPmo> {
    /**
     * Row Mapper
     * @param rs
     * @param rowNum
     * @return DashboardPmoForm
     */
    @Override
    public DashboardPmo mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        String courseName = rs.getString("C.NAME");
        String instructorName = rs.getString("FULL_NAME");
        ZonedDateTime startDateTime = ZonedDateTime.ofInstant(rs.getTimestamp("CSD.SCHEDULED_START_DATETIME").toInstant(), ZoneId.of("Asia/Manila"));
        ZonedDateTime endDateTime = ZonedDateTime.ofInstant(rs.getTimestamp("CSD.SCHEDULED_END_DATETIME").toInstant(), ZoneId.of("Asia/Manila"));
        int minRequired = rs.getInt("CS.MIN_REQUIRED");
        int maxAllowed = rs.getInt("CS.MAX_ALLOWED");
        int enrolled = rs.getInt("ENROLLED");
        String status = rs.getString("CS.STATUS");
        
        return new DashboardPmo.Builder(courseName,  instructorName, startDateTime, endDateTime, minRequired, maxAllowed, enrolled, status).build();
    }

}
