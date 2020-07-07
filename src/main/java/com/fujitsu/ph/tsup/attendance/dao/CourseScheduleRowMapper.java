package com.fujitsu.ph.tsup.attendance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import org.springframework.jdbc.core.RowMapper;
import com.fujitsu.ph.tsup.attendance.domain.CourseSchedule;
import com.fujitsu.ph.tsup.attendance.domain.CourseScheduleDetail;
//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :CourseScheduleRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/26/2020 |  WS) J. Iwarat        | New Creation
//0.02    | 07/07/2020 |  WS) J. Iwarat        | Update
//==================================================================================================
/**
* <pre>
* It is the row mapping class that maps each row to a result object
* In this class, it maps the row of a resultset on a per row basis
* </pre>
* 
* @version 0.02
* @author j.iwarat
*/
public class CourseScheduleRowMapper implements RowMapper<CourseSchedule> {

    @Override
    public CourseSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        Long courseId = rs.getLong("COURSE_ID");
        String courseName = rs.getString("COURSE_NAME");
        Long instructorId = rs.getLong("INSTRUCTOR_ID");
        String instructorLastName = rs.getString("INSTRUCTOR_LAST_NAME");
        String instructorFirstName = rs.getString("INSTRUCTOR_FIRST_NAME");
        Long venueId = rs.getLong("VENUE_ID");
        String venueName = rs.getString("VENUE_NAME");
        int minRequired = rs.getInt("MIN_REQUIRED");
        int maxAllowed = rs.getInt("MAX_ALLOWED");
        int totalParticipants = rs.getInt("PARTICIPANT_ID");
        char status = rs.getString("STATUS").charAt(0);

        /*
         * <pre> Set<CourseScheduleDetail> fields <pre>
         */
        ZonedDateTime scheduledStartDateTime = ZonedDateTime
                .ofInstant(rs.getTimestamp("SCHEDULED_START_DATETIME").toInstant(), ZoneId.of("UTC"));

        ZonedDateTime scheduledEndDateTime = ZonedDateTime
                .ofInstant(rs.getTimestamp("SCHEDULED_END_DATETIME").toInstant(), ZoneId.of("UTC"));
       
        CourseScheduleDetail courseScheduleDetail = 
                new CourseScheduleDetail.Builder(id, scheduledStartDateTime, scheduledEndDateTime).build();
        
        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
        
        courseScheduleDetailSet.add(courseScheduleDetail);
        
        CourseSchedule courseSchedule = new CourseSchedule.Builder(id, courseId, courseName, instructorId,
                instructorLastName, instructorFirstName, venueId, venueName, minRequired, maxAllowed, totalParticipants,
                status).addDetail(courseScheduleDetailSet).build();

        return courseSchedule;
       
    }
}
