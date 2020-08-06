package com.fujitsu.ph.tsup.attendance.dao;

import com.fujitsu.ph.tsup.attendance.domain.CourseParticipant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.springframework.jdbc.core.RowMapper;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign up
//System Name  :Attendance process 
//Class Name   :CourseParticipantRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated By                                      | Content
//--------+------------+-------------------------------------------------+--------------------------
//0.01    | 06/26/2020 | WS) h.francisco                                 | New Creation
//0.02    | 06/30/2020 | WS) K.Abad                                      | Update
//0.03    | 07/07/2020 | WS) K.Abad                                      | Update
//0.04    | 07/27/2020 | WS) K. Abad, WS) J. Iwarat, WS) R.Ramos         | Update
//==================================================================================================
/**
 * <pre>
 * It is the row mapping class that maps each row to a result object
 * In this class, it maps the row of a resultset on a per row basis
 * </pre>
 * 
 * @version 0.04
 * @author k.abad
 * @author h.francisco
 * @author j.iwarat
 * @author r.ramos
 */

public class CourseParticipantRowMapper implements RowMapper<CourseParticipant> {

    @Override
    public CourseParticipant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        Long courseScheduleId = rs.getLong("COURSE_SCHEDULE_ID");
        String courseName = rs.getString("COURSE_NAME");
        String instructorName = rs.getString("INSTRUCTOR_LAST_NAME") +  
                ", " + rs.getString("INSTRUCTOR_FIRST_NAME");
        String venueName = rs.getString("VENUE_NAME");
        Long participantId = rs.getLong("PARTICIPANT_ID");
        String participantName = rs.getString("PARTICIPANT_LAST_NAME") +  
                ", " + rs.getString("PARTICIPANT_FIRST_NAME");
        ZonedDateTime scheduledStartDateTime = ZonedDateTime
                .ofInstant(rs.getTimestamp("SCHEDULED_START_DATETIME").toLocalDateTime()
                .toInstant(ZoneOffset.UTC),ZoneId.of("UTC")); 
        ZonedDateTime scheduledEndDateTime = ZonedDateTime
                .ofInstant(rs.getTimestamp("SCHEDULED_END_DATETIME").toLocalDateTime()
                .toInstant(ZoneOffset.UTC),ZoneId.of("UTC"));
        Float duration = rs.getFloat("DURATION");
        ZonedDateTime registrationDate = ZonedDateTime
                .ofInstant(rs.getTimestamp("REGISTRATION_DATE").toLocalDateTime()
                .toInstant(ZoneOffset.UTC),ZoneId.of("UTC"));
        String email = rs.getString("EMAIL");
        String employeeNumber = rs.getString("EMPLOYEE_NUMBER");
        
        CourseParticipant participants = new CourseParticipant.Builder(id, courseScheduleId, courseName, 
                instructorName, venueName, participantId, participantName, scheduledStartDateTime, 
                scheduledEndDateTime, duration, registrationDate, email, employeeNumber).build();
        return participants;
    }
}

