package com.fujitsu.ph.tsup.attendance.dao;

import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.springframework.jdbc.core.RowMapper;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :CourseAttendanceRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated By                                      | Content
//--------+------------+-------------------------------------------------+--------------------------
//0.01    | 06/26/2020 |  WS) J. Iwarat                                  | New Creation
//0.02    | 07/07/2020 |  WS) J. Iwarat                                  | Update
//0.03    | 07/08/2020 |  WS) J. Iwarat                                  | Update
//0.04    | 07/27/2020 |  WS) K. Abad, WS) J. Iwarat, WS) R.Ramos        | Update
//==================================================================================================
/**
 * <pre>
 * It is the row mapping class that maps each row to a result object
 * In this class, it maps the row of a resultset on a per row basis
 * </pre>
 * 
 * @version 0.04
 * @author k.abad
 * @author j.iwarat
 * @author r.ramos
 */
public class CourseAttendanceRowMapper implements RowMapper<CourseAttendance> {

    @Override
    public CourseAttendance mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        Long courseScheduleDetailId = rs.getLong("COURSE_SCHEDULE_ID");
        String courseName = rs.getString("COURSE_NAME");
        String instructorName = rs.getString("INSTRUCTOR_LAST_NAME") + 
                ", " + rs.getString("INSTRUCTOR_FIRST_NAME");
        String venueName = rs.getString("VENUE_NAME");
        Long participantId = rs.getLong("PARTICIPANT_ID");
        String participantName = rs.getString("PARTICIPANT_LAST_NAME") + 
                ", " + rs.getString("PARTICIPANT_FIRST_NAME");
        float duration = rs.getFloat("DURATION");
        char status = rs.getString("STATUS").charAt(0);

        ZonedDateTime scheduledStartDateTime = ZonedDateTime
                .ofInstant(rs.getTimestamp("SCHEDULED_START_DATETIME").toLocalDateTime()
                .toInstant(ZoneOffset.UTC),ZoneId.of("UTC")); 
        ZonedDateTime scheduledEndDateTime = ZonedDateTime
                .ofInstant(rs.getTimestamp("SCHEDULED_END_DATETIME").toLocalDateTime()
                .toInstant(ZoneOffset.UTC),ZoneId.of("UTC"));
        ZonedDateTime loginDateTime = ZonedDateTime
                .ofInstant(rs.getTimestamp("LOG_IN_DATETIME").toLocalDateTime()
                .toInstant(ZoneOffset.UTC),ZoneId.of("UTC"));

        CourseAttendance courseAttendance = new CourseAttendance.Builder(id, courseScheduleDetailId, 
                courseName,instructorName, venueName, participantId, participantName, scheduledStartDateTime, 
                scheduledEndDateTime,duration, loginDateTime, status).build();
        return courseAttendance;
    }
}