package com.fujitsu.ph.tsup.attendance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.attendance.domain.CourseParticipant;





/**
 * <pre>
 * It is the row mapping class that maps each row to a result object
 * In this class, it maps the row of a resultset on a per row basis
 * </pre>
 * 
 * @version 0.01
 * @author h.francisco
 *
 */

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign up
//System Name  :Attendance process 
//Class Name   :CourseParticipantRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/26/2020 | WS) h.francisco       | New Creation

public class CourseParticipantRowMapper implements RowMapper<CourseParticipant> {

    @Override
    public CourseParticipant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        Long courseScheduleId = rs.getLong("course_schedule_id");
        String courseName = rs.getString("course_name");
        String instructorName = rs.getString("instructor_name");
        String venueName = rs.getString("venue_name");
        Long participantId = rs.getLong("participant_id");
        String participantName = rs.getString("participant_name");
        ZonedDateTime scheduledStartDateTime = ZonedDateTime
                .ofInstant(rs.getTimestamp("scheduled_start_date_time").toInstant(), ZoneId.of("UTC"));
        ZonedDateTime scheduledEndDateTime = ZonedDateTime
                .ofInstant(rs.getTimestamp("scheduled_end_date_time").toInstant(), ZoneId.of("UTC"));
        Float duration = rs.getFloat("duration");
        ZonedDateTime registrationDate = ZonedDateTime.ofInstant(rs.getTimestamp("registration_date").toInstant(),
                ZoneId.of("UTC"));
        String email = rs.getString("email");
        String employeeNumber = rs.getString("employee_number");
        CourseParticipant participants = new CourseParticipant.Builder(id, courseScheduleId, courseName, instructorName,
                venueName, participantId, participantName, scheduledStartDateTime, scheduledEndDateTime, duration,
                registrationDate, email, employeeNumber).build();

        return participants;
    }

}

