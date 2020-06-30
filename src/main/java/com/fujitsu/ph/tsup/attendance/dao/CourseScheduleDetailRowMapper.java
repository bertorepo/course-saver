package com.fujitsu.ph.tsup.attendance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.jdbc.core.RowMapper;
import com.fujitsu.ph.tsup.attendance.domain.CourseScheduleDetail;
//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :CourseScheduleDetailRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/26/2020 |  WS) J. Iwarat        | New Creation
//==================================================================================================
/**
* <pre>
* It is the row mapping class that maps each row to a result object
* In this class, it maps the row of a resultset on a per row basis
* </pre>
* 
* @version 0.01
* @author j.iwarat
*/
public class CourseScheduleDetailRowMapper implements RowMapper<CourseScheduleDetail> {

    @Override
    public CourseScheduleDetail mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        Long courseScheduleId = rs.getLong("COURSE_SCHEDULE_ID");

        ZonedDateTime scheduledStartDateTime = ZonedDateTime
                .ofInstant(rs.getTimestamp("SCHEDULED_START_DATETIME").toInstant(), ZoneId.of("UTC"));

        ZonedDateTime scheduledEndDateTime = ZonedDateTime
                .ofInstant(rs.getTimestamp("SCHEDULED_END_DATETIME").toInstant(), ZoneId.of("UTC"));

        CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(id, courseScheduleId,
                scheduledStartDateTime, scheduledEndDateTime).build();
        return courseScheduleDetail;
    }

}
